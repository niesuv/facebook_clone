package niesuv.facebookclone.user_service.service;

import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.user_service.dto.CreateUserDTO;
import niesuv.facebookclone.user_service.dto.FacebookUserDto;
import niesuv.facebookclone.user_service.dto.FriendResponse;
import niesuv.facebookclone.user_service.dto.UpdateUserDto;
import niesuv.facebookclone.user_service.entity.FacebookUser;
import niesuv.facebookclone.user_service.exception.CreateUserException;
import niesuv.facebookclone.user_service.exception.InputNotValid;
import niesuv.facebookclone.user_service.exception.UserIdNotExists;
import niesuv.facebookclone.user_service.exception.UserNameExistException;
import niesuv.facebookclone.user_service.http.PostFeignClient;
import niesuv.facebookclone.user_service.http.S3Client;
import niesuv.facebookclone.user_service.repository.FacebookUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private FacebookUserRepository userRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private S3Client s3Client;
    @Autowired
    private FriendService friendService;

    @Autowired
    private PostFeignClient postFeignClient;

    private static FacebookUserDto toDTO(FacebookUser fbUser) {
        return FacebookUserDto.builder()
                .avtUrl(fbUser.getAvtUrl())
                .backgroundUrl(fbUser.getBackgroundUrl())
                .email(fbUser.getEmail())
                .id(fbUser.getId())
                .fullName(fbUser.getFullName())
                .birthday(fbUser.getBirthday())
                .userName(fbUser.getUserName())
                .totalFriends(fbUser.getTotalFriends())
                .build();
    }

    public FacebookUserDto getUserById(UUID id) {
        if (userRepository.existsById(id)) {
            FacebookUser user = userRepository.getReferenceById(id);
            return toDTO(user);
        }
        else throw new UserIdNotExists("User Id does not belongs to any user");
    }

    public FacebookUserDto getUserByUserName(String userName) {
        if (userRepository.existsByUserName(userName)) {
            FacebookUser user = userRepository.findByUserName(userName);
            return toDTO(user);
        }
        else throw new UserNameExistException("Username does not exist");
    }

    public UUID createUser(CreateUserDTO dto) {
        if (userRepository.existsByUserName(dto.userName()))
            throw new UserNameExistException("UserName have existed");
        if (userRepository.existsByEmail(dto.email()))
            throw new UserNameExistException("Email have exists");
        var id = userRepository.save(toFacebookUser(dto)).getId();
        if (id == null)
            throw new CreateUserException("Cannot create due to unknown error");
        return id;
    }

    FacebookUser toFacebookUser(CreateUserDTO dto) {
        return FacebookUser.builder().userName(dto.userName())
                .email(dto.email()).fullName(dto.fullName()).birthday(dto.birthDay()).build();
    }

    public void updateUser(UpdateUserDto dto) {
        if (userRepository.existsById(dto.id())) {
            FacebookUser user = userRepository.getReferenceById(dto.id());
            // set username
            if (!user.getUserName().equals(dto.userName())) {
                if (dto.userName() != null) {
                    if (userRepository.existsByUserName(dto.userName()))
                        throw new UserNameExistException("Username you change have existed");
                    user.setUserName(dto.userName());
                }
            }
            if (dto.userName() != null ) {
                user.setUserName(dto.userName());
            }

            if (dto.email() != null ) {
                user.setEmail(dto.email());
            }

            if (dto.birthday() != null ) {
                user.setBirthday(dto.birthday());
            }
            userRepository.save(user);

        }
        else
            throw new UserIdNotExists("User id not exist!");
    }

    public void deleteUser(UUID id) {

        if (!userRepository.existsById(id))
            throw new UserIdNotExists("User id not exist!");
        FacebookUser user = userRepository.getUserWithAllFriends(id);
        Set<FacebookUser> friends = user.getFriends();


        //async call
        friendService.updateTotalFriend(friends, -1);
        s3Service.deleteFolder("user/" + id.toString());
        postFeignClient.clearActionsByUserId(id);

    }



    public boolean existsById(UUID id) {
        return userRepository.existsById(id);
    }

    public void updateAvatar(UUID id, MultipartFile file) {
        Optional<FacebookUser> opUser = userRepository.findById(id);
        if (opUser.isPresent()) {
            FacebookUser user = opUser.get();
            try {
                var extension = isImage(file);
                if (extension != null) {
                    String key = "user/" + id.toString() + "/" + file.getOriginalFilename();
                    s3Client.upload(file, key);
                    user.setAvtUrl(key);
                    userRepository.save(user);
                }
                else
                    throw new InputNotValid("File is not an image");
            }
            catch (IOException e) {
                throw new RuntimeException("Error when read image!");
            }

        }
        else
            throw new UserIdNotExists("User id not exist!");
    }


    public void updateBackGround(UUID id, MultipartFile file) {
        Optional<FacebookUser> opUser = userRepository.findById(id);
        if (opUser.isPresent()) {
            FacebookUser user = opUser.get();
            try {
                var extension = isImage(file);
                if (extension != null) {
                    String key = "user/" + id.toString() + "/" + file.getOriginalFilename();
                    s3Client.upload(file, key);
                    user.setBackgroundUrl(key);
                    userRepository.save(user);
                }
                else
                    throw new InputNotValid("File is not an image");
            }
            catch (IOException e) {
                throw new RuntimeException("Error when read image!");
            }

        }
        else
            throw new UserIdNotExists("User id not exist!");
    }

    public String isImage(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            byte[] headerBytes = new byte[4];
            int bytesRead = inputStream.read(headerBytes, 0, 4);  // Đọc 4 byte từ đầu file

            if (bytesRead < 4) {
                return null;  // File quá nhỏ, không hợp lệ
            }

            String fileSignature = String
                    .format("%02X %02X %02X %02X", headerBytes[0], headerBytes[1], headerBytes[2], headerBytes[3]);

            if (fileSignature.startsWith("FF D8 FF")) {
                return "jpg";  // JPEG
            } else if (fileSignature.startsWith("89 50 4E 47")) {
                return "png";  // PNG
            }
//            else if (fileSignature.startsWith("66 74 79 70")) {
//                return "mp4";
//            }
            ;  // mp4
            return null;
        }
    }


    public FriendResponse getFriends(UUID userId, PageRequest pageRequest) {
        if (userRepository.existsById(userId)) {
            int page = pageRequest.getPageNumber();
            int pageSize = pageRequest.getPageSize();
            FacebookUser user = userRepository.getUserWithAllFriends(userId);
            List<FacebookUserDto> friends = user.getFriends().stream().skip((long) page * pageSize)
                    .limit(pageSize).map(UserService::toDTO).toList();
            return FriendResponse.builder()
                    .friends(friends)
                    .total(user.getTotalFriends())
                    .page(page)
                    .size(pageSize)
                    .build();

        }
        else
            throw new UserIdNotExists("User id not exist!");
    }
}

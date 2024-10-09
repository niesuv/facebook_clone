package niesuv.facebookclone.user_service.service;

import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.user_service.dto.CreateUserDTO;
import niesuv.facebookclone.user_service.dto.UpdateUserDto;
import niesuv.facebookclone.user_service.entity.FacebookUser;
import niesuv.facebookclone.user_service.exception.CreateUserException;
import niesuv.facebookclone.user_service.exception.InputNotValid;
import niesuv.facebookclone.user_service.exception.UserIdNotExists;
import niesuv.facebookclone.user_service.exception.UserNameExistException;
import niesuv.facebookclone.user_service.repository.FacebookUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private FacebookUserRepository userRepository;


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
                .email(dto.email()).fullName(dto.fullName()).build();
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
            userRepository.save(user);

        }
        else
            throw new UserIdNotExists("User id not exist!");
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return userRepository.existsById(id);
    }
}

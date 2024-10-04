package niesuv.facebookclone.user_service.mapper;


import lombok.AllArgsConstructor;
import niesuv.facebookclone.user_service.dto.UpdateUserDto;
import niesuv.facebookclone.user_service.dto.CreateUserDTO;
import niesuv.facebookclone.user_service.dto.FacebookUserDto;
import niesuv.facebookclone.user_service.entity.FacebookUser;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    public FacebookUser toFaceBookUser(CreateUserDTO dto) {
        return FacebookUser.builder()
                .userName(dto.userName())
                .fullName(dto.fullName())
                .email(dto.email())
                .build();
    }


    public FacebookUser toFaceBookUser(FacebookUserDto dto) {
        return FacebookUser.builder()
                .id(dto.id())
                .userName(dto.userName())
                .fullName(dto.fullName())
                .email(dto.email())
                .build();

    }


    public FacebookUser toFaceBookUser(UpdateUserDto dto) {
        return FacebookUser.builder()
                .id(dto.id())
                .fullName(dto.fullName())
                .avtUrl(dto.avtUrl())
                .backgroundUrl(dto.backgroundUrl())
                .build();
    }

    public FacebookUserDto toFaceBookUserDto(FacebookUser u) {
        return FacebookUserDto.builder()
                .id(u.getId())
                .fullName(u.getFullName())
                .backgroundUrl(u.getBackgroundUrl())
                .avtUrl(u.getBackgroundUrl())
                .email(u.getEmail())
                .userName(u.getUserName())
                .build();
    }
}

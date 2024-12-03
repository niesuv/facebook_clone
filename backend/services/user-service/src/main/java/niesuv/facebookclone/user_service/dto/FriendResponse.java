package niesuv.facebookclone.user_service.dto;


import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendResponse {
    private int total;
    private int size;
    private int page;
    private List<FacebookUserDto> friends;
}

package niesuv.facebookclone.user_service.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "friend")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    @EmbeddedId
    private FriendId friendId;

}
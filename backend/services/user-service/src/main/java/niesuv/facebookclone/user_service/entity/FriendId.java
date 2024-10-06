package niesuv.facebookclone.user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class FriendId implements Serializable {


    @Column(name = "user1_id")
    private UUID user1Id;

    @Column(name = "user2_id")
    private UUID user2Id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FriendId entity = (FriendId) o;
        return Objects.equals(this.user2Id, entity.user2Id) &&
                Objects.equals(this.user1Id, entity.user1Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user2Id, user1Id);
    }
}
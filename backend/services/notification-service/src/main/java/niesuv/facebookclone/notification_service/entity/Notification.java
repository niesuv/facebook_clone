package niesuv.facebookclone.notification_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "notification", indexes = {
        @Index(name = "idx_notification_user", columnList = "user_id")
})
public class Notification {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreatedDate
    @Column(name = "create_at",updatable = false)
    private LocalDateTime createAt;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "link_id", nullable = false)
    private UUID linkId;

}
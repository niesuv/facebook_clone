package niesuv.facebookclone.post_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "post", indexes = {
        @Index(name = "idx_post_user_id", columnList = "user_id"),
        @Index(name = "idx_post_share_id", columnList = "share_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "post_id", nullable = false)
    private UUID id;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "share_id")
    private UUID shareId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "likes", nullable = false)
    private int likes = 0;

    @Column(name = "shares", nullable = false)
    private int shares = 0;

    @Column(name = "total_comments", nullable = false)
    private int totalComments = 0;

    @Column(name = "total_image", nullable = false)
    private int totalImage = 0;

    @Column(name = "video", nullable = false)
    private boolean video = false;

    @Column(name = "create_time", updatable = false)
    @CreatedDate
    private LocalDateTime createTime;



}
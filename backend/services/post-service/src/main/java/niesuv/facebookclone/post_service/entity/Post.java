package niesuv.facebookclone.post_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
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
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "post_id", nullable = false)
    private UUID id;

    @Lob
    @Column(name = "content")
    private String content;


    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Builder.Default
    @Column(name = "total_likes", nullable = false)
    private int totalLike = 0;

    @Builder.Default
    @Column(name = "shares", nullable = false)
    private int shares = 0;


    @Builder.Default
    @Column(name = "total_comments", nullable = false)
    private int totalComments = 0;


    @Column(name = "create_time", updatable = false)
    @CreatedDate
    private LocalDateTime createTime;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new LinkedHashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "share_id")
    private Post sharePost;

    @OneToMany(mappedBy = "sharePost", cascade = CascadeType.ALL)
    private Set<Post> shareBy = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PostImage> images = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Like> likes = new LinkedHashSet<>();


}
package niesuv.facebookclone.post_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "comment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "comment_id", nullable = false)
    private UUID id;


    @Builder.Default
    @Column(name = "total_likes", nullable = false)
    private int totalLikes = 0;

    @Column(name = "user_id")
    private UUID userId;


    @Builder.Default
    @Column(name = "total_rep", nullable = false)
    private int totalReplies = 0;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_to")
    private Comment replyTo;

    @OneToMany(mappedBy = "replyTo", cascade = CascadeType.ALL)
    @OrderBy("createTime desc")
    private Set<Comment> replies = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "content")
    @Lob
    private String content;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private Set<Like> likes = new LinkedHashSet<>();


    @Column(name = "create_time", updatable = false)
    @CreatedDate
    private LocalDateTime createTime;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Comment c) {
            return id.equals(c.id);
        }
        return false;
    }
}
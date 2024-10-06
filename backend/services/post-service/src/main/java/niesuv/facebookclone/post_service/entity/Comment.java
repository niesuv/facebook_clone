package niesuv.facebookclone.post_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "comment_id", nullable = false)
    private UUID commentId;

    @Column(name = "likes", nullable = false)
    private int likes = 0;

    @Column(name = "post_id")
    private UUID postId;

    @Column(name = "create_time", updatable = false)
    @CreatedDate
    private LocalDateTime createTime;

    @ManyToOne()
    @JoinColumn(name = "reply_id")
    private Comment replyTo;

    @OneToMany(mappedBy = "replyTo", cascade =  CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createTime desc")
    private List<Comment> replies = new ArrayList<>();

}
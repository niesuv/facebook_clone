package niesuv.facebookclone.user_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class FacebookUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false)
    private UUID id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;


    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "friend",
            joinColumns = @JoinColumn(name = "user1_id"),
            inverseJoinColumns = @JoinColumn(name = "user2_id"))
    private Set<FacebookUser> friends = new LinkedHashSet<>();


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "friend",
            joinColumns = @JoinColumn(name = "user2_id"),
            inverseJoinColumns = @JoinColumn(name = "user1_id"))
    private Set<FacebookUser> beingFriend = new LinkedHashSet<>();


    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FacebookUser fb)
            return id.equals(fb.getId());
        return false;
    }
}
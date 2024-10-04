package niesuv.facebookclone.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
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

    @Column(name = "avt_url")
    private String avtUrl;

    @Column(name = "background_url")
    private String backgroundUrl;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "user1_id"),
            inverseJoinColumns = @JoinColumn(name = "user2_id"))
    private Set<FacebookUser> friends = new LinkedHashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FacebookUser fb)
            return id.equals(fb.getId());
        return false;
    }
}
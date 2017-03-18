package net.domain.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String login;
    private String name;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }
}
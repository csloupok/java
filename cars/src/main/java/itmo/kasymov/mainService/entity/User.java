package itmo.kasymov.mainService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User implements UserDetails {
    @Id
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ROLE")
    private String role;
    @OneToOne
    @JoinColumn(name = "BRAND", referencedColumnName = "BRAND_ID")
    private Brand brand;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setUsername(String newUsername) {
        this.login = newUsername;
    }

    public void setRole(String newRole) {
        this.role = newRole;
    }

    public String getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

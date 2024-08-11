package es.marcosbejar.padeleasy_backend.model;


import es.marcosbejar.padeleasy_backend.objects.UserRegisterObject;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String city;
    //@ColumnDefault("L")
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public User(){
    }

    public User(UserRegisterObject userRegisterObject) {
        this.username = userRegisterObject.getUsername();
        this.password = userRegisterObject.getPassword();
        this.firstName = userRegisterObject.getFirstName();
        this.lastName = userRegisterObject.getLastName();
        this.email = userRegisterObject.getEmail();
        this.phoneNumber = userRegisterObject.getPhoneNumber();
        this.city = userRegisterObject.getCity();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !Objects.equals(status, "L");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

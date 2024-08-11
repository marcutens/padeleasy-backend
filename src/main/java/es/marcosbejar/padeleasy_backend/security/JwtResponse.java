package es.marcosbejar.padeleasy_backend.security;

import es.marcosbejar.padeleasy_backend.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class JwtResponse {
    @Getter
    private final String accessToken;
    @Getter
    private final String refreshToken;
    @Setter
    @Getter
    private Long userId;
    @Getter
    private Set<Role> roles;
    private String email;
    private String firstName;
    private String lastName;


    // Constructor
    public JwtResponse(String accessToken, String refreshToken, Long userId, Set<Role> roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.roles = roles;
    }

}

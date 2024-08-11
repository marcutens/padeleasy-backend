package es.marcosbejar.padeleasy_backend.objects;

import lombok.Data;

import java.util.Set;

@Data
public class UserRegisterObject {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String city;
    private Set<Long> roles;
}

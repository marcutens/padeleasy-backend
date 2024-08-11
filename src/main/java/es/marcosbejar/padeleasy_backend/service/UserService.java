package es.marcosbejar.padeleasy_backend.service;

import es.marcosbejar.padeleasy_backend.model.Role;
import es.marcosbejar.padeleasy_backend.model.User;
import es.marcosbejar.padeleasy_backend.objects.UserRegisterObject;
import es.marcosbejar.padeleasy_backend.repository.RoleRepository;
import es.marcosbejar.padeleasy_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public void registerUser(UserRegisterObject user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Crear la entidad User
        User new_user = new User(user);

        // Asignar los roles al usuario
        Set<Role> roles = user.getRoles().stream()
                .map(roleId -> roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found")))
                .collect(Collectors.toSet());

        new_user.setRoles(roles);

        // Guardar el usuario en la base de datos
        userRepository.save(new_user);
    }

    public Set<Role> getUserRoles(String username) {
        User user = userRepository.findByUsername(username);
        return user.getRoles(); // Asume que el método getRoles() en User devuelve un Set<Role>
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

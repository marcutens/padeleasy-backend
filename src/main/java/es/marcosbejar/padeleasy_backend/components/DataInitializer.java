package es.marcosbejar.padeleasy_backend.components;

import es.marcosbejar.padeleasy_backend.model.Role;
import es.marcosbejar.padeleasy_backend.model.RoleName;
import es.marcosbejar.padeleasy_backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(new Role(RoleName.ROLE_ADMIN));
            roleRepository.save(new Role(RoleName.ROLE_USER));
            roleRepository.save(new Role(RoleName.ROLE_COURT_ADMIN));
        }
    }
}
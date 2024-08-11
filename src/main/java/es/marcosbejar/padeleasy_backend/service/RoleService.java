package es.marcosbejar.padeleasy_backend.service;

import es.marcosbejar.padeleasy_backend.model.Role;
import es.marcosbejar.padeleasy_backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAllExceptAdmin() {
        return roleRepository.findAllExceptAdmin();
    }
}

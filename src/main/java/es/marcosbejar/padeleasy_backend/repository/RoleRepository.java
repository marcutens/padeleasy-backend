package es.marcosbejar.padeleasy_backend.repository;

import es.marcosbejar.padeleasy_backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    @Query("SELECT r FROM Role r WHERE r.name <> 'ROLE_ADMIN'")
    List<Role> findAllExceptAdmin();
}
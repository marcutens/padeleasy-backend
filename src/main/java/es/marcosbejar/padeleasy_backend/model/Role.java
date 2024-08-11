package es.marcosbejar.padeleasy_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    private String descriptionName;


    public Role(){}

    public Role(RoleName roleName) {
        this.name = roleName;
        this.descriptionName = roleName.getNombreDescriptivo();
    }

}

package es.marcosbejar.padeleasy_backend.model;


public enum RoleName {

    ROLE_ADMIN("Admin"),
    ROLE_USER("Jugador de Padel"),
    ROLE_COURT_ADMIN("Administrador de pistas de padel");

    private String nombreDescriptivo;

    private RoleName(String nombreDescriptivo) {
        this.nombreDescriptivo = nombreDescriptivo;
    }

    public String getNombreDescriptivo() {
        return nombreDescriptivo;
    }
}


package co.edu.uniquindio.plataformalogistica.model.dto;

/**
 * DTO para la entidad Usuario
 * RF-040: Uso de Data Transfer Objects
 */
public class UsuarioDTO {
    
    private String idUsuario;
    private String nombreCompleto;
    private String correoElectronico;
    private String telefono;
    private int cantidadDirecciones;
    private int cantidadEnvios;
    private String ultimoEnvio;

    // Constructor vacío
    public UsuarioDTO() {
    }

    // Constructor básico para login
    public UsuarioDTO(String idUsuario, String nombreCompleto, String correoElectronico) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
    }

    // Constructor completo para admin
    public UsuarioDTO(String idUsuario, String nombreCompleto, String correoElectronico, 
                     String telefono, int cantidadDirecciones, int cantidadEnvios, 
                     String ultimoEnvio) {
        this(idUsuario, nombreCompleto, correoElectronico);
        this.telefono = telefono;
        this.cantidadDirecciones = cantidadDirecciones;
        this.cantidadEnvios = cantidadEnvios;
        this.ultimoEnvio = ultimoEnvio;
    }

    // Getters y Setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCantidadDirecciones() {
        return cantidadDirecciones;
    }

    public void setCantidadDirecciones(int cantidadDirecciones) {
        this.cantidadDirecciones = cantidadDirecciones;
    }

    public int getCantidadEnvios() {
        return cantidadEnvios;
    }

    public void setCantidadEnvios(int cantidadEnvios) {
        this.cantidadEnvios = cantidadEnvios;
    }

    public String getUltimoEnvio() {
        return ultimoEnvio;
    }

    public void setUltimoEnvio(String ultimoEnvio) {
        this.ultimoEnvio = ultimoEnvio;
    }

    /**
     * Obtiene información resumida del usuario
     */
    public String getInformacionResumida() {
        return String.format("%s (%s) - %d envíos", 
                           nombreCompleto, correoElectronico, cantidadEnvios);
    }

    @Override
    public String toString() {
        return String.format("UsuarioDTO{id='%s', nombre='%s', email='%s'}", 
                           idUsuario, nombreCompleto, correoElectronico);
    }
}
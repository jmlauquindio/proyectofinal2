package co.edu.uniquindio.plataformalogistica.model;

import co.edu.uniquindio.plataformalogistica.model.enums.MetodoPago;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entidad Usuario que representa a las personas que utilizan la plataforma
 * Según RF-015 a RF-018: Gestión de usuarios
 */
public class Usuario {
    
    private String idUsuario;
    private String nombreCompleto;
    private String correoElectronico;
    private String telefono;
    private String password;
    private List<Direccion> direccionesFrecuentes;
    private List<MetodoPago> metodosPago;
    private List<Envio> historialEnvios;

    // Constructor vacío
    public Usuario() {
        this.direccionesFrecuentes = new ArrayList<>();
        this.metodosPago = new ArrayList<>();
        this.historialEnvios = new ArrayList<>();
    }

    // Constructor completo
    public Usuario(String idUsuario, String nombreCompleto, String correoElectronico, 
                   String telefono, String password) {
        this();
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Direccion> getDireccionesFrecuentes() {
        return direccionesFrecuentes;
    }

    public void setDireccionesFrecuentes(List<Direccion> direccionesFrecuentes) {
        this.direccionesFrecuentes = direccionesFrecuentes;
    }

    public List<MetodoPago> getMetodosPago() {
        return metodosPago;
    }

    public void setMetodosPago(List<MetodoPago> metodosPago) {
        this.metodosPago = metodosPago;
    }

    public List<Envio> getHistorialEnvios() {
        return historialEnvios;
    }

    public void setHistorialEnvios(List<Envio> historialEnvios) {
        this.historialEnvios = historialEnvios;
    }

    // Métodos de negocio

    /**
     * RF-016: Administrar direcciones frecuentes - Agregar dirección
     */
    public boolean agregarDireccionFrecuente(Direccion direccion) {
        if (direccion != null && !direccionesFrecuentes.contains(direccion)) {
            return direccionesFrecuentes.add(direccion);
        }
        return false;
    }

    /**
     * RF-016: Administrar direcciones frecuentes - Eliminar dirección
     */
    public boolean eliminarDireccionFrecuente(String idDireccion) {
        return direccionesFrecuentes.removeIf(d -> d.getIdDireccion().equals(idDireccion));
    }

    /**
     * RF-016: Administrar direcciones frecuentes - Buscar dirección
     */
    public Direccion buscarDireccionPorId(String idDireccion) {
        return direccionesFrecuentes.stream()
                .filter(d -> d.getIdDireccion().equals(idDireccion))
                .findFirst()
                .orElse(null);
    }

    /**
     * RF-017: Gestionar métodos de pago - Agregar método
     */
    public boolean agregarMetodoPago(MetodoPago metodoPago) {
        if (metodoPago != null && !metodosPago.contains(metodoPago)) {
            return metodosPago.add(metodoPago);
        }
        return false;
    }

    /**
     * RF-017: Gestionar métodos de pago - Eliminar método
     */
    public boolean eliminarMetodoPago(MetodoPago metodoPago) {
        return metodosPago.remove(metodoPago);
    }

    /**
     * RF-018: Consultar envíos asociados - Agregar envío al historial
     */
    public void agregarEnvioAlHistorial(Envio envio) {
        if (envio != null && !historialEnvios.contains(envio)) {
            historialEnvios.add(envio);
        }
    }

    /**
     * Valida si el usuario tiene credenciales correctas para login
     */
    public boolean validarCredenciales(String email, String password) {
        return this.correoElectronico.equals(email) && this.password.equals(password);
    }

    /**
     * Actualiza el perfil del usuario
     * RF-002: Gestionar perfil
     */
    public void actualizarPerfil(String nombreCompleto, String telefono) {
        if (nombreCompleto != null && !nombreCompleto.trim().isEmpty()) {
            this.nombreCompleto = nombreCompleto;
        }
        if (telefono != null && !telefono.trim().isEmpty()) {
            this.telefono = telefono;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    @Override
    public String toString() {
        return nombreCompleto + " (" + correoElectronico + ")";
    }
}
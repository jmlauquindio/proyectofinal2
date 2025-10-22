package co.edu.uniquindio.plataformalogistica.model.dto;

import co.edu.uniquindio.plataformalogistica.model.enums.EstadoEnvio;
import co.edu.uniquindio.plataformalogistica.model.enums.ServicioAdicional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para la entidad Envío
 * RF-040: Uso de Data Transfer Objects
 */
public class EnvioDTO {
    
    private String idEnvio;
    private String codigoSeguimiento;
    private DireccionDTO origen;
    private DireccionDTO destino;
    private String nombreUsuario;
    private String nombreRepartidor;
    
    // Información del paquete
    private double peso;
    private double volumen;
    private String descripcionPaquete;
    
    // Estado y fechas
    private String estado;
    private String fechaCreacion;
    private String fechaEstimadaEntrega;
    private String fechaEntregaReal;
    
    // Costos
    private double costo;
    private List<String> serviciosAdicionales;
    private boolean esPrioritario;
    
    // Seguimiento
    private List<String> historialEstados;

    // Constructor vacío
    public EnvioDTO() {
    }

    // Constructor completo para mostrar en UI
    public EnvioDTO(String idEnvio, String codigoSeguimiento, DireccionDTO origen, 
                    DireccionDTO destino, String nombreUsuario, String estado, 
                    String fechaCreacion, double costo) {
        this.idEnvio = idEnvio;
        this.codigoSeguimiento = codigoSeguimiento;
        this.origen = origen;
        this.destino = destino;
        this.nombreUsuario = nombreUsuario;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.costo = costo;
    }

    // Getters y Setters
    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getCodigoSeguimiento() {
        return codigoSeguimiento;
    }

    public void setCodigoSeguimiento(String codigoSeguimiento) {
        this.codigoSeguimiento = codigoSeguimiento;
    }

    public DireccionDTO getOrigen() {
        return origen;
    }

    public void setOrigen(DireccionDTO origen) {
        this.origen = origen;
    }

    public DireccionDTO getDestino() {
        return destino;
    }

    public void setDestino(DireccionDTO destino) {
        this.destino = destino;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public String getDescripcionPaquete() {
        return descripcionPaquete;
    }

    public void setDescripcionPaquete(String descripcionPaquete) {
        this.descripcionPaquete = descripcionPaquete;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    public void setFechaEstimadaEntrega(String fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    public String getFechaEntregaReal() {
        return fechaEntregaReal;
    }

    public void setFechaEntregaReal(String fechaEntregaReal) {
        this.fechaEntregaReal = fechaEntregaReal;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public List<String> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<String> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public boolean isEsPrioritario() {
        return esPrioritario;
    }

    public void setEsPrioritario(boolean esPrioritario) {
        this.esPrioritario = esPrioritario;
    }

    public List<String> getHistorialEstados() {
        return historialEstados;
    }

    public void setHistorialEstados(List<String> historialEstados) {
        this.historialEstados = historialEstados;
    }

    /**
     * Obtiene una representación resumida para tablas
     */
    public String getResumen() {
        return String.format("%s | %s → %s | %s | $%,.0f", 
                           codigoSeguimiento,
                           origen != null ? origen.getAlias() : "N/A",
                           destino != null ? destino.getAlias() : "N/A",
                           estado, 
                           costo);
    }

    @Override
    public String toString() {
        return String.format("EnvioDTO{id='%s', codigo='%s', estado='%s', costo=%s}", 
                           idEnvio, codigoSeguimiento, estado, costo);
    }
}
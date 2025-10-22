package co.edu.uniquindio.plataformalogistica.model;

import co.edu.uniquindio.plataformalogistica.model.enums.EstadoRepartidor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entidad Repartidor que representa a las personas encargadas del transporte
 * Según RF-019 a RF-021: Gestión de repartidores
 */
public class Repartidor {
    
    private String idRepartidor;
    private String nombre;
    private String documento;
    private String telefono;
    private EstadoRepartidor estado;
    private String zonaCobertura;
    private List<Envio> enviosAsignados;
    private int capacidadMaxima; // Número máximo de envíos simultáneos
    private String vehiculo;

    // Constructor vacío
    public Repartidor() {
        this.enviosAsignados = new ArrayList<>();
        this.estado = EstadoRepartidor.INACTIVO;
        this.capacidadMaxima = 5; // Valor por defecto
    }

    // Constructor completo
    public Repartidor(String idRepartidor, String nombre, String documento, String telefono,
                      String zonaCobertura, String vehiculo) {
        this();
        this.idRepartidor = idRepartidor;
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.zonaCobertura = zonaCobertura;
        this.vehiculo = vehiculo;
    }

    // Getters y Setters
    public String getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(String idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public EstadoRepartidor getEstado() {
        return estado;
    }

    public void setEstado(EstadoRepartidor estado) {
        this.estado = estado;
    }

    public String getZonaCobertura() {
        return zonaCobertura;
    }

    public void setZonaCobertura(String zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }

    public List<Envio> getEnviosAsignados() {
        return enviosAsignados;
    }

    public void setEnviosAsignados(List<Envio> enviosAsignados) {
        this.enviosAsignados = enviosAsignados;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    // Métodos de negocio

    /**
     * RF-020: Cambiar disponibilidad
     */
    public void cambiarDisponibilidad(EstadoRepartidor nuevoEstado) {
        this.estado = nuevoEstado;
    }

    /**
     * RF-021: Consultar envíos asignados - Asignar nuevo envío
     */
    public boolean asignarEnvio(Envio envio) {
        if (puedeRecibirEnvio() && envio != null) {
            enviosAsignados.add(envio);
            if (estado == EstadoRepartidor.ACTIVO) {
                estado = EstadoRepartidor.EN_RUTA;
            }
            return true;
        }
        return false;
    }

    /**
     * RF-021: Consultar envíos asignados - Completar envío
     */
    public boolean completarEnvio(String idEnvio) {
        boolean removed = enviosAsignados.removeIf(e -> e.getIdEnvio().equals(idEnvio));
        
        // Si no tiene más envíos asignados, cambiar estado a ACTIVO
        if (removed && enviosAsignados.isEmpty() && estado == EstadoRepartidor.EN_RUTA) {
            estado = EstadoRepartidor.ACTIVO;
        }
        
        return removed;
    }

    /**
     * Verifica si el repartidor puede recibir más envíos
     */
    public boolean puedeRecibirEnvio() {
        return (estado == EstadoRepartidor.ACTIVO || estado == EstadoRepartidor.EN_RUTA) 
               && enviosAsignados.size() < capacidadMaxima;
    }

    /**
     * Obtiene el número de envíos pendientes
     */
    public int getEnviosPendientes() {
        return enviosAsignados.size();
    }

    /**
     * Verifica si está disponible para asignaciones
     */
    public boolean estaDisponible() {
        return estado == EstadoRepartidor.ACTIVO || 
               (estado == EstadoRepartidor.EN_RUTA && puedeRecibirEnvio());
    }

    /**
     * Obtiene información resumida del repartidor
     */
    public String getInformacionResumida() {
        return String.format("%s - %s (%d/%d envíos) - %s", 
                           nombre, estado.getNombre(), 
                           enviosAsignados.size(), capacidadMaxima, 
                           zonaCobertura);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repartidor that = (Repartidor) o;
        return Objects.equals(idRepartidor, that.idRepartidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepartidor);
    }

    @Override
    public String toString() {
        return nombre + " - " + documento + " (" + estado.getNombre() + ")";
    }
}
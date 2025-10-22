package co.edu.uniquindio.plataformalogistica.model.dto;

/**
 * DTO para la entidad Repartidor
 * RF-040: Uso de Data Transfer Objects
 */
public class RepartidorDTO {
    
    private String idRepartidor;
    private String nombre;
    private String documento;
    private String telefono;
    private String estado;
    private String zonaCobertura;
    private int enviosAsignados;
    private int capacidadMaxima;
    private String vehiculo;
    private String disponibilidad;

    // Constructor vacío
    public RepartidorDTO() {
    }

    // Constructor básico
    public RepartidorDTO(String idRepartidor, String nombre, String documento, 
                        String estado, String zonaCobertura) {
        this.idRepartidor = idRepartidor;
        this.nombre = nombre;
        this.documento = documento;
        this.estado = estado;
        this.zonaCobertura = zonaCobertura;
    }

    // Constructor completo
    public RepartidorDTO(String idRepartidor, String nombre, String documento, 
                        String telefono, String estado, String zonaCobertura,
                        int enviosAsignados, int capacidadMaxima, String vehiculo) {
        this(idRepartidor, nombre, documento, estado, zonaCobertura);
        this.telefono = telefono;
        this.enviosAsignados = enviosAsignados;
        this.capacidadMaxima = capacidadMaxima;
        this.vehiculo = vehiculo;
        this.disponibilidad = calcularDisponibilidad();
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        this.disponibilidad = calcularDisponibilidad();
    }

    public String getZonaCobertura() {
        return zonaCobertura;
    }

    public void setZonaCobertura(String zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }

    public int getEnviosAsignados() {
        return enviosAsignados;
    }

    public void setEnviosAsignados(int enviosAsignados) {
        this.enviosAsignados = enviosAsignados;
        this.disponibilidad = calcularDisponibilidad();
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.disponibilidad = calcularDisponibilidad();
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
     * Calcula el estado de disponibilidad
     */
    private String calcularDisponibilidad() {
        if ("INACTIVO".equals(estado)) {
            return "No disponible";
        }
        
        if (enviosAsignados >= capacidadMaxima) {
            return "Sin capacidad";
        }
        
        if ("ACTIVO".equals(estado)) {
            return "Disponible";
        }
        
        if ("EN_RUTA".equals(estado)) {
            return String.format("En ruta (%d/%d)", enviosAsignados, capacidadMaxima);
        }
        
        return "Desconocido";
    }

    /**
     * Obtiene información para tabla de administración
     */
    public String getResumenAdmin() {
        return String.format("%s - %s - %s (%d/%d) - %s", 
                           nombre, documento, estado, 
                           enviosAsignados, capacidadMaxima, zonaCobertura);
    }

    /**
     * Verifica si puede recibir asignaciones
     */
    public boolean puedeRecibirAsignaciones() {
        return ("ACTIVO".equals(estado) || "EN_RUTA".equals(estado)) 
               && enviosAsignados < capacidadMaxima;
    }

    @Override
    public String toString() {
        return String.format("RepartidorDTO{id='%s', nombre='%s', estado='%s', disponibilidad='%s'}", 
                           idRepartidor, nombre, estado, disponibilidad);
    }
}
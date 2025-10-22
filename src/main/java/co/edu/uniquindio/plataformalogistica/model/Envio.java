package co.edu.uniquindio.plataformalogistica.model;

import co.edu.uniquindio.plataformalogistica.model.enums.EstadoEnvio;
import co.edu.uniquindio.plataformalogistica.model.enums.ServicioAdicional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entidad Envío que representa cada solicitud de transporte de un paquete
 * Según RF-022 a RF-026: Gestión de envíos
 */
public class Envio {
    
    private String idEnvio;
    private Direccion origen;
    private Direccion destino;
    private Usuario usuario;
    private Repartidor repartidor;
    
    // Información del paquete
    private double peso; // En kilogramos
    private double largo; // En centímetros
    private double ancho; // En centímetros
    private double alto; // En centímetros
    private String descripcionPaquete;
    
    // Control del envío
    private EstadoEnvio estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEstimadaEntrega;
    private LocalDateTime fechaEntregaReal;
    
    // Costos y servicios
    private double costo;
    private List<ServicioAdicional> serviciosAdicionales;
    private boolean esPrioritario;
    
    // Seguimiento
    private List<String> historialEstados;
    private String observaciones;
    private Pago pago;

    // Constructor vacío
    public Envio() {
        this.estado = EstadoEnvio.SOLICITADO;
        this.fechaCreacion = LocalDateTime.now();
        this.serviciosAdicionales = new ArrayList<>();
        this.historialEstados = new ArrayList<>();
        this.esPrioritario = false;
    }

    // Constructor completo
    public Envio(String idEnvio, Direccion origen, Direccion destino, Usuario usuario,
                 double peso, double largo, double ancho, double alto, String descripcionPaquete) {
        this();
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.destino = destino;
        this.usuario = usuario;
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.descripcionPaquete = descripcionPaquete;
        
        // Registrar estado inicial
        agregarHistorialEstado("Envío creado");
    }

    // Getters y Setters
    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Direccion getOrigen() {
        return origen;
    }

    public void setOrigen(Direccion origen) {
        this.origen = origen;
    }

    public Direccion getDestino() {
        return destino;
    }

    public void setDestino(Direccion destino) {
        this.destino = destino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public String getDescripcionPaquete() {
        return descripcionPaquete;
    }

    public void setDescripcionPaquete(String descripcionPaquete) {
        this.descripcionPaquete = descripcionPaquete;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    public void setFechaEstimadaEntrega(LocalDateTime fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    public LocalDateTime getFechaEntregaReal() {
        return fechaEntregaReal;
    }

    public void setFechaEntregaReal(LocalDateTime fechaEntregaReal) {
        this.fechaEntregaReal = fechaEntregaReal;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    // Métodos de negocio

    /**
     * RF-023: Actualizar estado del envío
     */
    public void actualizarEstado(EstadoEnvio nuevoEstado, String observacion) {
        EstadoEnvio estadoAnterior = this.estado;
        this.estado = nuevoEstado;
        
        String mensaje = String.format("%s: %s -> %s", 
                                     LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                                     estadoAnterior.getNombre(), 
                                     nuevoEstado.getNombre());
        
        if (observacion != null && !observacion.trim().isEmpty()) {
            mensaje += " - " + observacion;
        }
        
        agregarHistorialEstado(mensaje);
        
        // Actualizar fecha de entrega si se entregó
        if (nuevoEstado == EstadoEnvio.ENTREGADO) {
            this.fechaEntregaReal = LocalDateTime.now();
        }
    }

    /**
     * RF-024: Cancelar envío antes de ser asignado
     */
    public boolean cancelarEnvio() {
        if (estado == EstadoEnvio.SOLICITADO) {
            this.estado = EstadoEnvio.INCIDENCIA;
            agregarHistorialEstado("Envío cancelado por el usuario");
            return true;
        }
        return false;
    }

    /**
     * Calcula el volumen del paquete en metros cúbicos
     */
    public double calcularVolumen() {
        return (largo * ancho * alto) / 1000000.0; // Convertir cm³ a m³
    }

    /**
     * Calcula la distancia entre origen y destino
     */
    public double calcularDistancia() {
        if (origen != null && destino != null) {
            return origen.calcularDistancia(destino);
        }
        return 0.0;
    }

    /**
     * Agrega un servicio adicional al envío
     */
    public boolean agregarServicioAdicional(ServicioAdicional servicio) {
        if (estado == EstadoEnvio.SOLICITADO && !serviciosAdicionales.contains(servicio)) {
            serviciosAdicionales.add(servicio);
            if (servicio == ServicioAdicional.PRIORIDAD) {
                this.esPrioritario = true;
            }
            return true;
        }
        return false;
    }

    /**
     * Elimina un servicio adicional del envío
     */
    public boolean eliminarServicioAdicional(ServicioAdicional servicio) {
        if (estado == EstadoEnvio.SOLICITADO) {
            boolean removed = serviciosAdicionales.remove(servicio);
            if (removed && servicio == ServicioAdicional.PRIORIDAD) {
                this.esPrioritario = false;
            }
            return removed;
        }
        return false;
    }

    /**
     * Verifica si el envío puede ser modificado
     */
    public boolean puedeSerModificado() {
        return estado == EstadoEnvio.SOLICITADO;
    }

    /**
     * Verifica si el envío puede ser asignado a un repartidor
     */
    public boolean puedeSerAsignado() {
        return estado == EstadoEnvio.SOLICITADO && pago != null && pago.fueExitoso();
    }

    /**
     * Obtiene información resumida del envío
     */
    public String getInformacionResumida() {
        return String.format("Envío %s - %s a %s - %s - $%,.0f", 
                           idEnvio, 
                           origen.getAlias(), 
                           destino.getAlias(), 
                           estado.getNombre(),
                           costo);
    }

    /**
     * Genera código de seguimiento público
     */
    public String getCodigoSeguimiento() {
        return "LOG" + idEnvio.substring(Math.max(0, idEnvio.length() - 6));
    }

    /**
     * Estima el tiempo de entrega en horas
     */
    public int estimarTiempoEntrega() {
        double distancia = calcularDistancia();
        int tiempoBase = esPrioritario ? 2 : 4; // Horas base
        int tiempoPorDistancia = (int) Math.ceil(distancia / 10); // 10km por hora promedio
        return tiempoBase + tiempoPorDistancia;
    }

    /**
     * Agrega una entrada al historial de estados
     */
    private void agregarHistorialEstado(String mensaje) {
        historialEstados.add(mensaje);
    }

    /**
     * RF-025: Filtrar envíos por fecha
     */
    public boolean estaEnRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return !fechaCreacion.isBefore(fechaInicio) && !fechaCreacion.isAfter(fechaFin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Envio envio = (Envio) o;
        return Objects.equals(idEnvio, envio.idEnvio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEnvio);
    }

    @Override
    public String toString() {
        return String.format("Envío %s - %s (%s)", idEnvio, estado.getNombre(), getCodigoSeguimiento());
    }
}
package co.edu.uniquindio.plataformalogistica.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Entidad Incidencia para registrar problemas durante los envíos
 * Mencionada en RF-012: registrar incidencias
 */
public class Incidencia {
    
    private String idIncidencia;
    private String idEnvio;
    private String descripcion;
    private String tipoIncidencia;
    private LocalDateTime fechaReporte;
    private String reportadoPor; // ID del repartidor o "SISTEMA"
    private boolean resuelta;
    private String solucion;
    private LocalDateTime fechaResolucion;

    // Constructor vacío
    public Incidencia() {
        this.fechaReporte = LocalDateTime.now();
        this.resuelta = false;
    }

    // Constructor completo
    public Incidencia(String idIncidencia, String idEnvio, String descripcion, 
                     String tipoIncidencia, String reportadoPor) {
        this();
        this.idIncidencia = idIncidencia;
        this.idEnvio = idEnvio;
        this.descripcion = descripcion;
        this.tipoIncidencia = tipoIncidencia;
        this.reportadoPor = reportadoPor;
    }

    // Getters y Setters
    public String getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoIncidencia() {
        return tipoIncidencia;
    }

    public void setTipoIncidencia(String tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }

    public LocalDateTime getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(LocalDateTime fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getReportadoPor() {
        return reportadoPor;
    }

    public void setReportadoPor(String reportadoPor) {
        this.reportadoPor = reportadoPor;
    }

    public boolean isResuelta() {
        return resuelta;
    }

    public void setResuelta(boolean resuelta) {
        this.resuelta = resuelta;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(LocalDateTime fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    // Métodos de negocio

    /**
     * Resuelve la incidencia con una solución
     */
    public void resolver(String solucion) {
        this.solucion = solucion;
        this.resuelta = true;
        this.fechaResolucion = LocalDateTime.now();
    }

    /**
     * Obtiene el tiempo transcurrido desde el reporte
     */
    public String getTiempoTranscurrido() {
        LocalDateTime ahora = LocalDateTime.now();
        long horas = java.time.Duration.between(fechaReporte, ahora).toHours();
        
        if (horas < 24) {
            return horas + " horas";
        } else {
            long dias = horas / 24;
            return dias + " días";
        }
    }

    /**
     * Genera reporte de la incidencia
     */
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE DE INCIDENCIA ===\n");
        sb.append(String.format("ID: %s\n", idIncidencia));
        sb.append(String.format("Envío: %s\n", idEnvio));
        sb.append(String.format("Tipo: %s\n", tipoIncidencia));
        sb.append(String.format("Fecha: %s\n", fechaReporte.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append(String.format("Reportado por: %s\n", reportadoPor));
        sb.append(String.format("Descripción: %s\n", descripcion));
        sb.append(String.format("Estado: %s\n", resuelta ? "RESUELTA" : "PENDIENTE"));
        
        if (resuelta) {
            sb.append(String.format("Solución: %s\n", solucion));
            sb.append(String.format("Fecha resolución: %s\n", 
                                  fechaResolucion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        }
        
        sb.append("============================");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incidencia that = (Incidencia) o;
        return Objects.equals(idIncidencia, that.idIncidencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIncidencia);
    }

    @Override
    public String toString() {
        return String.format("Incidencia %s - %s (%s)", 
                           idIncidencia, tipoIncidencia, 
                           resuelta ? "RESUELTA" : "PENDIENTE");
    }
}
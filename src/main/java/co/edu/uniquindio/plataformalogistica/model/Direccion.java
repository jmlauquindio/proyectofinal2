package co.edu.uniquindio.plataformalogistica.model;

import java.util.Objects;

/**
 * Entidad que representa una dirección de origen o destino
 * Según RF-027 a RF-030: Gestión de direcciones
 */
public class Direccion {
    
    private String idDireccion;
    private String alias;
    private String calle;
    private String ciudad;
    private double latitud;
    private double longitud;
    private String descripcionAdicional;

    // Constructor vacío
    public Direccion() {
    }

    // Constructor completo
    public Direccion(String idDireccion, String alias, String calle, String ciudad, 
                     double latitud, double longitud, String descripcionAdicional) {
        this.idDireccion = idDireccion;
        this.alias = alias;
        this.calle = calle;
        this.ciudad = ciudad;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcionAdicional = descripcionAdicional;
    }

    // Constructor sin descripción adicional
    public Direccion(String idDireccion, String alias, String calle, String ciudad, 
                     double latitud, double longitud) {
        this(idDireccion, alias, calle, ciudad, latitud, longitud, "");
    }

    // Getters y Setters
    public String getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(String idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDescripcionAdicional() {
        return descripcionAdicional;
    }

    public void setDescripcionAdicional(String descripcionAdicional) {
        this.descripcionAdicional = descripcionAdicional;
    }

    /**
     * Calcula la distancia en kilómetros a otra dirección usando la fórmula de Haversine
     * @param otra La otra dirección
     * @return Distancia en kilómetros
     */
    public double calcularDistancia(Direccion otra) {
        final int RADIO_TIERRA_KM = 6371;
        
        double latDistance = Math.toRadians(otra.latitud - this.latitud);
        double lonDistance = Math.toRadians(otra.longitud - this.longitud);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.latitud)) * Math.cos(Math.toRadians(otra.latitud))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return RADIO_TIERRA_KM * c;
    }

    /**
     * Obtiene la dirección completa como string
     * @return Dirección formateada
     */
    public String getDireccionCompleta() {
        StringBuilder sb = new StringBuilder();
        sb.append(calle);
        if (descripcionAdicional != null && !descripcionAdicional.isEmpty()) {
            sb.append(" - ").append(descripcionAdicional);
        }
        sb.append(", ").append(ciudad);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return Objects.equals(idDireccion, direccion.idDireccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDireccion);
    }

    @Override
    public String toString() {
        return alias + ": " + getDireccionCompleta();
    }
}
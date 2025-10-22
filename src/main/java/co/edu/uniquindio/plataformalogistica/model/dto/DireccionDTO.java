package co.edu.uniquindio.plataformalogistica.model.dto;

/**
 * DTO para la entidad Dirección
 * RF-040: Uso de Data Transfer Objects
 */
public class DireccionDTO {
    
    private String idDireccion;
    private String alias;
    private String direccionCompleta;
    private String ciudad;
    private double latitud;
    private double longitud;

    // Constructor vacío
    public DireccionDTO() {
    }

    // Constructor completo
    public DireccionDTO(String idDireccion, String alias, String direccionCompleta, String ciudad) {
        this.idDireccion = idDireccion;
        this.alias = alias;
        this.direccionCompleta = direccionCompleta;
        this.ciudad = ciudad;
    }

    // Constructor con coordenadas
    public DireccionDTO(String idDireccion, String alias, String direccionCompleta, 
                       String ciudad, double latitud, double longitud) {
        this(idDireccion, alias, direccionCompleta, ciudad);
        this.latitud = latitud;
        this.longitud = longitud;
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

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
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

    /**
     * Obtiene la representación completa para mostrar en UI
     */
    public String getRepresentacionCompleta() {
        return String.format("%s: %s, %s", alias, direccionCompleta, ciudad);
    }

    @Override
    public String toString() {
        return getRepresentacionCompleta();
    }
}
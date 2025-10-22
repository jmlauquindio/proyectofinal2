package co.edu.uniquindio.plataformalogistica.model.enums;

/**
 * Estados posibles de un envío en el sistema
 * Según RF-006: Rastrear estado del envío
 */
public enum EstadoEnvio {
    SOLICITADO("Solicitado", "El envío ha sido solicitado y está pendiente de asignación"),
    ASIGNADO("Asignado", "El envío ha sido asignado a un repartidor"),
    EN_RUTA("En Ruta", "El repartidor está en camino con el paquete"),
    ENTREGADO("Entregado", "El paquete ha sido entregado exitosamente"),
    INCIDENCIA("Incidencia", "Ha ocurrido un problema durante el envío");

    private final String nombre;
    private final String descripcion;

    EstadoEnvio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
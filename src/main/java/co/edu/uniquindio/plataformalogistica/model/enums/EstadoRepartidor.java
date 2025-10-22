package co.edu.uniquindio.plataformalogistica.model.enums;

/**
 * Estados de disponibilidad de un repartidor
 * Según RF-020: Cambiar disponibilidad
 */
public enum EstadoRepartidor {
    ACTIVO("Activo", "Disponible para recibir asignaciones"),
    INACTIVO("Inactivo", "No disponible para asignaciones"),
    EN_RUTA("En Ruta", "Realizando una entrega");

    private final String nombre;
    private final String descripcion;

    EstadoRepartidor(String nombre, String descripcion) {
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
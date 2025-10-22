package co.edu.uniquindio.plataformalogistica.model.enums;

/**
 * Resultado del procesamiento de un pago
 * Según RF-033: Registrar pagos asociados a envíos
 */
public enum ResultadoPago {
    APROBADO("Aprobado", "El pago fue procesado exitosamente"),
    RECHAZADO("Rechazado", "El pago fue rechazado por el proveedor"),
    PENDIENTE("Pendiente", "El pago está en proceso de verificación"),
    ERROR("Error", "Ocurrió un error durante el procesamiento");

    private final String nombre;
    private final String descripcion;

    ResultadoPago(String nombre, String descripcion) {
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
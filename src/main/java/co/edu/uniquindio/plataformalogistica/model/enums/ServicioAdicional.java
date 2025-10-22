package co.edu.uniquindio.plataformalogistica.model.enums;

/**
 * Servicios adicionales que se pueden agregar a un envío
 * Según RF-007: Agregar servicios adicionales
 */
public enum ServicioAdicional {
    SEGURO("Seguro", "Cobertura por daños o pérdida", 5000.0),
    FRAGIL("Frágil", "Manejo especial para productos frágiles", 3000.0),
    FIRMA_REQUERIDA("Firma Requerida", "Requiere firma del destinatario", 2000.0),
    PRIORIDAD("Prioridad", "Entrega prioritaria", 8000.0),
    EMPAQUE_ESPECIAL("Empaque Especial", "Empaque adicional de protección", 4000.0);

    private final String nombre;
    private final String descripcion;
    private final double costoAdicional;

    ServicioAdicional(String nombre, String descripcion, double costoAdicional) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costoAdicional = costoAdicional;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getCostoAdicional() {
        return costoAdicional;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
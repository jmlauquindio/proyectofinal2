package co.edu.uniquindio.plataformalogistica.model.enums;

/**
 * Métodos de pago disponibles en el sistema
 * Según RF-017: Gestionar métodos de pago simulados
 */
public enum MetodoPago {
    TARJETA_CREDITO("Tarjeta de Crédito", "Pago con tarjeta de crédito"),
    TARJETA_DEBITO("Tarjeta de Débito", "Pago con tarjeta débito"),
    PSE("PSE", "Pago Seguro en Línea"),
    EFECTIVO("Efectivo", "Pago en efectivo contra entrega"),
    BILLETERA_DIGITAL("Billetera Digital", "Pago con billetera electrónica");

    private final String nombre;
    private final String descripcion;

    MetodoPago(String nombre, String descripcion) {
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
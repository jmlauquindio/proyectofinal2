package co.edu.uniquindio.plataformalogistica.model.dto;

/**
 * DTO para la entidad Pago
 * RF-040: Uso de Data Transfer Objects
 */
public class PagoDTO {
    
    private String idPago;
    private String numeroTransaccion;
    private double monto;
    private String fechaPago;
    private String metodoPago;
    private String resultado;
    private String idEnvio;
    private String comprobante;

    // Constructor vacío
    public PagoDTO() {
    }

    // Constructor para listados
    public PagoDTO(String idPago, String numeroTransaccion, double monto, 
                   String fechaPago, String metodoPago, String resultado) {
        this.idPago = idPago;
        this.numeroTransaccion = numeroTransaccion;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.resultado = resultado;
    }

    // Constructor completo
    public PagoDTO(String idPago, String numeroTransaccion, double monto, 
                   String fechaPago, String metodoPago, String resultado, 
                   String idEnvio, String comprobante) {
        this(idPago, numeroTransaccion, monto, fechaPago, metodoPago, resultado);
        this.idEnvio = idEnvio;
        this.comprobante = comprobante;
    }

    // Getters y Setters
    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public String getNumeroTransaccion() {
        return numeroTransaccion;
    }

    public void setNumeroTransaccion(String numeroTransaccion) {
        this.numeroTransaccion = numeroTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    /**
     * Verifica si el pago fue exitoso
     */
    public boolean fueExitoso() {
        return "APROBADO".equals(resultado);
    }

    /**
     * Obtiene representación para tabla
     */
    public String getResumenTabla() {
        return String.format("%s | $%,.0f | %s | %s", 
                           numeroTransaccion, monto, metodoPago, resultado);
    }

    @Override
    public String toString() {
        return String.format("PagoDTO{id='%s', monto=$%,.0f, resultado='%s'}", 
                           idPago, monto, resultado);
    }
}
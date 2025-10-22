package co.edu.uniquindio.plataformalogistica.model;

import co.edu.uniquindio.plataformalogistica.model.enums.MetodoPago;
import co.edu.uniquindio.plataformalogistica.model.enums.ResultadoPago;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Entidad Pago que representa las transacciones financieras de los envíos
 * Según RF-033 a RF-035: Gestión de pagos
 */
public class Pago {
    
    private String idPago;
    private double monto;
    private LocalDateTime fecha;
    private MetodoPago metodoPago;
    private ResultadoPago resultado;
    private String numeroTransaccion;
    private String idEnvio; // Referencia al envío asociado
    private String comprobante;
    private String descripcion;

    // Constructor vacío
    public Pago() {
        this.fecha = LocalDateTime.now();
        this.resultado = ResultadoPago.PENDIENTE;
    }

    // Constructor completo
    public Pago(String idPago, double monto, MetodoPago metodoPago, String idEnvio) {
        this();
        this.idPago = idPago;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.idEnvio = idEnvio;
        this.numeroTransaccion = generarNumeroTransaccion();
    }

    // Getters y Setters
    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public ResultadoPago getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoPago resultado) {
        this.resultado = resultado;
    }

    public String getNumeroTransaccion() {
        return numeroTransaccion;
    }

    public void setNumeroTransaccion(String numeroTransaccion) {
        this.numeroTransaccion = numeroTransaccion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos de negocio

    /**
     * RF-033: Registrar pago - Procesar pago simulado
     */
    public ResultadoPago procesarPago() {
        // Simulación de procesamiento de pago
        // En un sistema real, aquí se integraría con una pasarela de pagos
        
        try {
            Thread.sleep(1000); // Simular tiempo de procesamiento
            
            // Simulación: 90% de probabilidad de éxito
            double probabilidadExito = Math.random();
            
            if (probabilidadExito > 0.1) {
                this.resultado = ResultadoPago.APROBADO;
                this.comprobante = generarComprobante();
                this.descripcion = "Pago procesado exitosamente";
            } else {
                this.resultado = ResultadoPago.RECHAZADO;
                this.descripcion = "Pago rechazado por el banco emisor";
            }
            
        } catch (InterruptedException e) {
            this.resultado = ResultadoPago.ERROR;
            this.descripcion = "Error durante el procesamiento del pago";
        }
        
        return this.resultado;
    }

    /**
     * RF-034: Consultar comprobante de pago
     */
    public String generarComprobantePago() {
        if (resultado != ResultadoPago.APROBADO) {
            return "No disponible - Pago no aprobado";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== COMPROBANTE DE PAGO ===\n");
        sb.append(String.format("ID Pago: %s\n", idPago));
        sb.append(String.format("Número Transacción: %s\n", numeroTransaccion));
        sb.append(String.format("Fecha: %s\n", fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append(String.format("Monto: $%,.0f COP\n", monto));
        sb.append(String.format("Método: %s\n", metodoPago.getNombre()));
        sb.append(String.format("Estado: %s\n", resultado.getNombre()));
        sb.append(String.format("Envío: %s\n", idEnvio));
        sb.append("==========================");
        
        return sb.toString();
    }

    /**
     * Verifica si el pago fue exitoso
     */
    public boolean fueExitoso() {
        return resultado == ResultadoPago.APROBADO;
    }

    /**
     * Verifica si el pago está pendiente
     */
    public boolean estaPendiente() {
        return resultado == ResultadoPago.PENDIENTE;
    }

    /**
     * Genera un número de transacción único
     */
    private String generarNumeroTransaccion() {
        return "TXN" + System.currentTimeMillis() + 
               String.format("%04d", (int)(Math.random() * 10000));
    }

    /**
     * Genera un código de comprobante
     */
    private String generarComprobante() {
        return "CMP" + fecha.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + 
               String.format("%06d", Math.abs(Objects.hash(idPago, numeroTransaccion)) % 1000000);
    }

    /**
     * Obtiene información resumida del pago
     */
    public String getInformacionResumida() {
        return String.format("Pago %s - $%,.0f - %s - %s", 
                           idPago, monto, metodoPago.getNombre(), resultado.getNombre());
    }

    /**
     * RF-035: Verificar si el pago está en el rango de fechas
     */
    public boolean estaEnRango(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pago pago = (Pago) o;
        return Objects.equals(idPago, pago.idPago);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPago);
    }

    @Override
    public String toString() {
        return String.format("Pago %s - $%,.0f (%s)", 
                           idPago, monto, resultado.getNombre());
    }
}
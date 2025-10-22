package co.edu.uniquindio.plataformalogistica.model.dto;

/**
 * DTO para la entidad Tarifa
 * RF-040: Uso de Data Transfer Objects
 */
public class TarifaDTO {
    
    private String idTarifa;
    private double costoBase;
    private double costoPeso;
    private double costoDistancia;
    private double costoVolumen;
    private double costoPrioridad;
    private double costoServicios;
    private double costoTotal;
    private String desglose;

    // Constructor vacío
    public TarifaDTO() {
    }

    // Constructor para cotización
    public TarifaDTO(double costoBase, double costoPeso, double costoDistancia, 
                    double costoVolumen, double costoPrioridad, double costoServicios, 
                    double costoTotal) {
        this.costoBase = costoBase;
        this.costoPeso = costoPeso;
        this.costoDistancia = costoDistancia;
        this.costoVolumen = costoVolumen;
        this.costoPrioridad = costoPrioridad;
        this.costoServicios = costoServicios;
        this.costoTotal = costoTotal;
        this.desglose = generarDesglose();
    }

    // Getters y Setters
    public String getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(String idTarifa) {
        this.idTarifa = idTarifa;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public void setCostoBase(double costoBase) {
        this.costoBase = costoBase;
    }

    public double getCostoPeso() {
        return costoPeso;
    }

    public void setCostoPeso(double costoPeso) {
        this.costoPeso = costoPeso;
    }

    public double getCostoDistancia() {
        return costoDistancia;
    }

    public void setCostoDistancia(double costoDistancia) {
        this.costoDistancia = costoDistancia;
    }

    public double getCostoVolumen() {
        return costoVolumen;
    }

    public void setCostoVolumen(double costoVolumen) {
        this.costoVolumen = costoVolumen;
    }

    public double getCostoPrioridad() {
        return costoPrioridad;
    }

    public void setCostoPrioridad(double costoPrioridad) {
        this.costoPrioridad = costoPrioridad;
    }

    public double getCostoServicios() {
        return costoServicios;
    }

    public void setCostoServicios(double costoServicios) {
        this.costoServicios = costoServicios;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getDesglose() {
        return desglose;
    }

    public void setDesglose(String desglose) {
        this.desglose = desglose;
    }

    /**
     * Genera el desglose detallado de la tarifa
     */
    private String generarDesglose() {
        StringBuilder sb = new StringBuilder();
        sb.append("Desglose de Tarifa:\n");
        sb.append(String.format("• Tarifa base: $%,.0f\n", costoBase));
        sb.append(String.format("• Por peso: $%,.0f\n", costoPeso));
        sb.append(String.format("• Por distancia: $%,.0f\n", costoDistancia));
        sb.append(String.format("• Por volumen: $%,.0f\n", costoVolumen));
        
        if (costoPrioridad > 0) {
            sb.append(String.format("• Recargo prioridad: $%,.0f\n", costoPrioridad));
        }
        
        if (costoServicios > 0) {
            sb.append(String.format("• Servicios adicionales: $%,.0f\n", costoServicios));
        }
        
        sb.append("─────────────────────────\n");
        sb.append(String.format("TOTAL: $%,.0f", costoTotal));
        
        return sb.toString();
    }

    /**
     * Actualiza el desglose después de cambiar valores
     */
    public void actualizarDesglose() {
        this.desglose = generarDesglose();
    }

    @Override
    public String toString() {
        return String.format("TarifaDTO{total=$%,.0f}", costoTotal);
    }
}
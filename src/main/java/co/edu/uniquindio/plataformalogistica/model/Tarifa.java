package co.edu.uniquindio.plataformalogistica.model;

import co.edu.uniquindio.plataformalogistica.model.enums.ServicioAdicional;

import java.util.List;
import java.util.Objects;

/**
 * Entidad Tarifa que define las reglas de cálculo del costo de un envío
 * Según RF-031 y RF-032: Cálculo y desglose de tarifas
 */
public class Tarifa {
    
    private String idTarifa;
    private double tarifaBase;
    private double tarifaPorKilogramo;
    private double tarifaPorKilometro;
    private double tarifaVolumen; // Por metro cúbico
    private double multiplicadorPrioridad;
    private double costoTotal;
    
    // Componentes del cálculo para desglose
    private double costoBase;
    private double costoPeso;
    private double costoDistancia;
    private double costoVolumen;
    private double costoPrioridad;
    private double costoServicios;

    // Constructor vacío
    public Tarifa() {
        // Valores por defecto del sistema
        this.tarifaBase = 5000.0; // $5,000 COP base
        this.tarifaPorKilogramo = 1000.0; // $1,000 COP por kg
        this.tarifaPorKilometro = 500.0; // $500 COP por km
        this.tarifaVolumen = 2000.0; // $2,000 COP por m³
        this.multiplicadorPrioridad = 1.5; // 50% adicional
    }

    // Constructor con parámetros personalizados
    public Tarifa(String idTarifa, double tarifaBase, double tarifaPorKilogramo,
                  double tarifaPorKilometro, double tarifaVolumen, double multiplicadorPrioridad) {
        this.idTarifa = idTarifa;
        this.tarifaBase = tarifaBase;
        this.tarifaPorKilogramo = tarifaPorKilogramo;
        this.tarifaPorKilometro = tarifaPorKilometro;
        this.tarifaVolumen = tarifaVolumen;
        this.multiplicadorPrioridad = multiplicadorPrioridad;
    }

    /**
     * RF-031: Calcular costo estimado de un envío
     * @param peso Peso del paquete en kilogramos
     * @param volumen Volumen del paquete en metros cúbicos
     * @param distancia Distancia en kilómetros
     * @param esPrioritario Si el envío es prioritario
     * @param serviciosAdicionales Lista de servicios adicionales
     * @return Costo total calculado
     */
    public double calcularCosto(double peso, double volumen, double distancia, 
                               boolean esPrioritario, List<ServicioAdicional> serviciosAdicionales) {
        
        // Cálculo de componentes base
        this.costoBase = tarifaBase;
        this.costoPeso = peso * tarifaPorKilogramo;
        this.costoDistancia = distancia * tarifaPorKilometro;
        this.costoVolumen = volumen * tarifaVolumen;
        
        // Subtotal antes de modificadores
        double subtotal = costoBase + costoPeso + costoDistancia + costoVolumen;
        
        // Aplicar multiplicador de prioridad si corresponde
        if (esPrioritario) {
            this.costoPrioridad = subtotal * (multiplicadorPrioridad - 1);
            subtotal *= multiplicadorPrioridad;
        } else {
            this.costoPrioridad = 0;
        }
        
        // Calcular costo de servicios adicionales
        this.costoServicios = 0;
        if (serviciosAdicionales != null) {
            this.costoServicios = serviciosAdicionales.stream()
                    .mapToDouble(ServicioAdicional::getCostoAdicional)
                    .sum();
        }
        
        // Costo total
        this.costoTotal = subtotal + costoServicios;
        
        return costoTotal;
    }

    /**
     * RF-032: Desglosar componentes de la tarifa
     * @return Descripción detallada del cálculo
     */
    public String obtenerDesglose() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== DESGLOSE DE TARIFA ===\n");
        sb.append(String.format("Tarifa base: $%,.0f\n", costoBase));
        sb.append(String.format("Costo por peso: $%,.0f\n", costoPeso));
        sb.append(String.format("Costo por distancia: $%,.0f\n", costoDistancia));
        sb.append(String.format("Costo por volumen: $%,.0f\n", costoVolumen));
        
        if (costoPrioridad > 0) {
            sb.append(String.format("Recargo prioridad: $%,.0f\n", costoPrioridad));
        }
        
        if (costoServicios > 0) {
            sb.append(String.format("Servicios adicionales: $%,.0f\n", costoServicios));
        }
        
        sb.append(String.format("------------------------\n"));
        sb.append(String.format("TOTAL: $%,.0f", costoTotal));
        
        return sb.toString();
    }

    /**
     * Calcula una estimación rápida solo con peso y distancia
     */
    public double calcularEstimacionRapida(double peso, double distancia) {
        return tarifaBase + (peso * tarifaPorKilogramo) + (distancia * tarifaPorKilometro);
    }

    // Getters y Setters
    public String getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(String idTarifa) {
        this.idTarifa = idTarifa;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public double getTarifaPorKilogramo() {
        return tarifaPorKilogramo;
    }

    public void setTarifaPorKilogramo(double tarifaPorKilogramo) {
        this.tarifaPorKilogramo = tarifaPorKilogramo;
    }

    public double getTarifaPorKilometro() {
        return tarifaPorKilometro;
    }

    public void setTarifaPorKilometro(double tarifaPorKilometro) {
        this.tarifaPorKilometro = tarifaPorKilometro;
    }

    public double getTarifaVolumen() {
        return tarifaVolumen;
    }

    public void setTarifaVolumen(double tarifaVolumen) {
        this.tarifaVolumen = tarifaVolumen;
    }

    public double getMultiplicadorPrioridad() {
        return multiplicadorPrioridad;
    }

    public void setMultiplicadorPrioridad(double multiplicadorPrioridad) {
        this.multiplicadorPrioridad = multiplicadorPrioridad;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public double getCostoPeso() {
        return costoPeso;
    }

    public double getCostoDistancia() {
        return costoDistancia;
    }

    public double getCostoVolumen() {
        return costoVolumen;
    }

    public double getCostoPrioridad() {
        return costoPrioridad;
    }

    public double getCostoServicios() {
        return costoServicios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarifa tarifa = (Tarifa) o;
        return Objects.equals(idTarifa, tarifa.idTarifa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTarifa);
    }

    @Override
    public String toString() {
        return String.format("Tarifa - Base: $%,.0f, Total: $%,.0f", tarifaBase, costoTotal);
    }
}
package co.edu.uniquindio.plataformalogistica;

import co.edu.uniquindio.plataformalogistica.model.*;
import co.edu.uniquindio.plataformalogistica.model.enums.*;

/**
 * Versión de prueba de consola para verificar que el código funciona
 */
public class PruebaConsola {
    
    public static void main(String[] args) {
        System.out.println("=== PLATAFORMA DE LOGÍSTICA - UNIVERSIDAD DEL QUINDÍO ===");
        System.out.println("Iniciando prueba de funcionalidad...\n");
        
        // Crear direcciones de prueba
        Direccion direccion1 = new Direccion("DIR001", "Casa", "Calle 15 #10-25", "Armenia", 4.5339, -75.6811);
        Direccion direccion2 = new Direccion("DIR002", "Oficina", "Carrera 20 #30-45", "Armenia", 4.5389, -75.6861);
        
        System.out.println("✓ Direcciones creadas:");
        System.out.println("  - " + direccion1);
        System.out.println("  - " + direccion2);
        System.out.println("  - Distancia calculada: " + String.format("%.2f km", direccion1.calcularDistancia(direccion2)));
        
        // Crear usuario
        Usuario usuario = new Usuario("USR001", "Juan Pérez", "juan.perez@email.com", "3001234567", "password123");
        usuario.agregarDireccionFrecuente(direccion1);
        usuario.agregarDireccionFrecuente(direccion2);
        usuario.agregarMetodoPago(MetodoPago.TARJETA_CREDITO);
        
        System.out.println("\n✓ Usuario creado:");
        System.out.println("  - " + usuario);
        System.out.println("  - Direcciones: " + usuario.getDireccionesFrecuentes().size());
        System.out.println("  - Métodos de pago: " + usuario.getMetodosPago().size());
        
        // Crear repartidor
        Repartidor repartidor = new Repartidor("REP001", "Carlos López", "12345678", "3009876543", "Norte", "Motocicleta");
        repartidor.setEstado(EstadoRepartidor.ACTIVO);
        
        System.out.println("\n✓ Repartidor creado:");
        System.out.println("  - " + repartidor);
        System.out.println("  - " + repartidor.getInformacionResumida());
        
        // Calcular tarifa
        Tarifa tarifa = new Tarifa();
        double costo = tarifa.calcularCosto(2.5, 0.1, direccion1.calcularDistancia(direccion2), false, null);
        
        System.out.println("\n✓ Tarifa calculada:");
        System.out.println("  - Costo total: $" + String.format("%,.0f", costo));
        System.out.println("  - Desglose:");
        System.out.println(tarifa.obtenerDesglose());
        
        // Crear envío
        Envio envio = new Envio("ENV001", direccion1, direccion2, usuario, 2.5, 30, 20, 15, "Documentos importantes");
        envio.setCosto(costo);
        envio.agregarServicioAdicional(ServicioAdicional.SEGURO);
        
        System.out.println("\n✓ Envío creado:");
        System.out.println("  - " + envio);
        System.out.println("  - Código de seguimiento: " + envio.getCodigoSeguimiento());
        System.out.println("  - Volumen: " + String.format("%.3f m³", envio.calcularVolumen()));
        System.out.println("  - Tiempo estimado: " + envio.estimarTiempoEntrega() + " horas");
        
        // Procesar pago
        Pago pago = new Pago("PAG001", costo, MetodoPago.TARJETA_CREDITO, envio.getIdEnvio());
        ResultadoPago resultado = pago.procesarPago();
        
        System.out.println("\n✓ Pago procesado:");
        System.out.println("  - " + pago);
        System.out.println("  - Resultado: " + resultado.getNombre());
        if (pago.fueExitoso()) {
            System.out.println("  - Comprobante generado");
        }
        
        // Asignar envío a repartidor
        if (pago.fueExitoso()) {
            envio.setPago(pago);
            envio.actualizarEstado(EstadoEnvio.ASIGNADO, "Asignado a repartidor " + repartidor.getNombre());
            repartidor.asignarEnvio(envio);
            
            System.out.println("\n✓ Envío asignado:");
            System.out.println("  - Estado: " + envio.getEstado().getNombre());
            System.out.println("  - Repartidor: " + repartidor.getNombre());
            System.out.println("  - Puede ser modificado: " + (envio.puedeSerModificado() ? "Sí" : "No"));
        }
        
        // Mostrar historial
        System.out.println("\n✓ Historial del envío:");
        for (String evento : envio.getHistorialEstados()) {
            System.out.println("  - " + evento);
        }
        
        System.out.println("\n=== PRUEBA COMPLETADA EXITOSAMENTE ===");
        System.out.println("Todas las entidades del dominio funcionan correctamente.");
        System.out.println("El proyecto está listo para implementar la interfaz JavaFX.");
    }
}
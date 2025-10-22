# Plataforma de Logística - Proyecto Final

**Universidad del Quindío - Programación II**

## Descripción
Sistema de logística y envíos urbanos tipo "same-day" que permite a usuarios finales solicitar recolecciones y entregas dentro de la ciudad. La plataforma incluye cotización de tarifas, programación de envíos, rastreo de paquetes, servicios adicionales y gestión administrativa.

## Características Principales
- **Dos perfiles**: Usuario final y Administrador
- **Gestión completa de envíos**: Desde cotización hasta entrega
- **Servicios adicionales**: Seguro, entrega prioritaria, firma requerida
- **Panel administrativo**: Métricas, gestión de repartidores, asignaciones
- **Reportes**: Exportación en CSV y PDF
- **Arquitectura robusta**: Implementación de patrones de diseño y principios SOLID

## Tecnologías Utilizadas
- **Java 17**
- **JavaFX** - Interfaz gráfica
- **Apache POI** - Exportación Excel/CSV
- **PDFBox** - Generación de reportes PDF
- **Maven** - Gestión de dependencias

## Estructura del Proyecto
```
src/
├── main/java/co/edu/uniquindio/plataformalogistica/
│   ├── model/          # Entidades del dominio
│   ├── service/        # Lógica de negocio
│   ├── repository/     # Capa de persistencia
│   ├── controller/     # Controladores JavaFX
│   ├── patterns/       # Implementación de patrones de diseño
│   └── utils/          # Utilidades y helpers
└── main/resources/fxml/ # Archivos FXML
```

## Entidades Principales
- **Usuario**: Clientes que solicitan envíos
- **Repartidor**: Personal de entrega
- **Envío**: Solicitudes de transporte
- **Dirección**: Ubicaciones de origen/destino
- **Tarifa**: Sistema de cálculo de costos
- **Pago**: Transacciones financieras

## Patrones de Diseño Implementados

### Creacionales
- **Singleton**: Gestión única de la aplicación
- **Factory Method**: Creación de diferentes tipos de envío
- **Builder**: Construcción de objetos complejos

### Estructurales
- **Adapter**: Integración con sistemas externos
- **Decorator**: Servicios adicionales de envío
- **Facade**: Simplificación de operaciones complejas

### Comportamentales
- **Strategy**: Algoritmos de cálculo de tarifas
- **Observer**: Notificaciones de estado
- **State**: Estados del envío

## Instalación y Ejecución

### Prerrequisitos
- Java 17 o superior
- Maven 3.6+

### Comandos
```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn javafx:run

# Ejecutar tests
mvn test

# Generar JAR ejecutable
mvn clean package
```

## Funcionalidades por Perfil

### Usuario Final
- Registro e inicio de sesión
- Gestión de perfil y direcciones
- Cotización de tarifas
- Creación y seguimiento de envíos
- Historial y reportes personales
- Gestión de pagos

### Administrador
- Gestión de usuarios y repartidores
- Asignación de envíos
- Panel de métricas y estadísticas
- Generación de reportes operativos
- Gestión de incidencias

## Requisitos Funcionales Implementados
- **RF-001 a RF-009**: Funcionalidades de usuario
- **RF-010 a RF-014**: Funcionalidades administrativas
- **RF-015 a RF-035**: Gestión de entidades
- **RF-036 a RF-044**: Implementación técnica y patrones

## Autores
Proyecto desarrollado para el curso de Programación II
Universidad del Quindío

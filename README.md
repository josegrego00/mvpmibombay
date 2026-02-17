🍽️ MVP Recetas - Sistema de Gestión para Restaurantes con IA
SaaS para restaurantes que integra inventarios, ventas e inteligencia artificial para optimizar la gestión diaria.

🚀 Características
Multi-tenancy: Múltiples restaurantes por subdominio

Gestión completa: Productos, ingredientes, recetas, ventas y compras

IA integrada: Chat en lenguaje natural, recomendaciones y reportes automáticos vía DeepSeek

Modelo Freemium: Gratuito (50 productos, 3 consultas IA/día) o Pro ($9.99/mes)

🛠️ Stack Tecnológico
Backend: Spring Boot 3.2, Spring Security, Spring Data JPA

Frontend: Thymeleaf, Bootstrap 5

BD: MySQL 8.0

IA: Spring AI + DeepSeek API

📊 Modelo de Datos (Resumen)

Empresa 1──< Usuario N
  ├──< Producto N (insumos o platos)
  ├──< Ingrediente N (base para recetas)
  ├──< Receta N (compuesta por ingredientes)
  ├──< Venta N (con detalle de productos)
  ├──< Compra N (con detalle de insumos)
  └──< Cliente N
  
📁 Estructura

src/main/java/com/mibombay/mvprecetas/
├── config/         # Seguridad, DeepSeek
├── controllers/    # Controladores MVC
├── models/         # 11 entidades JPA
├── repositories/   # Acceso a datos
└── services/       # Lógica de negocio + IA

🗓️ Roadmap (11 semanas)

Fase	Semanas	Estado
F1: Fundación	1-3	⏳ En progreso
F2: DeepSeek	4-5	⏳ Pendiente
F3: IA Especializada	6-7	⏳ Pendiente
F4: Freemium	8-9	⏳ Pendiente
F5: Embeddings	10	⏳ Pendiente
F6: Despliegue	11	⏳ Pendiente

💻 Instalación Rápida
bash
# 1. Clonar
git clone https://github.com/josegrego00/mvpmibombay.git

# 2. Configurar BD en application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/mvpmibombay

# 3. Ejecutar
mvn spring-boot:run

🔐 Seguridad
Autenticación Spring Security + BCrypt

Usuarios aislados por empresa (constraints únicas)

Roles: ADMIN, GERENTE, VENDEDOR

📈 Modelo Freemium
Funcionalidad	Gratis	Pro
Productos	50	Ilimitado
Consultas IA/día	3	Ilimitadas
Reportes IA	❌	✅
Publicidad	✅	❌

✅ Estado Actual
Fase 1: ✅ Entidades JPA completadas (11 tablas)
Siguiente: 🔜 Configuración Spring Security + MySQL

⌨️ Desarrollado con ☕ por Jose Pino



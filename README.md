# Escucha tu historia <img align="right" width="50" height="50" alt="logo_escuchatuhistoria_sinfondo" src="https://github.com/user-attachments/assets/33dbb173-0ae9-48a0-ada3-f12336bc73b0" />


<div align="center">

![Dart](https://img.shields.io/badge/Dart-0175C2?style=for-the-badge&logo=dart&logoColor=white)
![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-31648C?style=for-the-badge&logo=postgresql&logoColor=white)
![Supabase](https://img.shields.io/badge/Supabase-3ECF8E?style=for-the-badge&logo=supabase&logoColor=white)

</div>

<div align="center">
  <strong>Backend para aplicación móvil y panel web para descubrir la historia de Martos (Jaén) mediante rutas, monumentos y audios narrados en varios idiomas.</strong>
</div>

Incluye:
 
- Backend en Java Spring Boot  
- Base de datos PostgreSQL alojada en Supabase  
- Almacenamiento de audios e imágenes en Supabase Storage  
- Audios en español (modo normal e infantil) e inglés  
- CRUD completo de monumentos, rutas, noticias, parámetros y estadísticas  

---

# ⚡ 0. ¿En qué consiste?

Escucha tu historia es una plataforma completa diseñada para que cualquier visitante o habitante de Martos pueda descubrir sus monumentos, historia y rutas culturales mediante una experiencia interactiva, accesible y moderna.

> El sistema combina una app móvil en Flutter, un panel de administración web, un backend robusto en Spring Boot y una base de datos en Supabase que almacena tanto la información como los audios e imágenes.

---

## ◻️ Objetivos del proyecto

- Facilitar el turismo cultural en Martos mediante una app intuitiva.  
- Ofrecer audios narrados en español (modo normal e infantil) e inglés.  
- Permitir explorar rutas entre monumentos para conocer la ciudad paso a paso.  
- Proveer un panel de administración para gestionar contenido en tiempo real.  
- Centralizar datos, imágenes y audios en Supabase.  
- Ofrecer estadísticas de uso para mejorar la experiencia del visitante.  

---

## ◻️ ¿A quién ayuda?

- Turistas que visitan Martos y quieren conocer su historia.  
- Familias gracias al modo infantil con audios adaptados.  
- Centros educativos que deseen usar rutas culturales como recurso didáctico.  
- Ayuntamientos y entidades culturales que necesiten gestionar contenido turístico.  
- Desarrolladores que quieran integrar rutas o monumentos en otras apps.  

> La app automatiza la experiencia turística, ofrece accesibilidad lingüística y permite actualizar contenido sin necesidad de publicar nuevas versiones.

---

# 🚀 1. Requisitos previos

- Flutter  
- Java 21+ y Maven  
- Cuenta en Supabase  
- Git  

---

# 📦 2. Instalación y ejecución del proyecto

Clona el repositorio:

```bash
git clone https://github.com/MireyaCueto/Backend_TFG.git
cd escucha-tu-historia
```

## 🔌 Backend (Spring Boot)

```bash
cd backend
mvn spring-boot:run
```

Esto iniciará:  
- Backend → http://localhost:8080   

---

# 🔌 3. Endpoints principales del backend

| Módulo | Método | Endpoint | Descripción |
|-------|--------|----------|-------------|
| Monumentos | GET | /api/v1/public/monuments | Lista todos los monumentos |
|  | GET | /api/v1/public/monuments/{id} | Un monumento por según su id  |
| | POST | /api/v1/admin/monuments | Crea un monumento |
| | PATCH | /api/v1/admin/monuments/{id}/activate | Activa o desactiva un monumento |
| | PUT | /api/v1/admin/monuments/{id} | Actualiza un monumento |
| | DELETE | /api/v1/admin/monuments/{id} | Elimina un monumento |
| Rutas | GET | /api/v1/public/routes | Devuelve rutas disponibles |
|  | GET | /api/v1/public/routes/{id} | Una ruta por según su id  |
| | POST | /api/v1/admin/routes | Crea una ruta |
| | PATCH | /api/v1/admin/routes/{id}/activate | Activa o desactiva una ruta |
| | PUT | /api/v1/admin/routes/{id} | Actualiza una ruta |
| | DELETE | /api/v1/admin/routes/{id} | Elimina una ruta |
| Noticias | GET | /api/v1/public/news | Lista noticias |
|  | GET | /api/v1/public/news/{id} | Una noticia por según su id  |
| | POST | /api/v1/admin/news | Crea una noticia |
| | PUT | /api/v1/admin/news/{id}/publish | Publica una noticia |
| | PUT | /api/v1/admin/news/{id} | Actualiza una noticia |
| | DELETE | /api/v1/admin/news/{id} | Elimina una noticia |
| Control | GET | /api/v1/public/control | Lista del estado de todos los controles |
| | GET | /api/v1/public/control/{name} | Devuelve el estado del control indicado |
| | PUT | /api/v1/admin/control/{name} | Cambia el estado actual de un control |
| Estadísticas | GET | /api/v1/admin/stats/summary | Devuelve un resumen de todas las estadisticas |
| | GET | /api/v1/admin/stats/{serviceName}/daily | Devuelve las estadisticas generadas el dia actual |
| | GET | /api/v1/admin/stats/{serviceName}/monthly | Devuelve las estadisticas generadas el mes actual |
| | GET | /api/v1/admin/stats/{serviceName}/yearly | Devuelve las estadisticas generadas el año actual |
| | GET | /api/v1/admin/stats/new-request | Aumenta en uno la cantidad de peticiones a la IA |
| | GET | /api/v1/admin/stats/fail-request | Aumenta en uno la cantidad de peticiones fallidas a la IA |
| Health | GET | /health | Devuelve el estado actual del servidor |


---

# 📚 4. Estructura del proyecto 

```
escucha-tu-historia/
│
├── src/             → API Spring Boot
│   ├── src/main/java/com/example/monumentos_backend
│   └── src/main/resources/
│       ├── hibernate.cfg.xml
│       └── application.properties
│
└── README.md
```

---

# 🔐 5. Configuración de Supabase

El backend y las apps necesitan conectarse a Supabase para:

- Base de datos PostgreSQL  
- Almacenamiento de audios  
- Almacenamiento de imágenes  

## Variables necesarias en application.properties

```bash
spring.application.name=monumentos_backend

# Fly.io / Docker: escuchar en todas las interfaces (obligatorio para el proxy)
server.address=0.0.0.0
server.port=${PORT:${SERVER_PORT:8080}}

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.maximum-pool-size=5

# Configuraci?n JPA / Hibernate
springdoc.swagger-ui.path=/api/v1/docs
springdoc.api-docs.path=/api/v1/api-docs
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```
> [!WARNING] 
> No compartas la API key pública ni privada.

---

# 🔊 6. Audios disponibles

Los audios se almacenan en Supabase Storage y están disponibles en:

- Español (modo normal)  
- Español (modo infantil)  
- Inglés  

Cada monumento puede tener hasta **tres audios** asociados.

---

# 🎉 7. Listo para usar

Con todo configurado, ya puedes:

- Ejecutar la app móvil  
- Administrar contenido desde la web  
- Gestionar monumentos, rutas, noticias y estadísticas  
- Subir audios e imágenes a Supabase  
- Explorar Martos mediante rutas interactivas  

¡Proyecto listo para funcionar!

Gracias por llegar hasta aquí ;)

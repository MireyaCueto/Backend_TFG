# Escucha tu historia <img align="right" width="50" height="50" alt="logo_escuchatuhistoria_sinfondo" src="https://github.com/user-attachments/assets/33dbb173-0ae9-48a0-ada3-f12336bc73b0" />


<div align="center">

![Flutter](https://img.shields.io/badge/Flutter-02569B?style=for-the-badge&logo=flutter&logoColor=white)
![Dart](https://img.shields.io/badge/Dart-0175C2?style=for-the-badge&logo=dart&logoColor=white)
![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-31648C?style=for-the-badge&logo=postgresql&logoColor=white)
![Supabase](https://img.shields.io/badge/Supabase-3ECF8E?style=for-the-badge&logo=supabase&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![iOS](https://img.shields.io/badge/iOS-000000?style=for-the-badge&logo=apple&logoColor=white)
![Web](https://img.shields.io/badge/Web-4285F4?style=for-the-badge&logo=googlechrome&logoColor=white)

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
- Java 17+ y Maven  
- Cuenta en Supabase  
- Git  

---

# 📦 2. Instalación y ejecución del proyecto

Clona el repositorio:

```bash
git clonehttps://github.com/MireyaCueto/Backend_TFG.git
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

> [!WARNING]
> (poner a posteriori los endpoints finales, estos son placeholders)

| Módulo | Método | Endpoint | Descripción |
|-------|--------|----------|-------------|
| Monumentos | GET | /api/monuments | Lista todos los monumentos |
| | POST | /api/monuments | Crea un monumento |
| | PUT | /api/monuments/{id} | Actualiza un monumento |
| | DELETE | /api/monuments/{id} | Elimina un monumento |
| Rutas | GET | /api/routes | Devuelve rutas disponibles |
| | POST | /api/routes | Crea una ruta |
| Noticias | GET | /api/news | Lista noticias |
| | POST | /api/news | Crea una noticia |
| Parámetros | GET | /api/config | Obtiene parámetros globales |
| | PUT | /api/config | Actualiza parámetros |
| Estadísticas | GET | /api/stats | Devuelve estadísticas de uso |

---

# 📚 4. Estructura del proyecto 

> [!WARNING]
> (poner a posteriori la estructura final, esto es un placeholder)

```
escucha-tu-historia/
│
├── app/                 → App móvil Flutter
│   ├── lib/
│   └── assets/
│
├── admin/               → Panel de administración Flutter Web
│   ├── lib/
│   └── assets/
│
├── backend/             → API Spring Boot
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── application.properties
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

> [!WARNING]
> (poner a posteriori las variables del properties necesarias, estas son placeholders)

```bash
spring.datasource.url=jdbc:postgresql://db.supabase.co:5432/tu_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

supabase.storage.url=https://tu-proyecto.supabase.co/storage/v1/object/public
supabase.storage.bucket=monumentos
supabase.api.key=tu_api_key
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

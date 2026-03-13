# Portfolio Backend API

Este proyecto es una API REST para el blog del portfolio, construida con Spring Boot 3 y Java 17.

## Requisitos

*   Java 17
*   Maven
*   Docker (opcional, para despliegue en contenedor)
*   PostgreSQL

## Configuración y Variables de Entorno

**IMPORTANTE:** Este proyecto utiliza un archivo `.env` para gestionar las variables de entorno sensibles. Nunca subas tu archivo `.env` al repositorio.

1.  Copia el archivo de ejemplo `.env.example` y renómbralo a `.env`:
    ```bash
    cp .env.example .env
    ```
2.  Edita el archivo `.env` con tus credenciales reales:

    ```properties
    # Database Configuration
    DB_URL=jdbc:postgresql://localhost:5432/portfolio_db
    DB_USERNAME=tu_usuario
    DB_PASSWORD=tu_contraseña

    # JWT Configuration
    JWT_SECRET=tu_secreto_super_seguro
    JWT_EXPIRATION=604800000

    # Hibernate Configuration
    DDL_AUTO=update
    ```

## Construcción del proyecto

Para compilar el proyecto y generar el artefacto ejecutable, ejecuta el siguiente comando en la raíz del proyecto:

```bash
mvn clean install
```

Esto descargará las dependencias, ejecutará las pruebas y generará un archivo `.jar` en el directorio `target/`.

## Ejecución

### Ejecución local

Asegúrate de haber configurado tu archivo `.env` correctamente. Luego ejecuta:

```bash
mvn spring-boot:run
```

O ejecuta el jar generado:

```bash
java -jar target/site-0.0.1-SNAPSHOT.jar
```

### Ejecución con Docker

El proyecto incluye un `Dockerfile` y un `docker-compose.yml`.

Para levantar el servicio con Docker Compose (asegúrate de que las variables en `docker-compose.yml` coincidan con lo que necesitas, o pásalas como variables de entorno al ejecutar docker-compose):

```bash
docker-compose up --build
```

## Tecnologías

*   Spring Boot 3
*   Spring Security
*   Spring Data JPA
*   PostgreSQL
*   JWT (JSON Web Tokens)
*   Lombok
*   Dotenv (gestión de variables de entorno)

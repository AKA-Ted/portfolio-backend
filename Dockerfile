# Etapa de construcción (Build Stage)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución (Run Stage)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Variables de entorno por defecto (se sobreescriben al correr el contenedor)
ENV DB_URL=jdbc:postgresql://postgres:5432/portfolio_db
ENV DB_USERNAME=portfolio
ENV DB_PASSWORD=tpLy108p
ENV JWT_SECRET=default_secret_key_change_me_in_production
ENV JWT_EXPIRATION=604800000

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
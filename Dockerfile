# --- Build Stage ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Zuerst pom.xml kopieren und Dependencies laden
COPY pom.xml .
RUN mvn dependency:go-offline

# Dann den vollständigen Quellcode kopieren
COPY src ./src

# Maven Build durchführen (ohne Tests)
RUN mvn clean package -DskipTests

# --- Runtime Stage ---
FROM eclipse-temurin:21-jre
WORKDIR /app

# Das fertige JAR aus der Build Stage übernehmen
COPY --from=build /app/target/book-backend-0.0.1-SNAPSHOT.jar app.jar

# Render setzt den Port über $PORT → an Spring Boot weitergeben
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar app.jar"]

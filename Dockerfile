# Stage 1: Build the application artifact
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests=true

# Stage 2: Create the final, minimal runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar
# Expose the port the application runs on
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
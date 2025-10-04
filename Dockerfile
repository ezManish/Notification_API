# Use an official Maven image to build the project
FROM maven:3.9.8-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy pom.xml and download dependencies first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the jar
COPY src ./src
RUN mvn clean package -DskipTests

# Use a lightweight JDK runtime for the final image
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Set environment variable for Render to use
ENV PORT=8088

# Expose the port
EXPOSE 8088

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

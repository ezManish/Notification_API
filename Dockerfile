# Use a lightweight JDK image
FROM eclipse-temurin:17-jdk-alpine AS build

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN ./mvnw package -DskipTests

# Final runtime image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy JAR file from builder stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Render will auto-detect)
EXPOSE 8088

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

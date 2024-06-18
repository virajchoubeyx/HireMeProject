# Use Maven image with OpenJDK 17
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy the rest of the application code
COPY src ./src

# Package the application
RUN mvn package -DskipTests

# Rename the Spring Boot executable JAR file
RUN mv target/Project1-0.0.1-SNAPSHOT.jar target/HireMee.jar

# Use AdoptOpenJDK's base image for Java 17
FROM adoptopenjdk:17-jdk-hotspot

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage to the new image
COPY --from=build /app/target/HireMee.jar .

# Expose port 8080 (default port for Spring Boot applications)
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "HireMee.jar"]

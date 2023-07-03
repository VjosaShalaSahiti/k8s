# Use a base image with Java installed
FROM adoptopenjdk:11-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/web-1.0-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application is listening on
EXPOSE 4444

# Set the command to run your Spring Boot application when the container starts
CMD ["java", "-jar", , "--server.port=4444", "web-service", "app.jar"]

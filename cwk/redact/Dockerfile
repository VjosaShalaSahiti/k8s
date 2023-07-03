# Use a base image with Java installed
FROM adoptopenjdk:11-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/redact-1.0-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application is listening on
EXPOSE 2222

# Set the command to run your Spring Boot application when the container starts
CMD ["java", "-jar",, "--server.port=2222", "redact-server",  "app.jar"]

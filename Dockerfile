# Use a base image with JDK
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY target/acleda_training_project-0.0.1-SNAPSHOT.jar /app/acleda_training_project-0.0.1-SNAPSHOT.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "acleda_training_project-0.0.1-SNAPSHOT.jar"]

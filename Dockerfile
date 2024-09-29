# Use the official OpenJDK 21 image as the base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory to the container
COPY ./target/kaleidoscope-0.0.1-SNAPSHOT.jar /app/kaleidoscope.jar

# Expose the port that the application listens on
EXPOSE 8080

# Command to run the application when the container starts
CMD ["java", "-jar", "kaleidoscope.jar"]

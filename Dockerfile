# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot JAR file into the container at /app
COPY target/app-onboarding-user-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that the application will listen on
EXPOSE 8080

# Define environment variables for MySQL connection
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql-host:3306/yourdb
ENV SPRING_DATASOURCE_USERNAME=yourdbuser
ENV SPRING_DATASOURCE_PASSWORD=yourdbpassword

# Wait for MySQL to be available before running the application
CMD ["sh", "-c", "while ! nc -z mysql-host 3306; do sleep 1; done; java -jar app.jar"]

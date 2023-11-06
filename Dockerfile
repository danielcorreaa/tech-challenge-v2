FROM eclipse-temurin:17-jdk-alpine 
# Setting up work directory
WORKDIR /app

# Copy the jar file into our app
COPY ./target/tech-challenge-*.jar /app/tech-challenge.jar

# Exposing port 8080
EXPOSE 8080

# Starting the application
CMD ["java", "-jar", "spring-0.0.1-SNAPSHOT.jar"]



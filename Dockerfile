FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ./target/tech-challenge-*.jar /tech-challenge.jar
ENTRYPOINT ["java","-jar","/tech-challenge.jar"]

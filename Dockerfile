FROM eclipse-temurin:17-jdk-jammy as base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src

FROM base as development

FROM base as build
RUN ./mvnw package

FROM eclipse-temurin:17-jre-jammy as production
EXPOSE 8080
COPY --from=build /app/target/tech-challenge-*.jar /tech-challenge.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/tech-challenge.jar"]


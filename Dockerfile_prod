FROM maven:3.8.5-openjdk-17 AS build
LABEL authors="mohamed"
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/
COPY .env .
EXPOSE 8080
CMD ["java", "-jar", "blog-api-0.0.1-SNAPSHOT.jar"]

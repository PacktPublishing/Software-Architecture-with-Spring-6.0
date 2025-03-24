# Stage 1: Build Stage
FROM maven:3.9.7-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

## Stage 2: Runtime Stage
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/bid-services*.jar ./bid-services.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bid-services.jar"]

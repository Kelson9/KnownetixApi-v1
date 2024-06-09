# Stage 1: Build the application
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY . /app
RUN ./mvnw package -DskipTests

# Stage 2: Create the final image
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/Distributed-AI-Driven-multi-agent-system-0.0.1-SNAPSHOT.jar /app/Distributed-AI-Driven-multi-agent-system-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "Distributed-AI-Driven-multi-agent-system-0.0.1-SNAPSHOT.jar"]

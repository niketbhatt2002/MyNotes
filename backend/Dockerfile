FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY . /app
RUN ./gradlew -p backend clean bootJar --no-daemon
EXPOSE 8080
CMD ["java", "-jar", "backend/build/libs/backend-0.0.1.jar"]

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY backend/gradlew backend/gradlew
COPY backend/gradle backend/gradle
COPY backend/build.gradle.kts backend/build.gradle.kts
COPY backend/settings.gradle.kts backend/settings.gradle.kts
COPY backend/src backend/src
RUN chmod +x backend/gradlew
RUN ./backend/gradlew -p backend clean bootJar --no-daemon
EXPOSE 8080
CMD ["java", "-jar", "backend/build/libs/backend-0.0.1.jar"]

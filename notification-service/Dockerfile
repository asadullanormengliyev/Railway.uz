# OpenJDK 17 image'ni ishlatamiz
FROM openjdk:17-jdk-slim

# Ishchi katalogni o‘rnatamiz
WORKDIR /app

# Build qilingan JAR faylni konteynerga nusxalash
COPY build/libs/notification-service-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application-docker.yml /app/application.yml

# Expose port 8761
EXPOSE 8083

# Ilovani ishga tushirish
ENTRYPOINT ["java", "-jar", "app.jar"]

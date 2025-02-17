# OpenJDK 17 image'ni ishlatamiz
FROM openjdk:17-jdk-slim

# Ishchi katalogni o‘rnatamiz
WORKDIR /app

# Build qilingan JAR faylni konteynerga nusxalash
COPY build/libs/eureka-server-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8761
EXPOSE 8761

# Ilovani ishga tushirish
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:17-jre

WORKDIR /app

RUN mkdir -p /var/log/app-logs

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
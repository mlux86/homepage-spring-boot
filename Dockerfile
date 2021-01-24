FROM openjdk:11-jre

COPY homepage.jar /app.jar

ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:sqlite:/db", "-jar", "/app.jar"]


FROM openjdk:11-jre

COPY target/homepage-1.0.0.jar /app.jar

ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:sqlite:/db", "-jar", "/app.jar"]


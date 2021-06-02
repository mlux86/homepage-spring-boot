FROM openjdk:11-jre

COPY homepage.jar /app.jar

ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:sqlite:/db", "-Dgeoip.db=/geo", "-Dlog.file=/log", "-jar", "/app.jar"]


FROM openjdk:11-jdk

RUN apt-get update
RUN apt-get install -y git maven

RUN git clone https://github.com/mlux86/homepage-spring-boot.git homepage
WORKDIR /homepage

RUN ./mvnw package

WORKDIR target

ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:sqlite:/db", "-jar", "homepage-1.0.0.jar"]

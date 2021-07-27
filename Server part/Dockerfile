FROM openjdk:8-alpine
MAINTAINER ralfplaying@yandex.ru
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080 5432
ENTRYPOINT ["java", "-jar", "/app.jar"]

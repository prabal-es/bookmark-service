FROM openjdk:12-alpine

COPY ./target/*-exec.jar /usr/bookmark-service.jar
WORKDIR /usr

CMD ["java", "-jar", "bookmark-service.jar"]

EXPOSE 8080
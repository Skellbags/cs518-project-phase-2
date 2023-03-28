FROM openjdk:17

ARG JAR_FILE=target/motivate-1.0.0.jar

COPY ${JAR_FILE} app.jar

ADD ./data /data

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]

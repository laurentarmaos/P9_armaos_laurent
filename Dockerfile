FROM openjdk:11-jre

ARG JAR_FILE=target/Mediscreen-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ui.jar

EXPOSE 8080

CMD ["java", "-jar", "/ui.jar"]
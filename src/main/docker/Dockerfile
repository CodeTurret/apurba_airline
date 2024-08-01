FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/airline-0.0.1.jar /airline_app.jar
ENTRYPOINT ["java","-jar","/airline_app.jar"]
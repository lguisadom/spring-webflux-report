FROM adoptopenjdk/openjdk11:alpine-slim
ADD target/spring-webflux-report-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app.jar"]
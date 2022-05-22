FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD target/spring_boot_rest_auth-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
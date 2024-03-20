FROM openjdk:17
EXPOSE 8080
ADD target/employee-creator-docker.jar employee-creator-docker.jar
ENTRYPOINT ["java", "-jar", "/employee-creator-docker.jar"]

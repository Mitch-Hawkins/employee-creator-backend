FROM openjdk:23
EXPOSE 8080
ADD ./employee-creator-docker.jar employee-creator-docker.jar
ENTRYPOINT ["java", "-jar", "/employee-creator-docker.jar"]

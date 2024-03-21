FROM openjdk:23
EXPOSE 8080
ADD target/employee-creator-docker.jar employee-creator-docker.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dockerembbed,oauth-security", "-jar", "/employee-creator-docker.jar"]

FROM amazoncorretto:17
LABEL author="bruno-rudy"
WORKDIR /app
COPY  target/task-service-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
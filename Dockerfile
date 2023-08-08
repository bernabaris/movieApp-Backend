FROM openjdk:17

WORKDIR /app

COPY target/movies-0.0.1-SNAPSHOT.jar /app/movies.jar

CMD ["java", "-jar", "/app/movies.jar"]
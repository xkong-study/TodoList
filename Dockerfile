FROM openjdk:17-oracle

WORKDIR /app

COPY target/TodoList-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 7003

CMD ["java", "-jar", "app.jar"]
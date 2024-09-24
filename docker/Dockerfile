#bringing jdk into container
FROM eclipse-temurin:22

#copying .jar file to container
COPY ../target/reviews-make.jar /app/app.jar

#executing .jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]


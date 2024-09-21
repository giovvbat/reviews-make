##bringing maven into container#
#FROM maven:3.8.4-jdk-8 AS build
#
##copying needed source code into container#
#COPY src /app/src
#COPY pom.xml /app
#
##am currently in /app directory#
#WORKDIR /app
#
##running maven#
#RUN mvn clean install
#
##bringing jdk into container#
#FROM openjdk:17-jdk-slim
#
##building .jar file#
#COPY --from=build /app/target/reviews-make.jar /app/app.jar
#
##back to /app directory#
#WORKDIR /app
#
##docker will run into container's 8080 port#
#EXPOSE 8080
#
##execute .jar file through command line#
#CMD ["java", "-jar", "app.jar"]

FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ./target/reviews-make-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
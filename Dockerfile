FROM maven:3.9-eclipse-temurin-22
WORKDIR /usr/local/src
COPY pom.xml .
COPY bootstrap bootstrap
COPY core core
RUN mvn clean install

FROM openjdk:22
COPY --from=0 /usr/local/src/bootstrap/target/bootstrap-*-exec.jar predictice-music-library.jar
CMD ["java", "-jar", "predictice-music-library.jar"]

FROM maven:3.9-eclipse-temurin-22
WORKDIR /usr/local/src
COPY pom.xml .
COPY bootstrap bootstrap
COPY core core
#COPY pom.xml .
RUN mvn clean install

FROM openjdk:22
#VOLUME /tmp
#EXPOSE 8080
#ARG JAR_FILE=/usr/local/src/bootstrap/target/bootstrap-0.0.1-SNAPSHOT-exec.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
#
#WORKDIR /app
#COPY --from=0 /usr/local/src/bootstrap/target/hesperides-*.jar hesperides.jar
COPY --from=0 /usr/local/src/bootstrap/target/bootstrap-*-exec.jar predictice-music-library.jar
CMD ["java", "-jar", "predictice-music-library.jar"]

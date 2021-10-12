#FROM java:8
#VOLUME /tmp
#EXPOSE 10555
#ADD target/customers-0.0.1-SNAPSHOT.jar customers-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/customers-0.0.1-SNAPSHOT.jar"]

FROM openjdk:8-jdk-alpine
EXPOSE 1100
VOLUME /tmp
ARG JAR_FILE=target/customers-0.0.1-SNAPSHOT.jar
ADD sample.db /tmp/sample.db
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

#docker run -it --rm \
#    -p 8080:8080 \
#    -v /path/containing/database:/data \
#    -e SQLITE_DATABASE="db_filename.db" \
#    coleifer/sqlite-web

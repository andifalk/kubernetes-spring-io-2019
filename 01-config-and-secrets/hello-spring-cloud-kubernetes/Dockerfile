#FROM gcr.io/distroless/java:11
FROM openjdk:11-jre-slim
COPY hello-spring-cloud-kubernetes-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN addgroup -g 1002 -S app && adduser -u 1002 -S appuser -G app
USER 1002
#CMD ["/app.jar"]
ENTRYPOINT java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar

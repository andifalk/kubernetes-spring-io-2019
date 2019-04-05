FROM gcr.io/distroless/java:11
COPY demo-app1-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
USER 1002
CMD ["/app.jar"]
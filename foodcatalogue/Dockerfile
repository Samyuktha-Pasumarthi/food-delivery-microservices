FROM eclipse-temurin:25-jdk
WORKDIR /opt
COPY target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
CMD ["sh", "-c", "sleep 15 && java -jar app.jar"]


FROM eclipse-temurin:25-jdk
WORKDIR /opt
COPY target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]


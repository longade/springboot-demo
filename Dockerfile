FROM openjdk:11.0.11-jdk-slim
COPY ./target/*.jar springboot-demo-app.jar
ENV PORT 9090
EXPOSE $PORT
ENTRYPOINT ["java","-jar","-Xmx1024M","-Dserver.port=${PORT}","-Dspring.profiles.active=prod","springboot-demo-app.jar"]
## Docker Build Maven Stage
# FROM maven:3.6.3-openjdk-11 AS build
## Copy folder in docker
# WORKDIR /opt/springboot-demo-app
# COPY ./ /opt/springboot-demo-app
# RUN mvn clean install -DskipTests
# Run spring boot in Docker
FROM openjdk:11.0.11-jdk-slim
WORKDIR /opt/springboot-demo-app
COPY ./ /opt/springboot-demo-app
RUN ls target
COPY /opt/springboot-demo-app/target/*.jar springboot-demo-app.jar
ENV PORT 9090
EXPOSE $PORT
ENTRYPOINT ["java","-jar","-Xmx1024M","-Dserver.port=${PORT}","springboot-demo-app.jar"]
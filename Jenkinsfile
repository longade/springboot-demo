pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }
    stages {
      stage("Clone the project") {
        git branch: 'master', url: 'https://github.com/longade/springboot-demo.git'
      }

      stage("Compilation") {
        sh "mvn clean install -DskipTests"
      }

      stage("Tests and Deployment") {
        stage("Running unit tests") {
          sh "mvn test -Punit"
        }
        stage("Deployment") {
          sh 'nohup mvn spring-boot:run -Dserver.port=9090 &'
        }
      }
  }
}
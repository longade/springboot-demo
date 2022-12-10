pipeline {
    agent any
    tools {
        maven 'maven-3.6.3'
        jdk 'jdk-11'
        docker 'default-docker'
    }
    stages {
        stage('Clone') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'dev', url: 'https://github.com/longade/springboot-demo.git'
            }
        }
        stage('Build') {
            steps {
                // Build the project using maven
                sh "mvn clean install -DskipTests"
            }
        }
        stage('Running unit tests') {
            steps {
                sh "mvn test -Punit"
            }
        }
        stage('Deploy on container') {
            steps {
                sh 'docker build -t longade/springboot-demo-docker .'
            }
        }
        stage('Run from docker container') {
            steps {
                sh 'docker run -d -p 9090:9090 longade/springboot-demo-docker'
            }
        }
    }
}

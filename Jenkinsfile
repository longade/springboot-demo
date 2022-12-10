pipeline {
    agent any
    tools {
        maven 'maven-3.6.3'
        jdk 'jdk-11'
    }
    stages {
        stage('Clone') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'dev', url: 'https://github.com/longade/springboot-demo.git'
            }
        }
        stage('Run permission') {
            steps {
                sh "chmod +x mvnw"
            }
        }
        stage('Build') {
            steps {
                // Build the project using maven
                sh "./mvnw clean install -DskipTests"
            }
        }
        stage('Running unit tests') {
            steps {
                sh "./mvnw test -Punit"
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t longade/springboot-demo-docker .'
            }
        }
        stage('Docker Remove old container') {
            steps {
                sh 'docker rm -f springboot-demo-docker'
            }
        }
        stage('Docker run') {
            steps {
                sh 'docker run -d --name springboot-demo-docker -p 9090:9090 longade/springboot-demo-docker'
            }
        }
    }
}
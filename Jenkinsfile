pipeline {
    agent any
    stages {
        stage('Clone from GitHub') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'dev', url: 'https://github.com/longade/springboot-demo.git'
            }
        }
        stage('Exec permission to mvnw') {
            steps {
                sh "chmod +x mvnw"
            }
        }
        stage('Maven Build') {
            steps {
                // Build the project using maven
                sh "./mvnw clean install -DskipTests"
            }
        }
        stage('Maven Running unit tests') {
            steps {
                sh "./mvnw test -Punit"
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t longade/springboot-demo-docker .'
            }
        }
        stage('Docker remove old container') {
            steps {
                sh 'docker rm -f -v springboot-demo-docker'
            }
        }
        stage('Docker run') {
            steps {
                sh 'docker run -d --name springboot-demo-docker -p 9090:9090 --network=mysql_default longade/springboot-demo-docker'
            }
        }
    }
}
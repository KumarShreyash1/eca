pipeline {
    agent any
   
    stages {
        stage('Checkout') {
            steps {
                echo 'Checkout..'
                // Checkout code from the repository
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'
                // Build your Spring Boot application
                //sh 'mvn clean package'
				mvn clean package
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Docker Build..'
                // Build Docker image using the Dockerfile
                //sh "docker build -t $IMAGE_NAME ."
				docker build -t $IMAGE_NAME .
            }
        }

        stage('Push to ACR') {
            steps {
				echo 'Push to ACR'                
            }
        }

        stage('Deploy to AKS') {
            steps {
				echo 'Deploy to AKS'
            }
        }
    }
}

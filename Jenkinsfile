pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout your code from the repository
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                // Build your Spring Boot application
                sh 'mvn clean package'
            }
        }
        
        stage('Docker Build') {
            steps {
                // Build Docker image using the Dockerfile
                sh 'docker build -t your-image-name .'
            }
        }
        
        stage('Deploy to AKS') {
            steps {
                // Authenticate with AKS
                sh 'az aks get-credentials --resource-group your-resource-group --name your-aks-cluster'
                
                // Deploy the application to AKS
                sh 'kubectl apply -f kubernetes-deployment.yml'
            }
        }
    }
}

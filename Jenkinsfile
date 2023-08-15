pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
				echo 'Checkout..'
                // Checkout your code from the repository
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
				echo 'Building..'
                // Build your Spring Boot application
                sh 'mvn clean package'
            }
        }
        
        stage('Docker Build') {
            steps {
				echo 'Docker Build..'
                // Build Docker image using the Dockerfile
                sh 'docker build -t your-image-name .'
            }
        }
        
        stage('Deploy to AKS') {
            steps {
				echo 'Deploy to AKS..'
                // Authenticate with AKS
                sh 'az aks get-credentials --resource-group your-resource-group --name your-aks-cluster'
                
                // Deploy the application to AKS
                sh 'kubectl apply -f kubernetes-deployment.yml'
            }
        }
    }
}

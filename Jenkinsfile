pipeline {
    agent any
    environment {
        // Define environment variables
        ACR_NAME = 'your-acr-name'
        ACR_RESOURCE_GROUP = 'your-acr-resource-group'
        AKS_CLUSTER_NAME = 'your-aks-cluster-name'
        AKS_RESOURCE_GROUP = 'your-aks-resource-group'
        IMAGE_NAME = 'your-app-image'
    }

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
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Docker Build..'
                // Build Docker image using the Dockerfile
                sh "docker build -t $IMAGE_NAME ."
            }
        }

        stage('Push to ACR') {
            steps {
                script {
                    // Authenticate with ACR
                    withCredentials([azureServicePrincipal('<your-credentials-id>')]) {
                        def acrLogin = azureLogin(servicePrincipalId: '<your-service-principal-id>', servicePrincipalKey: '<your-service-principal-key>', tenantId: '<your-tenant-id>')
                        sh "az acr login --name $ACR_NAME"
                    }

                    // Tag the Docker image with ACR URL
                    sh "docker tag $IMAGE_NAME $ACR_NAME.azurecr.io/$IMAGE_NAME"

                    // Push the Docker image to ACR
                    sh "docker push $ACR_NAME.azurecr.io/$IMAGE_NAME"
                }
            }
        }

        stage('Deploy to AKS') {
            steps {
                // Authenticate with AKS
                withCredentials([azureServicePrincipal('<your-credentials-id>')]) {
                    def aksLogin = azureAKS(credentialsId: '<your-credentials-id>', resourceGroup: AKS_RESOURCE_GROUP, aksName: AKS_CLUSTER_NAME)
                }

                // Set kubectl context to AKS cluster
                sh "az aks get-credentials --resource-group $AKS_RESOURCE_GROUP --name $AKS_CLUSTER_NAME"

                // Deploy the application to AKS using kubectl
                sh "kubectl apply -f kubernetes-deployment.yml"
            }
        }
    }
}

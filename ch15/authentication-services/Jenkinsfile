pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'wxesquevixos/authentication-services'
        DOCKER_TAG = 'latest'
        GITHUB_REPOSITORY_URL = 'https://github.com/wandersonxs/authentication-services.git'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                git branch: 'main', url: "${GITHUB_REPOSITORY_URL}"
            }
        }
        stage('Build') {
            steps {
                echo 'Building the application...'
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                sh """
                docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                """
            }
        }
        stage('Push Docker Image') {
            steps {
                echo 'Pushing Docker image to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh '''
                        echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
                        docker push ${DOCKER_IMAGE}:${DOCKER_TAG}
                    '''
                }
            }
        }
        stage('Deploy to Minikube') {
            steps {
                echo 'Deploying to Minikube...'
                sh """
                kubectl apply -f kubernetes/authentication-services-deployment.yaml
                kubectl apply -f kubernetes/authentication-services-service.yaml
                kubectl apply -f kubernetes/authentication-services-ingress.yaml
                """
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}
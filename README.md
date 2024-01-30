# java-maven-app

This project is a Java application that uses Maven for building, Docker for containerization, Nexus for artifact hosting, Jenkins for continuous integration and deployment. It also involves deploying the application to AWS EKS with Kubernetes.

## Build and Deploy Steps

1. **Maven Build**

    ```bash
    # Run Maven build
    mvn clean install
    ```

2. **Docker Image Build**

    ```bash
    # Build Docker image
    docker build -t java-maven-app .
    ```

3. **Push to Nexus Repository**

    ```bash
    # Push Docker image to Nexus repository
    docker tag java-maven-app nexus-repo-url/java-maven-app:latest
    docker push nexus-repo-url/java-maven-app:latest
    ```

4. **Push to DockerHub**

    ```bash
    # Push Docker image to DockerHub
    docker tag java-maven-app dockerhub-username/java-maven-app:latest
    docker push dockerhub-username/java-maven-app:latest
    ```

5. **Jenkins Pipeline**

    The Jenkins pipeline is defined in the `Jenkinsfile` at the root of the project. Ensure your Jenkins server is configured to use this file.

6. **Kubernetes Deployment on AWS EKS**

    ```bash
    # Apply Kubernetes configurations
    kubectl apply -f kubernetes/
    ```

7. **Local kubectl for Management**

    ```bash
    # Set Kubernetes context to AWS EKS
    aws eks --region region update-kubeconfig --name cluster-name

    # Use kubectl to manage AWS EKS cluster
    kubectl get pods
    kubectl logs pod-name
    # ... other kubectl commands
    ```

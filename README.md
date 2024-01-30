# java-maven-app

This project is a Java application that uses Maven for building, Docker for containerization, Nexus for artifact hosting, and Jenkins for continuous integration and deployment. It also involves deploying the application to AWS EKS with Kubernetes.

## Build and Deploy Steps

### 1. Maven Build

```bash
# Run Maven build
mvn clean install

# Build Docker image
docker build -t java-maven-app .

# Push Docker image to Nexus repository
docker tag java-maven-app nexus-repo-url/java-maven-app:latest
docker push nexus-repo-url/java-maven-app:latest

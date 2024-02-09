# Automated Java Application using Jenkins 

An example project showcasing a Jenkins pipeline for automating the deployment of a Maven Java application to AWS using Docker and Terraform.

## Overview

This project aims to automate the deployment process of a Java application using Jenkins. The Jenkins pipeline includes stages for cloning the GitHub repository, updating the project version, building the Maven Java application, creating a Docker image, pushing the image to Docker Hub, provisioning an AWS server using Terraform modules, pulling the Docker image into the AWS instance, running a setup script, deploying the application using Docker, and committing the new version to the GitHub repository.

## Jenkins Pipeline

### Pipeline Overview

1. **Cloning GitHub Repository:**
   - Jenkins clones the GitHub repository to access the application source code.

2. **Updating Project Version:**
   - The project version is updated using a versioning script or by modifying the `pom.xml` file.

3. **Building Maven Java Application:**
   - Maven is used to build the Java application.

4. **Building Docker Image:**
   - Docker is employed to create an image using the generated JAR file.

5. **Pushing Image to Docker Hub:**
   - The Docker image is pushed to Docker Hub for centralized storage.

6. **Provisioning AWS Server using Terraform:**
   - Terraform modules are utilized to provision an AWS server with the required resources.

7. **Pulling Image into AWS Instance and Running Setup Script:**
   - The Docker image is pulled into the AWS instance, and a setup script configures Docker and Docker Compose.

8. **Deploying Application using Docker Image:**
   - The application is deployed within Docker containers on the AWS instance.

9. **Exposing Application to Browser:**
   - The deployed application is exposed to a specified port for browser access.

10. **Committing New Version to GitHub Repository:**
    - The new version of the application is committed to the GitHub repository.

### Running the Pipeline

To run the Jenkins pipeline, follow these steps:

1. Configure Jenkins with the necessary credentials for GitHub, Docker Hub, and AWS.
2. Create a new pipeline job in Jenkins.
3. Specify the GitHub repository URL and configure the pipeline script using the provided Jenkinsfile.
4. Run the Jenkins job.

### Configuration Files

- `terraform/main.tf`: Terraform configuration for AWS resources.
- `Dockerfile`: Docker configuration for building the application image.
- `scripts/setup.sh`: Setup script for configuring Docker and Docker Compose on the AWS instance.

### Troubleshooting

If you encounter any issues during pipeline execution, check the Jenkins console output for detailed error messages. Ensure that all required credentials and configurations are correctly set.

### Acknowledgments

The project utilizes
- [Terraform](https://www.terraform.io/) for infrastructure provisioning.
- [Docker](https://www.docker.com/) is used for containerization.
- [Jenkins](https://www.jenkins.io/) automates the CI/CD pipeline.

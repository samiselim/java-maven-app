#!/usr/bin/env groovy
// @Library('jenkins-shared-lib')

// without adding this lib globally in jenkins 
library identifier: 'jenkins-shared-lib@main',retriever: modernSCM(
    [$class: 'GitSCMSource',
      remote: 'https://github.com/samiselim/jenkins-shared-lib.git',
      credentialsId: 'sami_githubAcess'
    ]
)
def gv

pipeline {
    agent any
    tools{
        maven 'Maven'
    }
    environment{
        IMAGE_NAME="samiselim/java-maven-app-image"
    }
    stages {
        stage("init") {
            steps {
                script { 
                    echo "****************** Starting loading groovy scripts  **************"
                    gv = load "script.groovy"
                }
            }
        }
        stage("Increment Version"){
            steps{
                script{
                    echo "****************** Starting Incrementing Version  **************"
                    gv.incVersion()
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    echo "****************** Starting Build Jar file using mvn  **************"
                    buildJar()
                }
            }
        } 
        stage("build image") {
            steps {
                script {
                    echo "****************** Starting Build Docker  **************"
                    dockerLogin('sami_docker_hub_credentials')
                    dockerBuildImage('samiselim/java-maven-app-image')
                    dockerPush('samiselim/java-maven-app-image')
                }
            }
        }
        stage("prvisioning server using terraform") {
            environment{
                AWS_ACCESS_KEY_ID = credentials('AWSAccessKeyID')
                AWS_SECRET_ACCESS_KEY = credentials('AWSAccessKeySecret')
                TF_VAR_prefix = 'test'
            }
            steps {
                script {
                    echo "****************** Starting provisioning  **************"
                    gv.provisionServer()
                }
            }
        }
        stage("deploy") {
            environment{
                DOCKER_CRED = credentials('sami_docker_hub_credentials')
            }
            steps {
                script {
                    echo "****************** Starting Deployment  **************"
                    gv.deployApp()
                }
            }
        }
        stage("Commit Version Update") {
            steps {
                script {
                    gv.commitChanges()
                    echo "****************** Starting Adding ,Commiting and pushing Changes to Git hub  **************"
                    githubLogin('java-maven-app-pipeline-CICD' , 'sami_githubAcess')
                    githubAddAllChanges()
                    githubCommitAllChanges('This Commit from jenkins to update version number of the application for the next build')
                    githubPush()
                }
            }
        }
        
    }
}
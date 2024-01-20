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
        IMAGE_NAME='java-maven-app-image'
        REPO_NAME='java-maven-app'
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
                    // gv.buildJar()
                    buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "****************** Starting Build Docker  **************"
                    // gv.buildImage()
                    dockerLogin('sami_docker_hub_credentials')
                    dockerBuildImage(env.IMAGE_NAME)
                    dockerPush(env.IMAGE_NAME)
                }
            }
        }
        stage("deploy") {
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
                    // gv.commitChanges()
                    echo "****************** Starting Adding ,Commiting and pushing Changes to ${env.REPO_NAME}  **************"
                    githubLogin(env.REPO_NAME , 'sami_githubAcess')
                    githubAddAllChanges()
                    githubCommitAllChanges('This Commit from jenkins to update version number of the application for the next build')
                    githubPush()
                }
            }
        }
    }
  
}
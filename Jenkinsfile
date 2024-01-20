#!/usr/bin/env groovy
@Library('jenkins-shared-lib')
def gv

pipeline {
    agent any
    tools{
        maven 'Maven'
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
                    dockerBuildImage('samiselim/java-maven-app-image')
                    dockerPush('samiselim/java-maven-app-image')
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
                    echo "****************** Starting Adding ,Commiting and pushing Changes to Git hub  **************"
                    githubLogin('java-maven-app' , 'sami_githubAcess')
                    githubAddAllChanges()
                    githubCommitAllChanges('This Commit from jenkins to update version number of the application for the next build')
                    githubPush()
                }
            }
        }
    }
  
}
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
                    gv = load "script.groovy"
                }
            }
        }
        stage("Increment Version"){
            steps{
                script{
                    echo "Incrementing Application version "
                    gv.incVersion()
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    echo "building jar"
                    // gv.buildJar()
                    buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building image"
                    // gv.buildImage()
                    buildImage('samiselim/demo-java-maven-app')
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying"
                    gv.deployApp()
                }
            }
        }
        stage("Commit Version Update") {
            steps {
                script {
                    // gv.commitChanges()
                    commitChanges()
                }
            }
        }
    }
  
}
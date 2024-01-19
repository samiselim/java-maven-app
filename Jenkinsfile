def gv

pipeline {
    agent any
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    echo "building jar"
                    //gv.buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building image"
                    //gv.buildImage()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    def dockerCmd = 'docker run -d -p 3080:3080 samiselim/node-app:v1.0'
                    sshagent(['ec2-server-cred']) {
                        sh "ssh -o StrictHostKeyChecking=0 ec2-user@54.93.142.184 ${dockerCmd}"
                    }
                    echo "deploying"
                    //gv.deployApp()
                }
            }
        }
    }   
}
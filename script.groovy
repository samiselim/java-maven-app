def incVersion(){
    echo "Incrementing App Version"
    
    sh 'mvn build-helper:parse-version versions:set \
    -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion}\
    versions:commit'
    
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'  // this code is reguler expression to extract version tag fro xml file 
    def version = matcher[0][1] // to extract the text of version only 
    env.IMAGE_VERSION = "$version"

}
def provisionServer(){
    dir('terraform_config'){
        sh "terraform init"
        sh "terraform apply --auto-approve"
        EC2_PUBLIC_IP = sh(script: "terraform output ec2_public_ip" , returnStdout: true).trim()
    }

}
def deployApp() {
    sleep(time: 90 ,unit: "SECONDS") // to give provisioning its time to complete 
    echo 'deploying the to EC2...'
    echo "EC2_IP = ${EC2_PUBLIC_IP}"
    def shellCmd = "bash ./server-cmds.sh ${env.IMAGE_NAME} ${env.IMAGE_VERSION} ${DOCKER_CRED_USR} ${DOCKER_CRED_PSW}"
    def ec2_instance = "ec2-user@${EC2_PUBLIC_IP}"
    sshagent(['server_ssh_key']) {
        sh "chmod +x server-cmds.sh"
        sh "scp -o StrictHostKeyChecking=no server-cmds.sh ${ec2_instance}:/home/ec2-user"
        sh "scp -o StrictHostKeyChecking=no docker-compose.yaml ${ec2_instance}:/home/ec2-user"
        sh "ssh -o StrictHostKeyChecking=no ${ec2_instance} ${shellCmd}"
    }

} 

return this
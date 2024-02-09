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
    // terraform provision server 
    // 1) create key pair 
    // 2) install terraform in jenkins container 
    // 3) creating terraform configs (terraform init)
}
def deployApp() {
    echo 'deploying the application...'
    def shellCmd = "bash ./server-cmds.sh ${env.IMAGE_NAME} ${env.IMAGE_VERSION}"
    def ec2_instance = "ec2-user@18.159.208.204"
    sshagent(['ssh-cred']) {
        sh "scp server-cmds.sh ${ec2_instance}:/home/ec2-user"
        sh "scp docker-compose.yaml ${ec2_instance}:/home/ec2-user"
        sh "ssh -o StrictHostKeyChecking=no ${ec2_instance} ${shellCmd}"
    }

} 

return this
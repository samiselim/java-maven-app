// def buildJar() {
//     echo "building the application..."
//     sh 'mvn clean package' // to build just one jar file and deleting any jar files before building 
// } 

// def buildImage() {
//     echo "building the docker image..."
//     withCredentials([usernamePassword(credentialsId: 'sami_docker_hub_credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
//         sh "docker build -t samiselim/demo-java-maven-app:${IMAGE_VERSION} ."
//         sh "echo $PASS | docker login -u $USER --password-stdin"
//         sh "docker push samiselim/demo-java-maven-app:${IMAGE_VERSION}"
//     }
// } 
def incVersion(){
    echo "Incrementing App Version"
    
    sh 'mvn build-helper:parse-version versions:set \
    -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion}\
    versions:commit'
    
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'  // this code is reguler expression to extract version tag fro xml file 
    def version = matcher[0][1] // to extract the text of version only 
    env.IMAGE_VERSION = "$version"

}

def deployApp() {
    echo 'deploying the application...'
} 


// def commitChanges(){
//     echo "Committing changes to github repository"
//      withCredentials([usernamePassword(credentialsId: 'sami_githubAcess', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
      
//         sh 'whoami'

//         sh "git remote set-url origin https://${USER}:${PASS}@github.com/samiselim/java-maven-app.git"
//         sh 'git add .'
//         sh 'git commit -m "this commit from jenkins "'
//         sh 'git push origin HEAD:jenkins-update'

//     }
// }

return this
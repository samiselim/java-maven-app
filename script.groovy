def buildJar() {
    echo "building the application..."
    sh 'mvn clean package' // to build just one jar file and deleting any jar files before building 
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'sami_docker_hub_credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t samiselim/demo-java-maven-app:${IMAGE_VERSION} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push samiselim/demo-java-maven-app:${IMAGE_VERSION}"
    }
} 
def incVersion(){
    echo "Incrementing App Version"
    
    sh 'mvn build-helper:parse-version versions:set \
    -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion}\
    versions:commit'
    
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'  // this code is reguler expression to extract version tag fro xml file 
    def version = matcher[0][1] // to extract the text of version only 
    env.IMAGE_VERSION = "$version-$BUILD_NUMBER"

}

def deployApp() {
    echo 'deploying the application...'
} 


def commitChanges(){
    echo "Committing changes to github repository"
     withCredentials([usernamePassword(credentialsId: 'sami_credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
      
        /* Meta Data for jenkins server */
        sh 'git config --global user.email "jenkins@example.com"'
        sh 'git config --global user.name "jenkins"'

        /* some information about our repo */
        sh 'git status'
        sh 'git branch'
        sh 'git config --list'
        // sh 'git branch switch jenkins-update'
        // sh "git remote set-url origin https://${USER}:${PASS}@github.com/samiselim/java-maven-app.git"
        git 'remote set-url origin https://samiselim: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@github.com/samiselim/java-maven-app.git'
        sh 'git add .'
        sh 'git commit -m "this commit from jenkins "'
        sh 'git push origin HEAD:jenkins-update'
    }
}

return this
def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'sami_docker_hub_credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t samiselim/demo-java-maven-app:jma-3.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push samiselim/demo-java-maven-app:jma-3.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 
def fun(newVer){
    echo "Building the application... version ${newVer}"
}
return this
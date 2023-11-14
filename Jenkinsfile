pipeline{
    agent any;
    options {
      buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '5', daysToKeepStr: '', numToKeepStr: '5')
   }
    

    environment{
        DOCKERHUB_CREDENTIALS=credentials('dockerid')
        REGISTRY = "amiraoslt/amiraoueslati_5se3-g2-khaddem"
    }
    


    stages {
         stage('Get project from Git') {
            steps {
                script {
                    git branch: 'AmiraOueslati-5SE3-G2',
                        credentialsId: 'GithubToken',
                        url: 'https://github.com/mehdifersi/5SE3-G2-khaddem.git'
                }
            }
        }
    
         stage('Clean Project') {
            steps {
                sh 'mvn clean  '
            }
         
        }
        
         stage('Unit Test') {
            steps {
              sh 'mvn test '
            }
        }
  
   
        
        
         stage('Artifact construction ') {
            steps {
                sh 'mvn package -DskipTests'
            }
         }
         
         
         
         
         
         stage('Code Quality : SonarQube') {
            steps {
                script {
                    catchError {
                        withSonarQubeEnv(credentials: 'J-sonarQ')
                        {
                        sh "mvn sonar:sonar"
                        }
                    }
                }
            }
        }
        
        
        
         stage('Quality Gate Status'){
                
                steps{
                    
                    script{
                        
                        waitForQualityGate abortPipeline: true;
                        
                    }
                }
            }
         
         
               
         stage('Deploy to Nexus') {
            steps {
              script{
                  
                  def readPomVersion = readMavenPom file: 'pom.xml'
                  
                  def nexusRepo = readPomVersion.version.endsWith("SNAPSHOT") ?"Khaddem-5SE3-G2-Am-S":"Khaddem-5SE3-G2-Am-R"
                  
                  nexusArtifactUploader artifacts: 
                  [
                          [
                          artifactId: 'khaddem',
                          classifier: '', 
                          file: 'target/5SE3-G2-khaddem.jar',
                          type: 'jar'
                          ]
                   ], 
                          credentialsId: 'nexus-auth', 
                          groupId: 'tn.esprit.spring', 
                          nexusUrl: '192.168.47.129:8081', 
                          nexusVersion: 'nexus3', 
                          protocol: 'http', 
                          repository: nexusRepo , 
                          version: "${readPomVersion.version}"
                  
                  
                  
                  
              }

            }
        }
         
         

         stage('Build docker image') {
            steps {
                sh 'docker build -t amiraoslt/amiraoueslati_5se3-g2-khaddem:$BUILD_ID .'
            }
        }
        
        
    

         stage('Push Docker Image') {
            steps {
              script {
               
               withCredentials([string(credentialsId: 'docker_cred', variable: 'dockerhub_cred')]) 
                   {
                   
                   
                   sh 'docker login -u amiraoslt -p ${dockerhub_cred}'
                   sh 'docker push amiraoslt/amiraoueslati_5se3-g2-khaddem:$BUILD_ID'
                   }
               
               
              }
            }
        }
        
 
 
         stage("Starting Docker-compose") {
            steps{
                    sh 'docker-compose up -d'
                }
        }
        

        
}



 post{
        always{
            emailext to: "amira.oslt13@gmail.com",
            subject: "jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
            body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}"
        }
    }








}

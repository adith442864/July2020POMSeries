pipeline {
  agent any
  stages {
    stage('Test Run on Dev') {
      parallel {
        stage('Test Run on Dev') {
          steps {
            bat 'mvn clean install -Denv="dev"'
          }
        }

    stage('Test QA') {
          steps {
            bat 'mvn clean install -Denv="qa"'
          }
        }

    stage('Test Stage') {
          steps {
            bat 'mvn clean install -Denv="stg"'
          }
        }

     stage('Test PROD') {
          steps {
            bat 'mvn clean install'
          }
        } 
     }
    } 
    
    stage(''){
    steps{
    	bat 'echo "test execution is done"'
    	}
     }
     
    }
   } 
   
  tools {
    maven 'M3'
  }
}
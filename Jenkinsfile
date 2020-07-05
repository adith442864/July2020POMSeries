  tools {
    maven 'M3'
  }
  
  pipeline {
  agent any
  stages {
    stage('Build Dev') {
      parallel {
        stage('Build Dev') {
          steps {
            sh 'mvn clean install -Denv ="dev"'
          }
        }

      }
    }

    stage('Build QA') {
      parallel {
        stage('Build QA') {
          steps {
            sh 'mvn clean install -Denv="qa"'
          }
        }

      }
    }
  
  stage('reports') {
    steps {
    script {
            allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: '/allure-results']]
            ])
            
            // publish html
        		publishHTML([
        		allowMissing: false, 
        		alwaysLinkToLastBuild: false, 
        		keepAll: false, 
        		reportDir: 'build', 
        		reportFiles: 'TestExecutionReport.html', 
        		reportName: 'Extent HTML Report',
        		 reportTitles: ''
        		 ])
        		 
   		 }
    }
	
	}
}
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
	            bat 'mvn clean install -Denv="stage"'
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
   		 
    
    stage('Publish reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
    
    

  }
  tools {
    maven 'M3'
  }
}
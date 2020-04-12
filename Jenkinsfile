pipeline {
	agent any
        tools {
                maven 'maven_3.5.2' 
         }
	stages {
		stage('Compile Stage') {
			steps { 
					sh 'mvn clean compile'	
			}
		}
		
		stage('Testing') {
			steps {
					sh 'mvn test'
			}
		}

		stage('Deploy') {
			steps {
				echo 'Deploying code......'
			}
		}
	}
}

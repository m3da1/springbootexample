pipeline {
	agent any

	stages {
		stage('Compile Stage') {
			steps { 
				withMaven(maven: 'maven') {
					sh 'mvn clean compile'	
				}
			}
		}
		
		stage('Testing') {
			steps {
				withMaven(maven: 'maven') {
					sh 'mvn test'
				}
			}
		}

		stage('Deploy') {
			steps {
				echo 'Deploying code......'
			}
		}
	}
}

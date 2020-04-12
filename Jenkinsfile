pipeline {
	agent any

	stages {
		stage('Compile Stage') {
			steps { 
				withMaven(maven: 'maven_3.5.2') {
					sh 'mvn clean compile'	
				}
			}
		}
		
		stage('Testing') {
			steps {
				withMaven(maven: 'maven_3.5.2') {
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

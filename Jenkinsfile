pipeline {
    agent any
 
    stages {
        stage('Test') {
            steps {
                bat "mvn -D clean test"
            }
 
            post {                
               
                success { allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
            }
         }
    }
}      
}

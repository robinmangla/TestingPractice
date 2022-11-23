
pipeline {

  agent any
  tools {

    maven 'TestMaven'
  }

  parameters {
    choice(name: 'Test_Suites', choices: ['testng'], description: 'SelectTestSuitetoExecute')
  }

  stages {

     stage("build") {

       steps {

        echo 'building the application....'
        bat 'mvn clean'

        }
     }

     stage("test") {

       steps {

        echo 'testing the application....'
        bat "mvn test -DsuiteXmlFile=${params.Test_Suites}"
        }      
       post {
                always {
                  junit allowEmptyResults: true, testResults: 'test-output/*.html'
                  }
             }
     }
  }
}


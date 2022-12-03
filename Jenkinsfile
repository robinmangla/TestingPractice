
pipeline {

  agent any
  tools {

    maven 'TestMaven'
  }

  parameters {
    choice(name: 'Test_Suites', choices: ['testng.xml'], description: 'SelectTestSuitetoExecute')
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
                          junit 'target/surefire-reports/junitreports/*.xml'
                        }
        }
       
     }

     stage("Email") {
         steps {
         emailext (to: 'robin.mangla@gmail.com', replyTo: 'robin.mangla@gmail.com', subject: "Email Report from - '${env.JOB_NAME}' ", body: readFile("Reports/AutomationReport_02-12-2022-23-12-52.html"),
         mimeType: 'text/html')
         }
     }
  }
}


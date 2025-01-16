pipeline {
   /* agent {
        docker {
            image: 'maven:3.9.9'
            arg: '-v /root/.m2:/root/.m2'
        }
        */

   agent any

   parameters {
        string name: 'GIT_HTTP_URL', trim: true, defaultValue: 'https://github.com/MungThai/selenium-java.git'
        string name: 'GIT_BRANCH', trim: true, defaultValue: '*/master'
        string name: 'GIT_CRED', trim: true, defaultValue: '1649cbf1-1966-4910-8459-74a11e2ec42e'
   }

   stages {
        stage('Checkout') {
            steps('Git Clone') {
                deleteDir()
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: "$GIT_BRANCH"]],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    submoduleCfg: [],
                    userRemoteConfigs: [
                        [credentialsId: "$GIT_CRED",
                        url: "$GIT_HTTP_URL"]
                    ]
                )
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }


        stage('Test') {
            steps {
                sh 'mvn -D clean test'
            }
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    publishHTML { [
                       allowMissing: false,
                       alwaysLinkToLastBuild: false,
                       keepAll: false,
                       reportDir: 'Reports',
                       reportName: 'Test Automation',
                       reportTitles: 'End-to-End',
                       useWrapperFileDirectory: true
                    ]}
                }
            }
        }
   }
}
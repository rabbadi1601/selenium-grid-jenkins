pipeline {
    agent any

    environment {
      
        ALLURE_RESULTS = 'target\\allure-results'
    }
     tools {
        // Use the Maven tool configured in Jenkins
        maven 'Maven-Local-3.9.12'
    }
    // Trigger on Git push + every 60 minutes
    triggers {
        pollSCM('H * * * *')   // Git polling
        cron('H * * * *')      // Every 60 mins
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10')) // Keep last 10 builds
        timestamps()
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Checking out code from GitHub..."
                git branch: 'main', url: 'https://github.com/rabbadi1601/selenium-grid-jenkins.git'
            }
        }

        stage('Build') {
            steps {
                echo "Building project with Maven..."
                bat "\"%MAVEN_HOME%\\bin\\mvn.cmd\" clean compile"
            }
        }

        stage('Run Tests on Selenium Grid') {
            steps {
                echo "Running TestNG tests on Selenium Grid..."
                bat "\"%MAVEN_HOME%\\bin\\mvn.cmd\" test -DsuiteXmlFile=testng.xml"
            }
        }

       stage('Generate Allure Report') {
    steps {
        echo "Generating Allure Report..."
        allure includeProperties: false, jdk: '', results: [[path: "%ALLURE_RESULTS%"]], reportBuildPolicy: 'ALWAYS'
    }
}
    }

    post {
        always {
            echo "Archiving artifacts and test results..."
            archiveArtifacts artifacts: '**\\target\\*.jar', fingerprint: true
            junit '**\\target\\surefire-reports\\*.xml'
        }
        success {
            echo "Pipeline succeeded  Allure report generated."
        }
        failure {
            echo "Pipeline failed  Check logs and Allure report."
        }
    }
}
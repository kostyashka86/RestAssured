timeout(60) {
    node('maven-slave') {
        stage('Checkout') {
            checkout scm
        }
        stage('Running API tests') {

            def exitCode = sh(
                    returnStatus: true,
                    script: """
                    mvn test
                    """
            )
            if (exitCode == 1) {
                currentBuild.result = 'UNSTABLE'
            }
        }
        stage('reports') {
            allure([
                    includeProperties: false,
                    jdk              : '',
                    properties       : [],
                    reportBuildPolicy: 'ALWAYS',
                    results          : [[path: 'allure-results']]
            ])
        }
    }
}
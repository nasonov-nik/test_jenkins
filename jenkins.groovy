pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }

    stages {
        stage("test") {
            steps{
                sh 'ls -l'
            }
        }
    }
}

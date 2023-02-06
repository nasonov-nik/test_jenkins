#!groovy
pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        timestamp()
    }

    stages {
        stage("test") {
            steps{
                sh 'ls -l'
            }
        }
    }
}

#!groovy
pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        timestamps()
    }

    stages {
        stage("test") {
            steps{
                sh 'ls -l'
            }
        }
    }
}

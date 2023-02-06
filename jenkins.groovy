#!groovy
pipeline {
    agent {
        label 'master'
    }
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

#!/usr/bin/env groovy

pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        timestamps()
    }

    stages {
        stage("Обычный sh") {
            steps {
                sh "ls -l"

            }
        }
        stage("sh с сохранением"){
            steps{
                script {
                    GIT_COMMIT_EMAIL = sh (
                            script: 'ls -l',
                            returnStdout: true
                    ).trim()
                }

            }
        }
    }
}
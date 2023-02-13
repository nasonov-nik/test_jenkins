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
                    remote_host = sh (
                            script: ' ssh osboxes@192.168.59.102 \'hostname\'',
                            returnStdout: true
                    ).trim()
                    println(remote_host)
                }

            }
        }
    }
}
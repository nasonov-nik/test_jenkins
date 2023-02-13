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

        stage("sh с сохранением") {
                steps{
                    withCredentials([sshUserPrivateKey(credentialsId: "yourkeyid", keyFileVariable: 'keyfile')]) {
                        script {
                            remote_host = sh (
                                script: ' ssh -o StrictHostKeyChecking=no -i ${keyfile} osboxes@192.168.59.102 \'hostname\'',
                                returnStdout: true
                            ).trim()
                        println(remote_host)
                    }
                }
            }
        }
    }
}
#!/usr/bin/env groovy

pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        timestamps()
    }
    environment{
        def STAND = ""
        def TASKS = []
    }

    stages {
        stage("Обработка параметров"){
            steps{
                STAND = env.TASK_TYPE
                if (env.change_hostname){
                    TASKS.add("change_hostname")
                }
                if (env.get_hostname){
                    TASKS.add("get_hostname")
                }

                println("тип задачи, ${STAND}")
                println("change_hostname, ${TASKS}")
            }
        }
//        stage("Обычный sh") {
//            steps {
//                sh "ls -l"
//            }
//        }

        stage("sh с сохранением") {
                steps{
                    withCredentials([sshUserPrivateKey(credentialsId: "osboxes", keyFileVariable: 'keyfile')]) {
                        script {
                            remote_host = sh (
                                script: ' ssh -o StrictHostKeyChecking=no -i ${keyfile} root@192.168.59.102 \'hostnamectl set-hostname opensuse\'',
                                returnStdout: true
                            ).trim()
                        println(remote_host)
                    }
                }
            }
        }
    }
}
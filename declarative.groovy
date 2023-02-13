#!/usr/bin/env groovy

pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        timestamps()
    }

    stages {
        stage("Обработка параметров"){
            environment{
                STAND = null
                TASKS = []
            }
            steps{
                env.STAND = env.TASK_TYPE
                if (env.change_hostname == true){
                    env.TASKS.add("change_hostname")
                } else {
                    println("lol")
                }

                if (env.get_hostname == true){
                    TASKS.add("get_hostname")
                } else {
                    println("lol")
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
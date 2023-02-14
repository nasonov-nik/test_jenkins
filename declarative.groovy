#!/usr/bin/env groovy

pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        timestamps()
    }

    stages {
        stage("Обработка параметров"){
            steps{
                script{
//                    def STAND = env.TASK_TYPE
                    def TASKS = []

                    if ( env.change_hostname ){
                        assert env.change_hostname == true
                        TASKS.add("change_hostname")
                    }

                    if ( env.get_hostname ){
                        assert env.get_hostname == true
                        TASKS.add("get_hostname")
                    }

                    if ( env.get_cpu ){
                        TASKS.add("get_cpu")
                    }

                    if ( env.get_mem ){
                        TASKS.add("get_mem")
                    }

                    TASKS.each {
                        print(it)

                    }
                }
            }
        }
//        stage("Обычный sh") {
//            steps {
//                sh "ls -l"
//            }
//        }

//        stage("sh с сохранением") {
//                steps{
//                    withCredentials([sshUserPrivateKey(credentialsId: "osboxes", keyFileVariable: 'keyfile')]) {
//                        script {
//                            remote_host = sh (
//                                script: ' ssh -o StrictHostKeyChecking=no -i ${keyfile} root@192.168.59.102 \'hostnamectl set-hostname opensuse\'',
//                                returnStdout: true
//                            ).trim()
//                        println(remote_host)
//                    }
//                }
//            }
//        }

    }
}
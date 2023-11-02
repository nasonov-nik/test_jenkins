#!/usr/bin/env groovy

pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        timestamps()
    }

    stages {
        stage("Обработка параметров") {
            steps {
                script {
                    new File('/').eachFileRecurse(){
                        line -> println file.getAbsolutePath()
                    }
                    println(pomUrl)
                }
            }
        }
    }
}
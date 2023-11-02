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
                    new File('/').eachFile(){
                        line -> println file.getAbsolutePath()
                    }
                }
            }
        }
    }
}
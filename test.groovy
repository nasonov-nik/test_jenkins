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
                    lol = new File('/').eachFileRecurse()
                    println(lol)
                }
            }
        }
    }
}
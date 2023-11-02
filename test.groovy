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
                    new File('/').eachLine{
                        line -> println "line : $line"
                    }
                    println(pomUrl)
                }
            }
        }
    }
}
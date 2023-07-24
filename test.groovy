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
                    def timeYear = new Date().getYear()
                    def timeMonth = new Date().getMonth()
                    def timeDay = new Date().getDate()
                    println([timeYear, timeMonth, timeDay])
                }
            }
        }
    }
}
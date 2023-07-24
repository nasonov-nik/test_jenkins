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
                    def timeYear = new Date().getYear() + 1900
                    def timeMonth = new Date().getMonth() + 1
                    def timeDay = new Date().getDate()
                    def timeHours = new Date().getHours()
                    def timeMinutes = new Date().getMinutes()
                    def timeSeconds = new Date().getSeconds()
                    def timeMilliseconds = '00+00:00'

                    if (timeMonth < 10){
                        timeMonth = "0${timeMonth}"
                    }
                    println([timeYear, timeMonth, timeDay, timeHours, timeMinutes, timeSeconds, timeMilliseconds])
                }
            }
        }
    }
}
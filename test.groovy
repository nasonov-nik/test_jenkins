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
                    new File("user.dir").eachLine{
                        line -> println "line : $line"
                    }
                    println(pomUrl)
                }
            }
        }
    }
}
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
                    def dirs
                    dirs = sh(script: "find -type d", returnStdout: true)
                    dirs.each {
                        print(it)
                    }
                }
            }
        }
    }
}
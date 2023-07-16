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
                    def dirs = []
                    dirs_sh = sh(script: "find -type d", returnStdout: true).trim()
                    dirs.append(dirs_sh)
                    dirs.each {
                        print(it)
                    }
                }
            }
        }
    }
}
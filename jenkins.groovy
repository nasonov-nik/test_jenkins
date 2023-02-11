#!/usr/bin/env groovy

pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        timestamps()
    }

    stages {
        stage("Обычный sh") {
            steps {
                sh "ls -l"

            }
        }
        stage("sh с сохранением"){
            steps{
                println(env.BRANCH_NAME)
                project_version = sh script: "set +x; ls -l", returnStdOut: true
                println(project_version)

            }
        }
    }
}
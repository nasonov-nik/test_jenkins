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
                    String link = "https://nexus-ci.delta.sbrf.ru/repository/maven-distr/CI04199620/CI04199620_oms-proxy-cib/1.21.17-SNAPSHOT/CI04199620_oms-proxy-cib-1.21.17-20230717.111952-3-distrib.zip"
                    def distrUrl = link.trim().replace('%26','&')
                    println(distrUrl)
                }
            }
        }
    }
}
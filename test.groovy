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

                    if(url ==~ /(?i)(^http(s)?:\/\/.*nexus.*\.(ca|sigma|delta)\.sbrf\.ru(:\d+)?\/.*\.(zip|rar)$)/){
                        urlParties = url.split('/').collect()
                        // Собираем url к pom файлу
                        pomUrl = "${urlParties[0..-2].join('/')}/${urlParties[-3]}-${urlParties[-2]}.pom"
                    }

                    if(url ==~ /(?i)(^http(s)?:\/\/.*nexus.*\.(ca|sigma|delta)\.sbrf\.ru(:\d+)?\/.*service\/local\/artifact\/maven\/.*&.*)/){
                        urlParties = url.split('&').collect()
                        def parameters = [:]
                        def tmp
                        for(part in urlParties[1..-1]){
                            tmp = part.split('=')
                            parameters."${tmp[0]}" = tmp[1]
                        }

                        // Собираем url к pom файлу
                        pomUrl = "${urlParties[0]}&g=${parameters.g}&a=${parameters.a}&v=${parameters.v}&p=pom"
                        println(pomUrl)
                    }
                }
            }
        }
    }
}
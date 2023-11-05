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

//                    String template = readFile(file: 'template.yaml')
//                    def bindingFile = readFile(file: 'env.env')
//                    def binding = ['hosts':[:]]
//
//                    bindingFile.split(',').each { line ->
//                        lineSplit = line.split(':')
//                        binding['hosts'].put(lineSplit[0].trim(),  lineSplit[1].trim())
//                    }
//                    println(binding)


                    def binding = [
                            dockerBuild: {
                                true
                            },
                            version: 'v1',
                            kind: 'Pod',
                            labels:[
                                    type: 'ephemeral-jenkins-agent',
                                    pipeline: 'generic_pipeline'
                            ]
                    ]

                    def template =     '''\
                                        apiVersion: ${version}
                                        kind: <%= kind %>
                                        metadata:
                                          labels:
                                        <% for(label in labels){ %>\
                                            ${label.key}: ${label.value}
                                        <% } %>\
                                        spec:
                                          containers:
                                          - name: alpine
                                            image: alpine:3.12.3
                                            command:
                                            - cat
                                            tty: true
                                        <% if( dockerBuild() ){ %>\
                                          - name: docker
                                            image: docker:18.05-dind
                                            securityContext:
                                              privileged: true
                                            volumeMounts:
                                            - name: dind-storage
                                              mountPath: /var/lib/docker
                                        <% } %>\
                                        <% if( dockerBuild() ){ %>\
                                          volumes:
                                            - name: dind-storage
                                              emptyDir: {}
                                        <% } else { %>\
                                          volumes: {}
                                        <% } %>\
                                            '''

                    def lol = renderTemplate(binding,template)
                    @NonCPS
                    println(lol)

                }
            }
        }
    }
}

//@NonCPS
//def renderTemplate(Map binding, String template){
//    def engine = new groovy.text.SimpleTemplateEngine()
//    def resutl = engine.createTemplate(template).make(binding)
//    println("Вывод внутри функции ${resutl}")
//    return resutl
//}


def renderTemplate(Map binding, String template) {
    return new groovy.text.SimpleTemplateEngine().createTemplate(template).make(binding)
}

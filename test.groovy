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
                    def bindingFile = readFile(file: 'env.env')
                    def binding = [:]

                    bindingFile.split(',').each { line ->
                        lineSplit = line.split(':')
                        binding.put(lineSplit[0].trim(),  lineSplit[1].trim())
                    }
                }
            }
        }
    }
}


//String renderTemplate(Map binding, String template) {
//    return new groovy.text.SimpleTemplateEngine().createTemplate(template).make(binding).toString()
//}
//
//    def binding = [
//            dockerBuild: {
//                true
//            },
//            version: 'v1',
//            kind: 'Pod',
//            labels:[
//                    type: 'ephemeral-jenkins-agent',
//                    pipeline: 'generic_pipeline'
//            ]
//    ]
//
//
//
//println renderTemplate(binding,template)
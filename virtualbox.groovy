node {

    stage("check host") {
        sh "ssh jenkins@192.168.59.102 'pwd && hostname'"
    }
}
node {

    stage("check host") {
        lol = sh(script: "ssh jenkins@192.168.59.102 pwd && hostname", returnStdOut: true)
        println(lol)
    }
}
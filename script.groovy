node {
    stage("check host") {
        lol = sh(script: "ssh osboxes@192.168.59.102 \'hostname\'", returnStdOut: true)
        println(lol)
    }
}
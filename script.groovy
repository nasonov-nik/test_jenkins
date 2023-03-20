node {
    environment {
        test1 = env.test1
        test2 = env.test2
    }
    stage("check host") {
        test1 = test1.split(',').toList()
        println(test1)
        withCredentials([sshUserPrivateKey(credentialsId: "osboxes", keyFileVariable: 'keyfile')]) {
            lol = sh(script: "ssh -o StrictHostKeyChecking=no -i ${keyfile} osboxes@192.168.59.102 \'hostname\'", returnStdout: true)
            println(lol)
        }
    }
}
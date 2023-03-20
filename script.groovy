node {
    environment {
        test1 = env.test1
        test2 = env.test2
    }
    stage("check host") {
        if (test1){
            println("ok")
        }else{
            println("oops")
        }
        withCredentials([sshUserPrivateKey(credentialsId: "osboxes", keyFileVariable: 'keyfile')]) {
            lol = sh(script: "ssh -o StrictHostKeyChecking=no -i ${keyfile} osboxes@192.168.59.102 \'hostname\'", returnStdout: true)
            println(lol)
        }
    }
}
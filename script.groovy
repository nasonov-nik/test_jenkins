node {
    environment {
        test1=readYaml text: env.test1
        test2=readYaml text: env.test2
    }
    stage("check host") {
        println(test1)
        println(test2)
        println(test1.getClass())
        print(test2.getClass())
        test1.each {x ->
            println(x)
        }
        withCredentials([sshUserPrivateKey(credentialsId: "osboxes", keyFileVariable: 'keyfile')]) {
            lol = sh(script: "ssh -o StrictHostKeyChecking=no -i ${keyfile} osboxes@192.168.59.102 \'hostname\'", returnStdout: true)
            println(lol)
        }
    }
}
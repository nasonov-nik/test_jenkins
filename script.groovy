node {
    environment {
        test1 = env.test1
        test2 = env.test2
    }
    stage("check host") {
        test1_list = test1.split(',')
        test2_list = test2.split(',')
        println(test1_list)
        println(test2_list)
        println(test1_list.getClass())
        print(test2_list.getClass())
        withCredentials([sshUserPrivateKey(credentialsId: "osboxes", keyFileVariable: 'keyfile')]) {
            lol = sh(script: "ssh -o StrictHostKeyChecking=no -i ${keyfile} osboxes@192.168.59.102 \'hostname\'", returnStdout: true)
            println(lol)
        }
    }
}
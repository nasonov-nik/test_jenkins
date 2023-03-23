node {
    environment {
        test1 = env.test1
        test2 = env.test2
    }
    stage("check host") {
        steps{
            script{
                sh "touch lol.yaml"
                lol = findFiles(glob '**/*.yaml')
                println(lol)
                withCredentials([sshUserPrivateKey(credentialsId: "osboxes", keyFileVariable: 'keyfile')]) {
                    lol = sh(script: "ssh -o StrictHostKeyChecking=no -i ${keyfile} osboxes@192.168.59.102 \'hostname\'", returnStdout: true)
                    println(lol)
                }
            }
        }
    }
}
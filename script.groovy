//node {
//    stages {
//        stage("check host") {
//            steps {
//                script {
//                    sh "touch lol.yaml"
//                    lol = findFiles(glob: '**/*.yaml')
//                    println(lol)
//                    withCredentials([sshUserPrivateKey(credentialsId: "osboxes", keyFileVariable: 'keyfile')]) {
//                        lol = sh(script: "ssh -o StrictHostKeyChecking=no -i ${keyfile} osboxes@192.168.59.102 \'hostname\'", returnStdout: true)
//                        println(lol)
//                    }
//                }
//            }
//        }
//    }
//}

node {

    stage(find){
        sh "touch lol.yaml"
        lol = findFiles(glob: '**/*.yaml')
        println(lol)
    }
    stage("check host") {
        lol = sh(script: "ssh osboxes@192.168.59.102 \'hostname\'", returnStdOut: true)
        println(lol)
    }
}
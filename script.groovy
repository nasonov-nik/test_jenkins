pipeline {
    agent any
    stages {
        stage("find") {
            steps{
                script {
                    println(env.workspace)
                    def yamlFiles = findFiles(glob: '**/*.y*ml')
                    sh "set +x mkdir -p lol/lol2"
                    sh '''set +x 
                        mkdir -p pop/pop2'''
                    sh "ls -l"
                }
            }
        }
        stage("check host") {
            steps {
                script {
                    withCredentials([sshUserPrivateKey(credentialsId: "osboxes", keyFileVariable: 'keyfile')]) {
                        lol = sh(script: "ssh -o StrictHostKeyChecking=no -i ${keyfile} osboxes@192.168.59.102 \'hostname\'", returnStdout: true)
//                        println(lol)
                    }
                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}

//node {
//    stage("find"){
//        sh "rm  lol.yaml"
//        sh "mkdir -p pop/lol/tot"
//        sh "touch pop/lol/tot/lol.yaml"
//        def lol = findFiles(glob: '**/*.yaml')
//        for (file in lol){
//            println("lol ${file}")
//        }
//    }
//    stage("check host") {
//        lol = sh(script: "ssh osboxes@192.168.59.102 \'hostname\'", returnStdOut: true)
//        println(lol)
//    }
//}
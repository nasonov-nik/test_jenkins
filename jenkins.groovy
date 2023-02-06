#!groovy
pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        timestamps()
    }

    stages {
        stage("test") {
            steps{
               result = sh(returnStdOut: true, script: " set +x; ls -l")
            }
        }
    }
}

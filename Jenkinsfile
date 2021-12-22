pipeline {

    agent any

    stages {
        stage('pullCode') {
            steps {
                echo 'pull code'
                checkout scm
            }
        }

        stage('build') {
            steps {
                echo 'mvn build'
                sh 'mvn clean package'
            }
        }
    }

    post {
        always {
            echo '总是打印'
        }
        success {
            echo '成功'
        }
        failure {
            echo '失败'
        }
        unstable {
            echo '标记为不稳定'
        }
        changed {
            echo '和上次pipeline结果不同时'
        }
    }
}
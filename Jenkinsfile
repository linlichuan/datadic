pipeline {

    agent {
        dockerfile {
            filename 'Dockerfile'
            dir 'provider/zh-service-8103/src'
        }
    }

    environment {
        CUSTOMER_ARG = 'customer_arg'
        HALLO_WORD = 'hallo word'
    }

    stages {
        stage('Build') {
            steps {
                echo CUSTOMER_ARG
                echo HALLO_WORD
            }
        }

        stage('Test') {
            steps {

            }
        }

        stage('Deploy') {
            steps {

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
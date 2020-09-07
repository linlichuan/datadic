pipeline{
    agent{
        docker {
            image 'java:alpine'
        }
    }
    environment{
        CUSTOMER_ARG = 'customer_arg'
    }
    stages{
        stage('build'){
            steps{
                sh 'java -version'
                echo CUSTOMER_ARG
            }
        }
    }
    post{
        always{
            echo '总是打印'
        }
        success{
            echo '成功'
        }
        failure{
            echo '失败'
        }
        unstable{
            echo '标记为不稳定'
        }
        changed{
            echo '和上次pipeline结果不同时'
        }
    }
}
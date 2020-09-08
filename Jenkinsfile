pipeline{
    agent{
        docker {
            image 'java:alpine'
        }
    }
    environment{
        CUSTOMER_ARG = 'customer_arg'
        HALLO_WORD = 'hallo word'
    }
    stages{
        stage('build'){
            steps{
                echo CUSTOMER_ARG
            }
            steps{
                echo HALLO_WORD
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
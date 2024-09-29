pipeline {
    agent any
    tools { go 'go-1.19' }
    ...
}


pipeline {
    ...
    environment {
        COMMIT = "${env.GIT_COMMIT}"
        BRANCH = "${env.GIT_BRANCH}"
    }
    ...
}

pipeline {
    ...
    stages {
        stage('Build') {
            steps {
                dir('src') {
                    sh '''
                        export GOTMPDIR="$JENKINS_HOME/go-cache"
                        mkdir -p $GOTMPDIR
                        go build -o generator main.go
                    '''
                }
            }
        }
        ...
    }
}

pipeline {
    ...
    stages {
        ...
        stage('Test') {
            steps {
                dir('src') { sh 'go fmt *.go' }
            }
        }
        ...
    }
}

pipeline {
    ...
    stages {
        ...
        stage('Generate HTML') {
            steps {
                dir('src') { sh './generator' }
            }
        }
        ...
    }
}

pipeline {
    ...
    stages {
        ...
    }
    post {
        success {
            publishHTML([
                reportDir: 'src',
                reportFiles: 'index.html',
                reportName: 'Dynamic HTML generator',
                allowMissing: false, // Default value
                alwaysLinkToLastBuild: false, // Default value
                keepAll: false, // Default value
            ])
        }
    }
}


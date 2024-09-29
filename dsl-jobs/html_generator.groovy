pipelineJob('html-generator') {
    parameters {
        stringParam('DEPLOYED_BY',
            defaultValue = '',
            description = 'Identifies who generated the latest HTML.'
        )
    }
    
    definition {
        cpsScm {
            scm {
                git {
                    remote { 
                        url('https://github.com/min1907/go-html-renderer.git') // Your Github repo
                        credentials("go-html-renderer") // Credentials ID from the previous step
                    }
                    branches('dynamic-html')
                    scriptPath('Jenkinsfile')
                }
            }
        }
    }
}
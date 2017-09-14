pipeline {
  agent any
  stages {
    stage('Test Build') {
      steps {
        parallel(
          "Test Build": {
            echo 'Hello!'
            sh 'echo "Test"'
            
          },
          "Release Build": {
            echo 'Release hello'
            slackSend(message: 'Test', botUser: true)
            
          }
        )
      }
    }
    stage('Test Test') {
      steps {
        parallel(
          "Test Test": {
            sh '#!/bin/sh'
            
          },
          "Release Test": {
            timeout(time: 5) {
              sh '''#!/bin/bash

echo "Hello world"'''
            }
            
            
          }
        )
      }
    }
  }
}
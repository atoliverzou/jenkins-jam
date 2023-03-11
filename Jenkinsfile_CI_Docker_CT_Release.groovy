pipeline{
  
  agent {
        node {
            label 'master'
        }
    }

  stages {
    stage('check branch R589 ') {
      steps {
        script{
          println(env.GIT_BRANCH)
          sleep(30000)
          if (env.ISDELETED == "true") {
            println "Branch deletion detected. Short-circuiting the build"
            currentBuild.displayName = env.RELEASE_BRANCH + " deletion skipped"
            currentBuild.result = 'SUCCESS'
            return
          }

          if (env.RELEASE_BRANCH != "") {
            targetname = env.RELEASE_BRANCH
          } else {
            targetname = env.GIT_BRANCH.replaceFirst(/.*\//, "")
          }
        }
      }
    }
  }
}  

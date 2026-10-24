def call(String localImage, String repoName, List tags = ['latest']) {
    withCredentials([usernamePassword(credentialsId: 'dockerHubCred',
                                      usernameVariable: 'DH_USER',
                                      passwordVariable: 'DH_PASS')]) {
        sh """
            echo "\$DH_PASS" | docker login -u "\$DH_USER" --password-stdin
        """
        tags.each { tag ->
            sh """
                docker tag ${localImage}:latest "\$DH_USER/${repoName}:${tag}"
                docker push "\$DH_USER/${repoName}:${tag}"
            """
        }
    }
}

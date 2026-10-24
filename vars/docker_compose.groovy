def call(String envCredentialId = 'fastapi-env') {
    withCredentials([file(credentialsId: envCredentialId, variable: 'ENV_FILE')]) {
        sh '''
            cp "$ENV_FILE" .env
            chmod 600 .env
            docker compose down --remove-orphans
            docker compose up -d db
            docker compose run --rm -T api alembic upgrade head
            docker compose up -d
        '''
    }
}

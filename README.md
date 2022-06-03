# keycloak-spring-boot
Keycloak authorization with some extra topics

## Test
1. Enter `mvn clean compile` to compile project
2. Enter `mvn clean test` to run unit tests

## Create Docker Image
`mvn clean compile jib:dockerBuild`

## Run App
1. Enter `docker-compose up` to run all services
2. Go to Keycloak [Local Keycloak](http://localhost:8080/)
3. Enter username: admin Password: admin to login as an administrator
4. Create a new user Username: admin Password: 123456
5. Set Role "app-admin" to admin user
6. Create a new user Username: user Password: 123456
7. Set Role "app-user" to standard user
8. Regenerate spring-boot client secret and keep for OAuth2 requests

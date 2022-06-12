# keycloak-spring-boot

[![Java CI with Maven](https://github.com/oktaykcr/keycloak-spring-boot/actions/workflows/maven.yml/badge.svg)](https://github.com/oktaykcr/keycloak-spring-boot/actions/workflows/maven.yml)

The aim of this project is to show how to implement Keycloak into Spring Boot and how to authenticate/authorize the user. Besides, I implemented basic CRUD logic using best practices (project structure, code quality). The project has also automatic docker image deployment through `jib-maven-plugin`.

## Built Using
- OpenJDK 18
- Spring Boot 2.7.0
  - starter-data-jpa
  - starter-oauth2-resource-server
  - starter-security
  - starter-validation
  - starter-web
- PostgreSQL
- testcontainers (1.17.2)
- mapstruct (1.4.2.Final)
- openapi

## OpenAPI Descriptions

`http://localhost:8081/v3/api-docs/`

## Keycloak Client Configuration
1. Create Realm
![1createRealm](https://user-images.githubusercontent.com/26169464/172070175-0e68c3e4-875c-4820-a456-6074d999db44.png)
2. Create Roles
![2createRoles](https://user-images.githubusercontent.com/26169464/172070198-8fa9a0dc-ea9b-4b49-a91f-6a943ae97f29.png)
3. Create Users
![3createUsers](https://user-images.githubusercontent.com/26169464/172070209-f4c2a9db-5d18-40a5-9aab-1fa09c0611cd.png)
4. Create Spring Boot Client
![4createSpringBootClient](https://user-images.githubusercontent.com/26169464/172070215-a2183e60-91ca-4ec9-b49c-0861fd7a9049.png)
5. Change Access Type
![5changeAccessType](https://user-images.githubusercontent.com/26169464/172070235-a0a3e68d-628e-4d51-970e-ef5fefa73436.png)
6. Get jwks Uri
![6getjwks_uri](https://user-images.githubusercontent.com/26169464/172070260-9021a224-1847-4f18-ac5d-faf2d604efa5.png)

## Postman OAuth2 Configuration
![7PostmanOAUTH2Conf](https://user-images.githubusercontent.com/26169464/172070278-424cdb91-bbcb-4378-803e-a3f1e89d15a7.png)


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

# Integration Tests for DynamoDB with Spring Boot, Testcontainers and LocalStack

## What is it
This project shows how test DynamoDB using Spring Boot, Testcontainers and LocalStack

**Spring Boot** is used not just for testing, but as a main framework of project.

**Testcontainers** allows to do Integration tests by Docker. It builds Docker containers during the tests
and then destroys them in the end of tests. \
In this project, Testcontainers will build a container of LocalStack. \
Prerequisite is having Docker up & running on the machine.

LocalStack is a service that permits to test AWS objects in a local environment (you can test DynamoDB, S3, SQS, etc). \
In this project we will just test DynamoDB.

## Run of the project (the tests)
Start Docker in your machine and move in the root of project. Run: \
`mvnw clean test`


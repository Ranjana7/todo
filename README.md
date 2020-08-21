
# Todo
## Scope
The purpose and scope of this documentation is to provide an overview of the Todo Service along with information like how the functionality was implemented. This document will also list the possible functionality for next version.
## Introduction
Todo-Service app is a multi-user Spring Boot app with a possibility to have a full fledge Angular front end in future. The app allows the user to see a list of existing to-dos 

### Swagger is used for documentation and can be easily used by user for testing via following url while the app is running on localhost:
http://localhost:8080/swagger-ui.html#

Please note, I am using the same in-memory db for testing and the front-end app. However, this can be easily updated by adding a application.properties file in src/test/resources.
Username in DB to be used for Login to Todo App:
TestUser1, 
TestUser2, 
David, 
Amit, 
Ranjana, 
Guest

## What’s Next
Fix Front end bug 
Create Javadoc
Finish Unit and Integrations tests for Todo-Service – both backend and front end
Implement Authentication and Authorization
Role based Access.
Add description to Todo
Create a unique key with description and title of todo rather making title as unique.
Ability to add a user by adding a microservice
Performance Test
Dockerize the Frontend and Backend
Set up CI/CD
Deploy to AWS – for starter we can use s3 bucket.


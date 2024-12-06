# Publisher Packt
## Software Architecture with Spring 6 - A guide to building robust systems with Spring 6

Welcome to the repository for **"Software Architecture with Spring 6"**! This book is a practical guide for software engineers, architects, and developers looking to master modern software architecture principles, especially in the context of Java and Spring Framework. 

## Overview

This book explores the journey of building robust, scalable, and maintainable applications using a structured approach. It covers a wide range of architectural styles and design principles, equipping readers with insights and strategies for architecting resilient systems. From monolithic applications to microservices, this guide walks through key concepts and provides actionable examples to enhance your software architecture skills.

### Sample Code

The book provides sample code for each major chapter, allowing readers to experiment and implement concepts in real-world scenarios. Please check each chapter's directory for related code snippets and explanations.

- ## Chapter 1 : Diving into the software architecture
  The examples provided delve into fundamental architectural concepts, such as high and low coupling, cohesion, and the SOLID principles.
    - ch1:
      - 01-high-coupling-shopping-application
      - 02-low-coupling-shopping-application
      - 03-low-cohesion-usermanagement-application
      - 04-high-cohesion-usermanagement-application
      - 05-solid-srp-library-application
      - 06-open-closed-library-application
      - 07-liskov-substitution-library-application
      - 08-interface-segregation-library-application
      - 09-dependency-inversion-library-application

- ## Chapter 4 : Monolithic Architecture
  
    - ch4:
      - docker/
        - postgresql/
          - init.sql -> SQL DDL and DML
        - .env -> PostgreSQL credentials
        - docker-compose.yml -> Run an image of PostgreSQL and populate it with data.
      - onlineauction/ 
  #### Prerequisites:
      - Java 21
      - Docker and Docker Compose
      - Maven 3.9.9

  ## Instructions: 
  ### To set up environment and run the project:

    1. Go the docker ch4/docker folder
    2. Execute the command: ```docker-compose up -d```
    3. Go to the project's folder: onlineauction
    4. Execute the command: ```mvn clean package```
    5. To run the project execute the command:  ```mvn spring-boot:run```
    6. To connect to the PostgreSQL: 
        - Url: jdbc:postgresql://localhost:5432/auction_db
        - User: auction_app
        - Password: auction123
        - Database: auction_db
    ### Access the application
    1. Access the URL at http://localhost:8080
    2. Click at login menu
    3. Enter with the credencial:
       - Administrator
          - Username: admin
          - Password: test123
       - User
          - Username: user
          - Password: test123

  ## References
    - https://hub.docker.com/_/postgres
    
  
- ## Chapter 5 : Client-Server Architecture
  
    - ch5:
      - docker/
        - postgresql/
          - init.sql -> SQL DDL and DML
        - .env -> PostgreSQL credentials
        - docker-compose.yml -> Run an image of PostgreSQL and populate it with data.
      - onlineauction/ -> Server application
      - onlineauction-client-mobile -> Mobile application (Client)
      - onlineauction-client-web -> Web application (Client)

  #### Prerequisites:
      - Java 21
      - Docker and Docker Compose
      - Maven 3.9.9
      - Node.js and npm
      - React
      - React Native
      - Expo Go App


  ## Instructions: 
  ### To set up environment and run the project (Server):

    1. Go the docker ch5/docker folder
    2. Execute the command: ```docker-compose up -d```
    3. Go to the project's folder: onlineauction
    4. Execute the command: ```mvn clean package```
    5. To run the project execute the command:  ```mvn spring-boot:run```
    6. To connect to the PostgreSQL: 
        - Url: jdbc:postgresql://localhost:5432/auction_db
        - User: auction_app
        - Password: auction123
        - Database: auction_db
    7. OpenAPI documentation can be accessed at http://localhost:8080/swagger-ui/index.html.

    ### To set up environment and run the project (Web Client):
    1. Go to the project's folder: onlineauctiononlineauction-client-web
    2. Execute the command: ```npm install```
    3. Execute the command: ```npm start  ```
    4. Access the URL at http://localhost:3000/
    5. Enter with the credencial:
       - Administrator
          - Username: admin
          - Password: test123
       - User
          - Username: user
          - Password: test123

    ### To set up environment and run the project (Mobile Client):
    1. Go to the project's folder: onlineauctiononlineauction-client-mobile
    2. Execute the command: ```npm install -g expo-cli```
    3. Execute the command: ```npm install```
    4. Execute the command: ```expo doctor --fix-dependencies```
    5. Execute the command: ```npx expo-doctor```
    6. Execute the command: ```npx expo install --check```
    7. Execute the command: ```npm install expo@latest```
    8. Execute the command: ```npx expo install @expo/metro-runtime```
    9. Execute the command: ```npx expo start```
    10. To see the application in the web browser, press w
    11. To see the application on the Expo Go app, install Expo Go on your mobile device and scan the QR code.
    12. Enter with the credencial:
        - Administrator
          - Username: admin
          - Password: test123
        - User
          - Username: user
          - Password: test123
    
   PS: Some commands may not be necessary, but following these steps will allow you to run the application and view it through Expo Go. 
   You may update the dependencies with the command ``` expo install <dependency> ```.
    In this case, nothing is better than asking our friend to chat GTP or something else to provide us with the command. ;)
   
- ## Chapter 6 : Microservices Architecture
  
    - ch6:
      - docker/
        - postgresql/
          - init.sql -> SQL DDL and DML
        - mongo-init/
          - init.js -> Create database, collection and insert data.
        - .env -> PostgreSQL and MongoDB credentials
        - docker-compose.yml -> Run an image of PostgreSQL and MongoDB, populate them with data, and run all microservices.
      - docker-resources/
        - postgresql/
          - init.sql -> SQL DDL and DML
        - mongo-init/
          - init.js -> Create database, collection and insert data.
        - .env -> PostgreSQL and MongoDB credentials
        - docker-compose.yml -> Create the databases PostgreSQL and MongoDB and populate them with data. This is useful to run the code in an IDE.
      - postman
        - ch6.postman_collection.json -> Postman collection for chapter 6 to request the services.
      - authentication-services -> Code to authentication services.
      - user-services -> Code to authentication services.
      - product-services -> Code to authentication services.

  #### Prerequisites:
      - Java 21
      - Docker and Docker Compose
      - Maven 3.9.9


  ## Instructions: 
  ### To set up environment and run the project (Run the services without code):

    1. Go the docker ch6/docker folder
    2. Execute the command: ```docker-compose up -d```
    3. Go to the project's folder: postman and import the collection
    4. Now, execute the request for the desired service.
    5. To connect to the PostgreSQL: 
        - Url user database: jdbc:postgresql://localhost:5432/user_db
        - Url product database: jdbc:postgresql://localhost:5432/product_db
        - User: auction_app
        - Password: auction123
    6. To connect to the MongoDB: mongodb://auction_app:auction123@localhost:27017/
    authentication_db?authSource=admin
        - User: auction_app
        - Password: auction123

  ### To set up environment and run the project (Run the services via IDE):

    1. Go the docker ch6/docker-resources folder
    2. Execute the command: ```docker-compose up -d```
    3. Open the microservices into your favorite IDE 
    4. For each microservices execute the command: ```mvn clean package``` and then  ```mvn spring-boot:run```    
    5. Go to the project's folder: postman and import the collection
    6. Now, execute the request for the desired service.
    5. To connect to the PostgreSQL: 
        - Url user database: jdbc:postgresql://localhost:5432/user_db
        - Url product database: jdbc:postgresql://localhost:5432/product_db
        - User: auction_app
        - Password: auction123
    6. To connect to the MongoDB: mongodb://auction_app:auction123@localhost:27017/
    authentication_db?authSource=admin
        - User: auction_app
        - Password: auction123

- ## Chapter 7 : Microservices Patterns with Spring Cloud
  ### ALERT ###
  Run the services in the order they appear for each folder. Generally, it is :
   1. configuration-services
   2. service-discovery-services
   3. gateway-services
   4. Other services such as authentication, user, and product services.

   ### ALERT 2 ###
   Please wait up to 30 seconds after starting the services before making requests. Eureka requires this time to register all services and synchronize its registry due to default heartbeat and cache update intervals. Reducing this delay is possible but expected in the default configuration.

   ## Chapter 7 - Folder's structure 
    - ch7:
      - 01-Service-Discovery/ -> Section's code: Discovering and registering services with Eureka 
        - docker/
          - postgresql/
            - init.sql -> SQL DDL and DML
          - mongo-init/
            - init.js -> Create database, collection and insert data.
          - .env -> PostgreSQL and MongoDB credentials
          - docker-compose.yml -> Run an image of PostgreSQL and MongoDB, populate them with data, and run all microservices.
        - service-discovery-services -> Service Discovery Services (Eureka Server)
        - authentication-services -> Code to authentication services.
        - user-services -> Code to authentication services.
        - product-services -> Code to authentication services.
      - docker-resources/
        - postgresql/
          - init.sql -> SQL DDL and DML
        - mongo-init/
          - init.js -> Create database, collection and insert data.
        - .env -> PostgreSQL and MongoDB credentials
        - docker-compose.yml -> Create the databases PostgreSQL and MongoDB and populate them with data. This is useful to run the code in an IDE.
      - postman
        - ch7.postman_collection.json -> Postman collection for chapter 7 to request the services.      

  #### Prerequisites:
      - Java 21
      - Docker and Docker Compose
      - Maven 3.9.9

  ## Instructions: 
  ### To set up environment and run the project (Run the services without code):

    1. Go the docker ch7/[01-Service-Discovery | 02-Load-Balancer | 03-Gateway | 04-Configuration-Server - 05-Resiliency]/docker folder
    2. Execute the command: ```docker-compose up -d```
    3. Go to the project's folder: postman and import the collection
    4. Now, execute the request for the desired service.
    5. To connect to the PostgreSQL: 
        - Url user database: jdbc:postgresql://localhost:5432/user_db
        - Url product database: jdbc:postgresql://localhost:5432/product_db
        - User: auction_app
        - Password: auction123
    6. To connect to the MongoDB: mongodb://auction_app:auction123@localhost:27017/
    authentication_db?authSource=admin
        - User: auction_app
        - Password: auction123

  ### To set up environment and run the project (Run the services via IDE):

    1. Go the docker ch7/docker-resources folder
    2. Execute the command: ```docker-compose up -d```
    3. Open the microservices into your favorite IDE 
       * RUN THE SERVICES IN THE FOLLOWING ORDER: 
         * Configuration Services
         * Discovery Services
         * Gateway Services
    4. Go to ch7/[01-Service-Discovery | 02-Load-Balancer | 03-Gateway | 04-Configuration-Server - 05-Resiliency] folder and for each microservices execute the command: ```mvn clean package``` and then  ```mvn spring-boot:run``` 
    5. Go to the project's folder: postman and import the collection
    6. Now, execute the request for the desired service.
    5. To connect to the PostgreSQL: 
        - Url user database: jdbc:postgresql://localhost:5432/user_db
        - Url product database: jdbc:postgresql://localhost:5432/product_db
        - User: auction_app
        - Password: auction123
    6. To connect to the MongoDB: mongodb://auction_app:auction123@localhost:27017/
    authentication_db?authSource=admin
        - User: auction_app
        - Password: auction123

  ## References
    - https://hub.docker.com/_/postgres
    - https://hub.docker.com/_/mongo

    
### Who This Book is For

- Software architects looking to deepen their knowledge of Spring-based architecture.
- Developers and engineers involved in designing, implementing, or evolving software systems.
- Technical leads aiming to improve scalability, performance, and security in distributed systems.


## About the Author

The author, Wanderson Xesquevixos, is a seasoned software engineer and architect with over 20 years of experience in Java, Spring, and cloud-native technologies. Wanderson brings practical insights from extensive experience across diverse projects in microservices, cloud computing, and software design.

## Feedback and Contributions

Your feedback is invaluable! Feel free to contact us about issues, suggest improvements, or share your thoughts. This repository will be updated with any errata and additional resources to accompany the book.


**Thank you for embarking on this journey in software architecture! Happy coding and architecting!**


# Sky Aviator Agency.

## Overview:

![Overview](System-Design/Sky%20Aviator%20-%20System%20Design%20-%20V1.2.jpg)

## Prerequisites:

- JDK 20
- Spring Boot 3.X.X
- Maven 3.X.X

## Technology stack:

- Java `20`
- Spring Boot `3.X.X`
- Spring Data JPA `3.1.0`
- Sonar Lint `5.1`
- Docker
- MySQL `8.0.33`
- PostgreSQL `15.0`
- MongoDB `6.0`
- Redis `6.0`
- ATP Oracle
- JWT (JSON Web Tokens) `0.11.5`
- Spring Security `6.1.0`
- Spring Cloud Discovery Service `4.0.2`
- Spring Cloud Gateway `4.0.6`
- Spring Cloud LoadBalancer
- Swagger `3.0.3`

## Microservices:

| Microservice   | PORT |
| -------------- | ---- |
| config-server  | 9090 |
| api-gateway    | 9091 |
| eureka-service | 9092 |
| ms-users       | 9093 |
| ms-booking     | 9094 |

## Database:

| Service | Host      | Port  |
| ------- | --------- | ----- |
| Redis   | 127.0.0.1 | 6379  |
| MySQL   | localhost | 3306  |
| MongoDB | localhost | 27017 |

## Spring Cloud API Gateway:

An API Gateway is a centralized entry point for all API calls in a microservices architecture. It acts as a reverse proxy that receives requests from clients, routes them to the appropriate services, and manages the communication between clients and microservices.

The API Gateway provides several important functionalities, such as authentication, authorization, load balancing, caching, data transformation, routing, and more. It helps simplify the microservices architecture by providing a single entry point for APIs and centralizing common API management logic.

Additionally, the API Gateway also acts as a security layer, protecting the underlying microservices by exposing only authorized APIs and validating client requests.

>URI: http://localhost:9091

Register the microservices.

The route to "ms-users" microservice for example:

>http://localhost:9091/ms-users/api/v1/users

This should return all registered users.

## Discovery Service:

Eureka-service is our Discovery Service.

A Discovery Service is a fundamental component in a microservices architecture. It plays the role of registering and tracking information about the available services in the infrastructure. The Discovery Service allows microservices to automatically register themselves and provide details about their location, such as IP address, port, version, etc. It also enables other services to dynamically find and access the required services without needing to know the specific location information of each service.

A famous Discovery Service is Eureka, provided by Spring Cloud Netflix. It allows services to register and discover each other using logical names, making scalability and resilience easier to achieve in a microservices architecture.

>URI: http://localhost:9092/eureka

## Ms-Users:

"ms-users" is our user microservice;

>URI: http://localhost:9093

### - Endpoints Management:

>URI: http://localhost:9093/swagger-ui/index.html


![Overview](docs/Swagger%20V3.png)

    • MS Reservas

| HTTP METHOD | PATCH                                                   | DESCRIPTION                                             |
| ----------- | ------------------------------------------------------- | ------------------------------------------------------- |
| POST        | /api/v1/booking                                         | (Realizar reserva)                                      |
| PUT         | /api/v1/booking                                         |                                                         |
| PAT         | /api/v1/booking                                         |                                                         |
| GET         | /api/v1/booking                                         | (Obter todos as reservas, disponíveis ou indisponíveis) |
| GET         | /api/v1/booking/available                               | (Obter todas as reservas disponíveis)                   |
| GET         | /api/v1/booking/available/{Id}                          | (Obter todas as reservas disponíveis por Id)            |
| GET         | /api/v1/booking/reserved                                | (Obter todas as reservas indisponíveis)                 |
| GET         | /api/v1/booking/reserved/{Id}                           | (Obter todas as reservas indisponíveis por Id)          |
| GET         | /api/v1/booking/{city}                                  | (Obter Reserva com parâmetros)                          |
| GET         | /api/v1/booking/available/{city}?{lowerDate=2023-06-27} | (Obter Reserva por menor data - Obrigatório)            |
| GET         | /api/v1/booking/available/{city}?{upperDate=2023-09-30} | (Obter Reserva por maior data - Opcional)               |
| GET         | /api/v1/booking/available/{city}?{lowerHour=HH-MM-ss}   | (Obter Reserva por menor hora - Opcional)               |
| GET         | /api/v1/booking/available/{city}?{upperHour=HH-MM-ss}   | (Obter Reserva por maior hora - Opcional)               |
| DEL         | /api/v1/booking/{Id}                                    | (Deletar Reserva)                                       |
 
    • MS Aeroporto (Acesso restrito - Gerenciamento do aeroporto)

| HTTP METHOD | PATCH                                | DESCRIPTION |
| ----------- | ------------------------------------ | ----------- |
| POST        | /api/v1/airports                     |             |
| PUT         | /api/v1/airports                     |             |
| PAT         | /api/v1/airports                     |             |
| GET         | /api/v1/airports                     |             |
| GET         | /api/v1/airports/{Id}                |             |
| GET         | /api/v1/airports/{countries}         |             |
| GET         | /api/v1/airports/{city}              |             |
| GET         | /api/v1/airports/{city}?{lag}?{long} |             |
| GET         | /api/v1/airports/{lag}/{long}        |             |
| DEL         | /api/v1/airports/{Id}                |             |
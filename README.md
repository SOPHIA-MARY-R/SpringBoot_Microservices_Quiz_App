# Quiz App
- Quiz Application to reinforce understanding in SpringBoot and Microservices
- This repository contains a simple Monolithic Quiz application (a personal project)
- The same application has been transformed into a Microservices architectured application

## Microservices (The core implementation):
1. [Question Service](https://github.com/SOPHIA-MARY-R/SpringBoot_Microservices_Quiz_App_Question_Service)
2. [Quiz Service](https://github.com/SOPHIA-MARY-R/SpringBoot_Microservices_Quiz_App_Quiz_Service)

## Service Registry
- For microservices to register themselves and discover other microservices for communication
- SpringBoot provides **Eureka Server** dependency which acts as the Service Registry
- Microservices can register themselves as Eureka clients in the registry with the help of SpringBoot dependency **Eureka Discovery Client**
- Microservices communicate easily among themselves via REST APIs using the **Feign Client** (SpringBoot dependency)
- [Service Registry](https://github.com/SOPHIA-MARY-R/SpringBoot_Microservices_Quiz_App_Service_Registry)

## API Gateway
- While there are advantages over Microservices, we end up addressing the cross-cutting concerns in each service which results in redundance.
- To overcome this we have a gate through which all client requests will enter the application.
- Because of this Gateway, our application behaves like a single(monolithic) application on the perspective of the client, but internally we have multiple microservices coordinating and handling the request.
- All the cross cutting concerns can be handled in the API Gateway avoiding redundance.
- [API Gateway](https://github.com/SOPHIA-MARY-R/SpringBoot_Microservices_Quiz_App_API_Gateway)

## Circuit Breaker (Exploration)
- When we have interdependent microservices, we have a concern that if a microservice fails, then the dependent service would also crash.
- So we install a circuit breaker in our microservices network to improve fault tolerance and reliability which acts like a connection breaker (as the name suggests) by delinking the connection between the 2 microservices if one fails.

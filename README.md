# Wholesale Microservice
- This is a test project for microservice implementations

## What we used in this
  - Eureka  ```as discovery server```
  - Spring server cloud
  - Gateway  ```difining the routing for microservices in gateway```
  - Feign Client
  - resilience4j ```circuit breakers.```

# Eureka


The term "Eureka discovery" in the context of **Spring Boot** and **microservices** architecture refers to a specific technical component and its crucial role in managing distributed applications. It's not a sudden "aha!" moment for a developer, but rather a system's mechanism for services to find each other.

---

## Eureka Discovery Server in Spring Boot: Definition and Use

In a **microservices architecture**, applications are broken down into smaller, independent services. For these services to communicate with each other, they need a way to **find** each other on the network. This is where a **service discovery mechanism** comes in, and **Netflix Eureka**, integrated with **Spring Cloud**, is a popular choice for this.

---

### Definition

A **Eureka Discovery Server** (often simply called a "Eureka Server" or "Discovery Server") is a **centralized service registry** that holds information about all the running **microservice instances** within a distributed system.

* **Service Registry:** It acts like a phone book or directory for your microservices.
* **Centralized:** All services register with and query this single point.
* **Information:** It stores details such as the service's name, IP address, port number, and health status.

---

### How it Works (Use)

The interaction between the Eureka Discovery Server and your microservices happens in two main ways:

1.  **Service Registration (Eureka Client):**
    * When a microservice (acting as an "Eureka Client") starts up, it **registers itself** with the Eureka Server.
    * It sends its details (like its name and network location) to the Eureka Server.
    * It continuously sends **heartbeats** to the Eureka Server to indicate that it's still alive and healthy. If heartbeats stop for a configured period, the Eureka Server will unregister the instance.
    * In Spring Boot, you enable a service as an Eureka client by adding the `spring-cloud-starter-netflix-eureka-client` dependency and annotating your main application class with `@EnableDiscoveryClient` (or `@EnableEurekaClient` in older Spring Cloud versions).

2.  **Service Discovery (Eureka Client):**
    * When one microservice needs to communicate with another microservice (e.g., Service A needs to call Service B), it doesn't need to know Service B's exact IP address and port beforehand.
    * Instead, Service A **queries the Eureka Server** using Service B's logical name (e.g., "order-service").
    * The Eureka Server returns the network locations (IPs and ports) of all available instances of "order-service."
    * Service A can then use a client-side load balancer (like Spring Cloud LoadBalancer, formerly Ribbon) to choose an instance and send the request.
    * This dynamic lookup prevents hardcoding network locations and makes the system more resilient to changes (e.g., scaling up/down instances, instances crashing and restarting on new ports).

---

### Why use Eureka Discovery Server with Spring Boot?

* **Decoupling:** Microservices don't need to know each other's physical addresses, only their logical names. This reduces coupling and makes services more independent.
* **Scalability:** When you need to scale up a microservice, you simply deploy new instances, and they automatically register with Eureka. The system dynamically adapts.
* **Resilience:** If an instance crashes, Eureka's health checks and heartbeats detect the failure, and the server removes the unhealthy instance from its registry, preventing other services from trying to call it.
* **Load Balancing:** By providing a list of available instances, Eureka facilitates client-side load balancing, distributing requests evenly among healthy service instances.
* **Simplified Configuration:** Spring Cloud provides convenient annotations (`@EnableEurekaServer`, `@EnableDiscoveryClient`) and auto-configuration to quickly set up Eureka Server and clients in Spring Boot applications, significantly reducing boilerplate code.

---

### Setting up an Eureka Server with Spring Boot

1.  **Create a Spring Boot project** using Spring Initializr.
2.  Add the `Eureka Server` dependency (under Spring Cloud Discovery).
3.  Add the `@EnableEurekaServer` annotation in the main application class.
4.  Configure `application.properties` or `application.yml` (e.g., set `server.port=8761`, `eureka.client.registerWithEureka=false`, `eureka.client.fetchRegistry=false` for the server itself).

This setup makes your Spring Boot application the central "Eureka discovery server" for your microservices ecosystem.

### Microservice For Eureka Server
- Folder Name :
  - ServiceRepository
- Annotations: 
  ```java
    @EnableEurekaServer
  ```
- Configuration
  ```yml
  server:
    port: 8761
  
  
  
  eureka:
    instance:
      hostname : localhost
  
    client:
      register-with-eureka: false
      fetch-registry: false

  ```


# Spring Cloud Config Server

The **Spring Cloud Config Server** is a crucial component in a microservices architecture that provides **centralized external configuration management**.

In simpler terms:

* **Problem it Solves:** In a microservices environment, you often have many small services, each needing its own configuration (e.g., database connection strings, API keys, feature flags). Managing these configurations across different environments (development, testing, production) for numerous services can become a chaotic mess.
* **Solution:** The Spring Cloud Config Server acts as a **single source of truth** for all your application configurations. Instead of each microservice having its own `application.properties` or `application.yml` files, they fetch their configurations from this central server.
* **How it Works:**
    * It typically **stores configuration files in a version-controlled repository** like Git (its default and most common backend). This means you can track changes, revert to previous versions, and manage configurations like any other code.
    * Microservices (acting as "Config Clients") retrieve their specific configurations from the Config Server during startup or even dynamically at runtime (using features like `@RefreshScope`).
* **Key Benefits:**
    * **Centralization:** All configurations in one place.
    * **Version Control:** Configurations are versioned, auditable, and can be easily rolled back.
    * **Environment Specificity:** Easily manage different configurations for different environments (e.g., `dev`, `prod` profiles).
    * **Dynamic Updates:** Supports refreshing configurations without restarting microservices.
    * **Security:** Can encrypt and decrypt sensitive properties.

In essence, the Spring Cloud Config Server helps you maintain consistency, flexibility, and auditability for your microservices' configurations, making distributed system management much more streamlined.

## Microservice for Spring Cloud Config Server
- Folder : 
   - wholesaleConfig
- Annotation
  ```java
  @EnableConfigServer
  ```
- Configurations
```yml
  server:
    port: 8085
  
  spring:
    application:
      name: GLOBAL_CONFIG
    profiles:
      active: default
    cloud:
      config:
        server:
          git:
            uri: https://github.com/NARESHSWAMI199/wholesale-config
            skipSslValidation: true

```

# Gateway

The **Spring Cloud Gateway** is a powerful and flexible **API Gateway** solution built on top of Spring Boot and Project Reactor, designed specifically for **microservices architectures**.

In a nutshell:

* **Single Entry Point:** It acts as the **single entry point** for all client requests into your microservices ecosystem. Instead of clients needing to know the specific addresses of individual microservices, they just interact with the Gateway.
* **Request Routing:** Its primary function is to **route incoming requests** to the appropriate backend microservice based on defined rules (like URL paths, headers, or query parameters).
* **Cross-Cutting Concerns:** Beyond routing, it's also the ideal place to handle **cross-cutting concerns** that apply to multiple microservices, such as:
    * **Authentication and Authorization:** Verifying who the user is and what they're allowed to access.
    * **Rate Limiting:** Preventing abuse by limiting the number of requests a client can make.
    * **Load Balancing:** Distributing requests among multiple instances of a microservice (often integrated with Eureka).
    * **Circuit Breakers:** Implementing resilience patterns to prevent cascading failures in case a microservice becomes unresponsive.
    * **Request/Response Transformation:** Modifying requests before sending them to a microservice or responses before sending them back to the client.
* **Reactive and Non-Blocking:** It's built on a reactive, non-blocking architecture, making it highly performant and scalable, suitable for handling a large number of concurrent requests.

Essentially, Spring Cloud Gateway acts as the **"front door"** to your microservices, simplifying client interaction, enhancing security, and providing centralized control over how requests are managed and processed before they reach your individual services.


## Microservice for Gateway
- Folder
  - wholesaleApiGatway
- Annotations
```java
  @EnableEurekaClient // Same for all microservices
```
- Configurations
```yml
  server :
    port : 8080
  
  spring:
    application:
      name: API_GETAWAY
    cloud:
      gateway:
        routes:
          - id: WHOLESALE-SERVICE
            uri: lb://WHOLESALE-SERVICE
            predicates:
              - Path=/wholesale/**
          - id: USER-SERVICE
            uri: lb://USER-SERVICE
            predicates:
              - Path=/users/**
  
  eureka:
    instance:
      prefer-ip-address: true
    client:
      fetch-registry: true
      register-with-eureka: true
      service-url:
        defaultZone : http://localhost:8761/eureka

```

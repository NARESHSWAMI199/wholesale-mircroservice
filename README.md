# Wholesale Microservice
- This is a test project for microservice implementations

## What we used in this
  - Eureka  ```as discovery server```
  - Spring server cloud
  - Feign Clent
  - resilience4j ```fcircuit breakers.```

## Eureka


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
    * When a microservice (acting as a "Eureka Client") starts up, it **registers itself** with the Eureka Server.
    * It sends its details (like its name and network location) to the Eureka Server.
    * It continuously sends **heartbeats** to the Eureka Server to indicate that it's still alive and healthy. If heartbeats stop for a configured period, the Eureka Server will unregister the instance.
    * In Spring Boot, you enable a service as a Eureka client by adding the `spring-cloud-starter-netflix-eureka-client` dependency and annotating your main application class with `@EnableDiscoveryClient` (or `@EnableEurekaClient` in older Spring Cloud versions).

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

### Setting up a Eureka Server with Spring Boot

1.  **Create a Spring Boot project** using Spring Initializr.
2.  Add the `Eureka Server` dependency (under Spring Cloud Discovery).
3.  In the main application class, add the `@EnableEurekaServer` annotation.
4.  Configure `application.properties` or `application.yml` (e.g., set `server.port=8761`, `eureka.client.registerWithEureka=false`, `eureka.client.fetchRegistry=false` for the server itself).

This setup makes your Spring Boot application act as the central "Eureka discovery server" for your microservices ecosystem.

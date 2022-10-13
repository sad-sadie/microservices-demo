# microservices-demo

Implementing microservices in practice purposes. I've implemented two RESTful services: user-service and department-service, which are communicating synchronously, api-gateway to have one actual entry-point, service registry using eureka server, config server, which repo can be found in my profile. I have also implemented circuit breaker with Resilience4j(I've also got retry-mechanism and rate-limiter as two possible variants) and distributed log tracing with zipkin and spring cloud sleuth.

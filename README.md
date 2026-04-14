# Food Delivery Microservices System

A full-stack **microservices-based food delivery application** built using **Spring Boot, Angular, Docker, CI/CD, and Kubernetes**.

---

## Tech Stack

### Backend

* Java (Spring Boot)
* Spring Data JPA
* Spring Cloud (Eureka Service Discovery)
* REST APIs

### Frontend

* Angular

### DevOps & Tools

* Docker & Docker Compose
* GitHub Actions (CI/CD)
* Kubernetes (Minikube)
* MySQL

---

## Microservices Architecture

* **Eureka Server** – Service discovery
* **Restaurant Service** – Manage restaurant data
* **Food Catalogue Service** – Manage food items
* **Order Service** – Handle orders
* **User Service** – Manage users
* **Frontend (Angular)** – UI layer

---

## Features

* Microservices-based architecture
* Service discovery using Eureka
* RESTful communication between services
* Dockerized services
* CI/CD pipeline using GitHub Actions
* Kubernetes deployment ready
* Unit testing with JUnit & Mockito

---

## Running with Docker

### Build images

```bash
docker build -t samyuktha11/eureka-service:latest ./eureka
docker build -t samyuktha11/restaurant-service:latest ./restaurantlisting
docker build -t samyuktha11/food-catalogue-service:latest ./foodcatalogue
docker build -t samyuktha11/order-service:latest ./order
docker build -t samyuktha11/user-service:latest ./userinfo
```

---

### Run all services

```bash
docker-compose up -d
```

---

### Access Eureka Dashboard

```
http://localhost:8761
```

---

## CI/CD Pipeline

Implemented using **GitHub Actions**:

* Build all microservices using Maven
* Run unit tests
* Build Docker images
* Push images to Docker Hub

---

## Kubernetes Deployment

### Start Minikube

```bash
minikube start
```

---

### Apply deployments

```bash
kubectl apply -f k8s/
```

---

### Check pods

```bash
kubectl get pods
```

---

### Access services

```bash
minikube service eureka-service
```

---

## Testing

* Unit tests implemented using:

  * JUnit
  * Mockito

Run tests:

```bash
mvn test
```

---

## Project Structure

```
food-delivery-microservices/
│
├── eureka/
├── restaurantlisting/
├── foodcatalogue/
├── order/
├── userinfo/
├── food-delivery-app/   (Angular frontend)
├── docker-compose.yml
└── .github/workflows/
```

---

##  Environment Configuration

Example:

```yaml
eureka:
  client:
    service-url:
      defaultZone: http://eureka-service:8761/eureka/
```




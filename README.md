# 🏋️ AI-Powered Fitness Application (Microservices Backend)

> A scalable Spring Boot microservices backend for a fitness platform with secure authentication, real-time activity processing, and AI-powered insights.

---

## 🌟 Overview

The **AI-Powered Fitness Application** is a backend-centric microservices project built using **Spring Boot and Spring Cloud**.  
It is designed to manage users, track daily fitness activities, and generate AI-based insights in a **secure, scalable, and asynchronous** manner.

The system leverages **Config Server, Eureka Server, API Gateway, RabbitMQ, and Keycloak**, along with **MySQL and MongoDB**, to deliver a robust distributed architecture.

---

## 🚀 Key Features

- ✅ Spring Boot microservices architecture  
- ✅ Centralized configuration with Config Server  
- ✅ Service discovery using Eureka Server  
- ✅ API Gateway for routing and filtering  
- ✅ Secure authentication and authorization with Keycloak  
- ✅ Asynchronous communication using RabbitMQ  
- ✅ AI integration using Google Gemini API  
- ✅ MySQL for user data & MongoDB for activity data  
- ✅ Designed for scalability and real-time processing  

---

## 🏗️ Microservices Architecture

The backend system consists of the following services:

- ⚙️ **Config Server** – Centralized configuration management  
- 🧭 **Eureka Server** – Service registry and discovery  
- 🌐 **API Gateway** – Single entry point for all client requests  
- 👤 **User Service** – User management and authentication support  
- 🏃 **Activity Service** – Fitness activity tracking and storage  
- 🤖 **AI Service** – AI processing and insight generation  
- 📩 **RabbitMQ** – Message broker for asynchronous messaging  

---

## 🛠️ Tech Stack

**Backend & Cloud**  
- Java, Spring Boot, Spring Cloud  
- Config Server, Eureka, API Gateway  

**Security**  
- Keycloak  

**Messaging**  
- RabbitMQ  

**Databases**  
- MySQL, MongoDB  

**AI Integration**  
- Google Gemini API  

---

## ⚙️ System Workflow

1. Requests enter through the **API Gateway**  
2. Services are discovered via the **Eureka Server**  
3. User-related operations are handled by **User Service**  
4. Activity data is stored in **MongoDB**  
5. Events are published to **RabbitMQ**  
6. **AI Service** consumes messages and generates insights using Gemini API  
7. Processing happens asynchronously for better performance and scalability  

---

## 📂 Repository Structure
AI_Powered_Fitness_App/
├─ api-gateway/
│ ├─ src/
│ └─ pom.xml
├─ user-service/
│ ├─ src/
│ └─ pom.xml
├─ activity-service/
│ ├─ src/
│ └─ pom.xml
├─ ai-service/
│ ├─ src/
│ └─ pom.xml


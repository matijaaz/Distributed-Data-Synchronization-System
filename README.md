# Distributed Data Synchronization System (Video Streaming Backend)

This repository contains a fully functional, distributed backend system designed for a video streaming platform. The project demonstrates advanced architectural patterns, including an API Gateway, microservices-like subsystems, and asynchronous message-driven communication to maintain data consistency across multiple isolated databases.

## 🏗️ Architecture Overview

The architecture is designed to handle user requests, process them centrally, and delegate tasks to specialized subsystems. [cite_start]The system consists of a client application, a central server, and three distinct subsystems[cite: 18].

1. [cite_start]**Client Application (Java SE):** A console/GUI application that accepts user inputs, creates REST requests, and displays responses from the server[cite: 24, 25].
2. [cite_start]**Central Server (API Gateway):** A stateless server that exposes 25 REST endpoints[cite: 27, 28]. [cite_start]It receives HTTP requests from the client and delegates them to the appropriate subsystem using Java Message Service (JMS)[cite: 20, 21].
3. [cite_start]**Subsystem 1 (User Management):** Manages relational data regarding cities and users[cite: 56]. [cite_start]Communicates exclusively via JMS[cite: 57].
4. [cite_start]**Subsystem 2 (Content Management):** Manages videos, categories, and their relations to users[cite: 59]. [cite_start]Communicates exclusively via JMS[cite: 60].
5. [cite_start]**Subsystem 3 (Billing & Analytics):** Handles subscription packages, active user subscriptions, video views, and user ratings[cite: 62]. [cite_start]Communicates exclusively via JMS[cite: 63].

### 🔄 The Synchronization Challenge
[cite_start]Because entities like `Users` and `Videos` span across multiple subsystems (e.g., Subsystem 3 needs user and video data to process a subscription or a rating)[cite: 59, 62], the system relies on asynchronous JMS queues/topics to replicate and synchronize data across three separate MySQL databases, ensuring high availability and eventual consistency.

## 🚀 Core Features & REST API

The Central Server exposes a comprehensive RESTful API handling the following domains:

* [cite_start]**User Management:** Create users, update email/location, and fetch user data[cite: 30, 31, 32, 46]. [cite_start]Directed to Subsystem 1[cite: 54].
* [cite_start]**Video & Content:** Create categories, upload videos, modify video details, and delete content[cite: 33, 34, 35, 44]. [cite_start]Directed to Subsystem 2[cite: 54].
* [cite_start]**Billing & Subscriptions:** Create monthly packages, modify pricing, and handle user subscriptions (ensuring no overlapping active subscriptions)[cite: 10, 11, 12, 37, 39]. [cite_start]Directed to Subsystem 3[cite: 54].
* [cite_start]**Analytics & Interactions:** Track video views (start time, watch duration) and user ratings (1-5 scale)[cite: 14, 15, 40, 41]. [cite_start]Directed to Subsystem 3[cite: 54].

## 🛠️ Technologies Used

* [cite_start]**Java (Core/EE):** Primary programming language[cite: 24].
* [cite_start]**JAX-RS (REST):** Used in the Central Server for handling incoming client HTTP requests[cite: 20, 28].
* [cite_start]**Java Message Service (JMS):** Used for all inter-service asynchronous communication between the Central Server and the three Subsystems[cite: 21, 57, 60, 63].
* [cite_start]**MySQL:** Relational databases used by each subsystem to persist data[cite: 67, 80].
* [cite_start]**UML:** System design was modeled using Sequence and Class diagrams[cite: 70, 71].

## ⚙️ Getting Started

### Prerequisites
* Java Development Kit (JDK) 8+
* [cite_start]MySQL Server [cite: 80]
* Application Server / Message Broker (e.g., Payara, GlassFish, or ActiveMQ) configured with the necessary JMS Connection Factories and Destinations (Queues/Topics).

### Database Setup
1. [cite_start]Locate the MySQL dump files in the `baze` directory[cite: 68].
2. [cite_start]Execute the scripts to create and populate the databases for Subsystem 1, Subsystem 2, and Subsystem 3[cite: 67].

### Running the System
1. Deploy the Central Server to your Application Server to expose the REST API.
2. Deploy/Start Subsystem 1, Subsystem 2, and Subsystem 3 so they can begin listening to their respective JMS queues.
3. [cite_start]Run the Client Application (`java aplikacije` folder) to start interacting with the system[cite: 69].

## 📝 Author
**Matija Krečković**
[cite_start]*Developed as part of the Information Systems 1 curriculum at the School of Electrical Engineering, University of Belgrade[cite: 1, 3].*

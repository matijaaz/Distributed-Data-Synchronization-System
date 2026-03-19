# Distributed Data Synchronization System

This repository contains a fully functional, distributed backend system designed for a video management platform.

## Architecture Overview

The architecture is designed to handle user requests, process them centrally, and delegate tasks to specialized subsystems. The system consists of a client application, a central server, and three distinct subsystems.

1. **Client Application (Java SE):** A console/GUI application that accepts user inputs, creates REST requests, and displays responses from the server.
2. **Central Server (API Gateway):** A stateless server that exposes 25 REST endpoints. It receives HTTP requests from the client and delegates them to the appropriate subsystem using Java Message Service (JMS).
3. **Subsystem 1 (User Management):** Manages relational data regarding cities and users. Communicates exclusively via JMS.
4. **Subsystem 2 (Content Management):** Manages videos, categories, and their relations to users. Communicates exclusively via JMS.
5. **Subsystem 3 (Billing & Analytics):** Handles subscription packages, active user subscriptions, video views, and user ratings. Communicates exclusively via JMS.

### The Synchronization Challenge
Because entities like `Users` and `Videos` span across multiple subsystems (e.g., Subsystem 3 needs user and video data to process a subscription or a rating), the system relies on asynchronous JMS queues/topics to replicate and synchronize data across three separate MySQL databases, ensuring high availability and eventual consistency.

## Core Features & REST API

The Central Server exposes a comprehensive RESTful API handling the following domains:

* **User Management:** Create users, update email/location, and fetch user data. Directed to Subsystem 1.
* **Video & Content:** Create categories, upload videos, modify video details, and delete content. Directed to Subsystem 2.
* **Billing & Subscriptions:** Create monthly packages, modify pricing, and handle user subscriptions (ensuring no overlapping active subscriptions). Directed to Subsystem 3.
* **Analytics & Interactions:** Track video views (start time, watch duration) and user ratings (1-5 scale). Directed to Subsystem 3.

## Technologies Used

* **Java (Core/EE):** Primary programming language.
* **JAX-RS (REST):** Used in the Central Server for handling incoming client HTTP requests.
* **Java Message Service (JMS):** Used for all inter-service asynchronous communication between the Central Server and the three Subsystems.
* **MySQL:** Relational databases used by each subsystem to persist data.
* **UML:** System design was modeled using Sequence and Class diagrams.

## Getting Started

### Prerequisites
* Java Development Kit (JDK) 8+
* MySQL Server
* Application Server / Message Broker (e.g., Payara, GlassFish, or ActiveMQ) configured with the necessary JMS Connection Factories and Destinations (Queues/Topics).

### Database Setup
1. Locate the MySQL dump files in the `baze` directory.
2. Execute the scripts to create and populate the databases for Subsystem 1, Subsystem 2, and Subsystem 3.

### Running the System
1. Deploy the Central Server to your Application Server to expose the REST API.
2. Deploy/Start Subsystem 1, Subsystem 2, and Subsystem 3 so they can begin listening to their respective JMS queues.
3. Run the Client Application (`java aplikacije` folder) to start interacting with the system.

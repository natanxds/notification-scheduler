# Microservice API

## Overview

The **Notification Scheduler API** is a Spring Boot application designed to manage notifications. It includes features for scheduling, sending, and canceling notifications. The application uses Spring Scheduling to periodically check and send pending notifications.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Features

- Schedule notifications
- Send notifications
- Cancel notifications
- Periodic task scheduling using Spring Scheduling

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- Docker

### Installation

1. **Clone the repository:**

    ```sh
    git clone https://github.com/Natan-Xavier-da-Silva-MCFP36_moto/Microservice.git
    cd notification-scheduler
    ```

2. **Start the MySQL container:**

    Ensure Docker is running and execute the following command to start the MySQL container:

    ```sh
    docker-compose -f docker/docker-compose.yaml up -d
    ```

3. **Configure the database:**

    Update the `application.properties` or `application.yml` file in the `src/main/resources` directory with your database configuration.

4. **Build the project:**

    ```sh
    mvn clean install
    ```

### Running the Application

1. **Run the application:**

    ```sh
    mvn spring-boot:run
    ```

2. **Access the application:**

    The application will be available at `http://localhost:8080`.

## Configuration

### Application Properties

The application can be configured using the `application.properties` or `application.yml` file located in the `src/main/resources` directory.

Example `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mysql-database
spring.datasource.username=root
spring.datasource.password=123
spring.jpa.hibernate.ddl-auto=update
spring.application.name=Microservice

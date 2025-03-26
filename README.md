# Credable Loan Management System

This is a simple loan management system that allows users to apply for loans and view their loan status.
It is component based system built using spring boot.

## Getting Started

To get started with this project, you need to have the following installed on your machine:

- Java 21
- Maven
- MySQL
- Git
- IDE (Vscode, IntelliJ IDEA, Eclipse, etc)
- Postman

## Installation

1. Clone the repository

```bash
git clone https://github.com/acellam/credable-lms.git
```

2. Change directory to the project directory

```bash
cd credable-lms
```

3. Install dependencies

```bash
mvn clean install
```

4. Change directory to the project directory

```bash
mvn spring-boot:run
```

5. Open Postman and test the endpoints

## Usage

The application has the following endpoints:

- POST /api/v1/subscribe - Submit loan request (customer number + amount).
- POST /api/v1/loans - Apply for a loan (customer number + amount).
- GET /api/v1/loans/{id} - Get loan status (pending/approved/rejected/failed)

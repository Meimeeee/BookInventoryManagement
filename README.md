# BookInventoryManage

## Overview
BookInventoryManage is a backend system for managing a book inventory, built with Spring Boot. It provides robust CRUD operations, user authentication and authorization, custom data queries, and integration with external services like Google OAuth. The project is designed for extensibility and security, making it suitable for real-world inventory management scenarios.

## Technologies Used
- **Java 17**
- **Spring Boot 3.4.5**
- **Spring Data JPA** (with custom native queries)
- **Spring Security** (JWT-based authentication)
- **Spring Validation** (DTO validation)
- **MSSQL** (primary database)
- **Lombok** (boilerplate reduction)
- **JUnit 5** (unit testing)
- **Google OAuth** (social login)

## Key Features
### 1. Custom Data Access
- All repository data access is implemented using custom SQL queries (native queries), not just standard JPA methods. This allows for optimized and flexible data retrieval tailored to business requirements.
- Complex queries for filtering, searching, and aggregating book, author, and review data.

### 2. User Authentication & Authorization
- Secure login and registration with JWT tokens.
- Google OAuth integration for social login.
- Three user roles: `GUEST`, `USER`, `ADMIN`.
- Role-based access control: only `ADMIN` can delete accounts or reviews of other users.

### 3. CRUD Operations
- **Account:** Register, login (standard & Google), view/update profile, change password, delete account.
- **Review:** Create, view, update, and delete reviews. List reviews by book or user. Only review owners or admins can delete reviews.
- **Book, Author, Profile:** Full CRUD with advanced filtering and searching using custom queries.

### 4. Data Validation
- DTOs are validated using Jakarta Validation annotations.
- Examples: strong password enforcement, email format, review content length, rating range (1-10), etc.

### 5. Security
- JWT-based stateless authentication.
- Passwords are hashed and never stored in plain text.
- Secure endpoints with role-based access restrictions.

### 6. Third-Party Libraries
- **com.auth0:java-jwt**: JWT creation and validation.
- **com.google.api-client:google-api-client**: Google OAuth integration.
- **org.projectlombok:lombok**: Code reduction for entities and DTOs.
- **Spring Boot Starters**: Security, validation, data-jpa, web, etc.

## Configuration
- Main configuration file: `src/main/resources/application.properties`
- Contains DB connection, JWT secret, Google client ID/secret, etc.

## How to Run
1. Install Java 17 and MSSQL.
2. Configure database, JWT, and Google OAuth credentials in `application.properties`.
3. Build and run:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Run front end:
```bash
http-server -p 3000
```
## Relationship Diagram
https://drive.google.com/file/d/1p2IBkNrwqAyxK2hOu3_vBf45B3bNFHlV/view?usp=sharing



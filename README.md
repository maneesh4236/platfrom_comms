# Student Management System

Spring Boot REST API for managing student admissions, courses, and enrollments.

## Features

- Admin authentication with Spring Security Basic Auth.
- Student identity verification using student code and date of birth.
- Student admission with multiple addresses.
- Course creation and assignment to students.
- Search students by name.
- Get students assigned to a course.
- Student profile update, including email, mobile number, parents' names, and addresses.
- Search assigned courses by course name, topic, or description.
- Leave an assigned course.
- Swagger UI for API documentation.
- Unit test coverage for the service layer.

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Security
- JPA with Hibernate
- MySQL
- Lombok
- Swagger / SpringDoc OpenAPI
- JUnit and Mockito

## Project Structure

```text
src/main/java/com/platformcommons/sms
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
│   └── impl
├── security
└── service
    └── impl
```

Layer flow:

```text
Controller -> Service Interface -> Service Implementation -> Repository Interface -> Repository Implementation
```

## Design Notes

This project uses a layered backend structure so each part has a clear responsibility:

- Controllers receive HTTP requests and return API responses.
- Service interfaces define the business operations available to controllers.
- Service implementations contain admission, enrollment, profile update, and course search logic.
- Repository interfaces define persistence operations used by services.
- Repository implementations use JPA `EntityManager` for database access.
- Mapper classes convert request DTOs into entities before persistence.
- DTO validation protects the API from incomplete or invalid request data.

Before submission, review these files and make sure you can explain the choices in your own words:

```text
src/main/java/com/platformcommons/sms/service/impl/StudentServiceImpl.java
src/main/java/com/platformcommons/sms/repository/impl/StudentRepositoryImpl.java
src/main/java/com/platformcommons/sms/mapper/StudentMapper.java
src/main/java/com/platformcommons/sms/security/SecurityConfig.java
```

## Database Setup

Create a MySQL database:

```sql
CREATE DATABASE student_db;
```

Update database credentials in:

```text
src/main/resources/application.yaml
```

Current configuration:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/student_db
    username: root
    password: your_mysql_password
```

## Run the Application

From the project root:

```powershell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-21'
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
.\mvnw.cmd spring-boot:run
```

Application URL:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

## Run Tests

```powershell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-21'
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
.\mvnw.cmd clean test
```

## Authentication

Admin endpoints use Basic Auth.

```text
Username: admin
Password: admin123
```

Student verification is available without admin login:

```http
POST /auth/student/verify
```

## Main API Endpoints

### Auth

```http
GET /auth/admin/login
POST /auth/student/verify
```

### Admin

```http
POST /admin/students
POST /admin/courses
POST /admin/assign-course
GET /admin/students/search?name=Man
GET /admin/courses/{courseId}/students
```

### Student

```http
PUT /student/profile/{studentId}
GET /student/{studentId}/courses
GET /student/{studentId}/courses/search?keyword=java
DELETE /student/{studentId}/courses/{courseId}
```

## Sample Student Request

```json
{
  "name": "Maneesh",
  "dateOfBirth": "2001-06-15",
  "gender": "Male",
  "studentCode": "STU001",
  "addresses": [
    {
      "addressType": "PERMANENT",
      "addressLine": "Permanent address"
    },
    {
      "addressType": "CURRENT",
      "addressLine": "Current address"
    }
  ]
}
```

## Postman Collection

Import this file into Postman:

```text
postman/student-management-system.postman_collection.json
```

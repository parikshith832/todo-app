# Todo App

A simple, clean To-Do List web application built with **Spring Boot** and plain **HTML/CSS/JavaScript** (Bootstrap 5).

## Features

- User registration and login (email or username)
- Session-based authentication with Spring Security and BCrypt
- Dashboard with task statistics
- Full task CRUD (create, view, edit, delete)
- Mark tasks complete / pending
- Live search by title (JavaScript)
- Filters: All, Pending, Completed, Today, Overdue
- Monthly calendar view with tasks by due date
- Dark mode toggle (saved in localStorage)
- Responsive Bootstrap 5 UI with Font Awesome icons and SweetAlert2

## Tech Stack

| Layer     | Technology                                      |
|-----------|-------------------------------------------------|
| Backend   | Java 17, Spring Boot 3, Spring MVC, Spring Data JPA, Spring Security |
| Database  | MySQL                                           |
| Frontend  | Thymeleaf, HTML5, CSS3, JavaScript (ES6), Bootstrap 5 |
| Build     | Maven, Lombok                                   |

## Prerequisites

- **Java 17+**
- **Maven 3.8+**
- **MySQL 8+** running locally

## Database Setup

1. Start MySQL.
2. Update credentials in `src/main/resources/application.properties` if needed:

```properties
spring.datasource.username=root
spring.datasource.password=root
```

3. The app auto-creates the `todo_db` database on first run (`createDatabaseIfNotExist=true`).

Alternatively, run the manual schema:

```bash
mysql -u root -p < sql/schema.sql
```

## Run the Application

```bash
# From project root
mvn spring-boot:run
```

Open [http://localhost:8080](http://localhost:8080) in your browser.

1. Click **Register** to create an account.
2. Log in with your username or email.
3. Start adding tasks from the Dashboard or Tasks page.

## Project Structure

```
todo-app/
├── sql/
│   └── schema.sql
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── todoapp/
│       │           ├── config/
│       │           │   ├── SecurityConfig.java
│       │           │   └── WebConfig.java
│       │           │
│       │           ├── controller/
│       │           │   ├── AuthController.java
│       │           │   ├── CalendarController.java
│       │           │   ├── DashboardController.java
│       │           │   └── TaskController.java
│       │           │
│       │           ├── dto/
│       │           │   ├── DashboardStatsDto.java
│       │           │   ├── RegisterDto.java
│       │           │   └── TaskDto.java
│       │           │
│       │           ├── entity/
│       │           │   ├── Priority.java
│       │           │   ├── Task.java
│       │           │   ├── TaskStatus.java
│       │           │   └── User.java
│       │           │
│       │           ├── repository/
│       │           │   ├── TaskRepository.java
│       │           │   └── UserRepository.java
│       │           │
│       │           ├── security/
│       │           │   ├── CurrentUser.java
│       │           │   └── CustomUserDetailsService.java
│       │           │
│       │           ├── service/
│       │           │   ├── impl/
│       │           │   │   ├── TaskServiceImpl.java
│       │           │   │   └── UserServiceImpl.java
│       │           │   ├── TaskService.java
│       │           │   └── UserService.java
│       │           │
│       │           └── TodoApplication.java
│       │
│       └── resources/
│           ├── static/
│           │   ├── css/
│           │   │   └── style.css
│           │   └── js/
│           │       ├── app.js
│           │       ├── calendar.js
│           │       └── search.js
│           │
│           ├── templates/
│           │   ├── fragments/
│           │   │   └── layout.html
│           │   ├── tasks/
│           │   │   ├── detail.html
│           │   │   ├── form.html
│           │   │   └── list.html
│           │   ├── calendar.html
│           │   ├── dashboard.html
│           │   ├── login.html
│           │   └── register.html
│           │
│           └── application.properties
│
├── .gitattributes
├── .gitignore
├── LICENSE
├── pom.xml
└── README.md
```

## Default Routes

| Route              | Description              |
|--------------------|--------------------------|
| `/login`           | Login page               |
| `/register`        | Registration page        |
| `/dashboard`       | Dashboard with stats     |
| `/tasks`           | Task list with filters   |
| `/tasks/new`       | Add new task             |
| `/tasks/{id}`      | Task detail view         |
| `/tasks/{id}/edit` | Edit task                |
| `/calendar`        | Monthly calendar view    |
| `/logout`          | Logout (POST)            |

## Build JAR

```bash
mvn clean package
java -jar target/todo-app-1.0.0.jar
```

## License

MIT — free to use for learning and personal projects.

# ✅ Todo App

A modern **Task Management Web Application** built with **Java Spring Boot**, **Spring Security**, **Spring Data JPA**, **MySQL**, and **Thymeleaf**. The application helps users organize daily tasks through an intuitive dashboard, calendar view, task management system, and secure authentication.

---

## 📸 Preview

> Add screenshots of your application here.

```
Dashboard
Tasks
Calendar
Task Details
Dark Mode
```

---

# ✨ Features

### 🔐 Authentication
- User Registration
- Secure Login (Username or Email)
- Password Encryption using BCrypt
- Session-based Authentication with Spring Security
- Logout Support

### 📋 Task Management
- Create Tasks
- View Tasks
- Update Tasks
- Delete Tasks
- Task Details Page
- Mark Tasks as Completed
- Mark Tasks as Pending

### 📊 Dashboard
- Total Tasks
- Pending Tasks
- Completed Tasks
- Today's Tasks
- Today's Schedule
- Recent Tasks

### 📅 Calendar
- Monthly Calendar View
- View Tasks by Date
- Quick Add Task
- Due Date Navigation

### 🔍 Search & Filters
- Live Search
- Filter by:
  - All
  - Pending
  - Completed
  - Today
  - Overdue

### 🎨 UI Features
- Responsive Design
- Bootstrap 5
- Font Awesome Icons
- SweetAlert2 Alerts
- Floating Add Button
- Dark Mode
- Mobile Friendly

---

# 🛠 Tech Stack

| Category | Technology |
|----------|------------|
| Backend | Java 17 |
| Framework | Spring Boot 3 |
| MVC | Spring MVC |
| ORM | Spring Data JPA (Hibernate) |
| Security | Spring Security |
| Database | MySQL 8 |
| Frontend | Thymeleaf |
| Styling | HTML5, CSS3, Bootstrap 5 |
| Scripting | JavaScript (ES6) |
| Build Tool | Maven |
| Utilities | Lombok |

---

# 📂 Project Structure

```text
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

---

# ⚙ Requirements

- Java 17 or later
- Maven 3.8+
- MySQL 8+
- IntelliJ IDEA / Eclipse / VS Code

---

# 🗄 Database Setup

Create a MySQL database:

```sql
CREATE DATABASE todo_db;
```

Update your database credentials in:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=root
spring.datasource.password=your_password
```

You may also execute:

```bash
mysql -u root -p todo_db < sql/schema.sql
```

---

# ▶ Running the Project

Clone the repository

```bash
git clone https://github.com/your-username/todo-app.git
```

Move into the project

```bash
cd todo-app
```

Run the application

```bash
mvn spring-boot:run
```

The application starts at

```
http://localhost:8080
```

---

# 🚀 Application Workflow

1. Register a new account.
2. Login using Username or Email.
3. Access the Dashboard.
4. Create Tasks.
5. Manage Tasks.
6. Track today's schedule.
7. View tasks in Calendar.
8. Enable Dark Mode if preferred.

---

# 🌐 Available Routes

| URL | Description |
|------|------------|
| `/` | Redirect to Login |
| `/login` | Login Page |
| `/register` | Register Page |
| `/dashboard` | User Dashboard |
| `/tasks` | Task List |
| `/tasks/new` | Create Task |
| `/tasks/{id}` | View Task |
| `/tasks/{id}/edit` | Edit Task |
| `/calendar` | Calendar |
| `/logout` | Logout |

---

# 📦 Build Executable JAR

```bash
mvn clean package
```

Run the generated JAR

```bash
java -jar target/todo-app-1.0.0.jar
```

---

# 🔒 Security

- Spring Security Authentication
- BCrypt Password Hashing
- Protected Routes
- Session Management
- CSRF Protection
- User-based Task Authorization

---

# 📈 Future Enhancements

- Email Notifications
- Task Reminders
- File Attachments
- Labels & Tags
- Task Sharing
- Recurring Tasks
- REST API
- Docker Deployment
- Unit & Integration Tests

---

## 👥 Team

This project was collaboratively developed by:

- **Parikshith S**
- **Darshan N G**

as part of an IOT Full Stack Web Development project.

---

# 📄 License

This project is licensed under the **MIT License**.

Feel free to use, modify, and learn from this project.

# Student Result Portal

A simple Java-based website for managing and displaying students assessment results.

## Description

This project is a basic web-based Student Result Portal that allows:
* Students to enter their roll number and view their subject-wise marks.
* Admins to add new student details and marks for specific subjects.

## Technologies Used

* Java (Servlets and JSP)
* HTML & CSS
* JavaScript
* MySQL (Database)
* JDBC (Java Database Connectivity)
* Apache Tomcat (Server)

## Project Structure

StudentResultPortal (Project Root)
│
├── src/main/java
│   ├── A_Servlet
│   │   └── AdminServlet.java
│   └── R_Servlet
│       └── ResultServlet.java
│
├── webapp
│   ├── META-INF
│   └── WEB-INF
│       ├── lib
│       │   └── mysql-connector-j-9.3.0.jar
│       ├── web.xml
│       ├── admin.html
│       ├── index.html
│       └── result.jsp


### How to Run the Project in Eclipse

### Prerequisites

Before running the project, ensure the following software is installed and set up:

* Eclipse IDE for Enterprise Java Developers
* Apache Tomcat Server (configured in Eclipse)
* MySQL Server
* MySQL JDBC Driver (`mysql-connector-j-9.3.0.jar`)

### Step-by-Step Guide

#### 1. Create the MySQL Database and Tables

Open MySQL and execute the following SQL commands:
sql
CREATE DATABASE student_portal;

USE student_portal;

CREATE TABLE students (
    roll_no VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    course VARCHAR(100)
);

CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    roll_no VARCHAR(10),
    subject VARCHAR(100),
    marks INT,
    FOREIGN KEY (roll_no) REFERENCES students(roll_no)
);

#### 4. Add the MySQL JDBC Driver

* Copy the `mysql-connector-j-9.3.0.jar` file into the `WEB-INF/lib` directory of your project.
* Right-click on the project → `Build Path` → `Configure Build Path`.
* Ensure the JAR is listed under the Libraries tab.

#### 5. Run the Project

* Right-click on the project → `Run As` → `Run on Server`.
* The application will be deployed to Tomcat and accessible at:
http://localhost:8080/StudentResultPortal/

#### 6. Using the Application

* Open `index.html` to allow students to enter their roll number and view results.
* Use `admin.html` to enter student details and add marks (handled by AdminServlet).





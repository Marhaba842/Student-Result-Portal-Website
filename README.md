# Student Result Portal

A simple Java-based website for managing and displaying student results.

## Description

This project is a basic web-based Student Result Portal that allows:

- Students to enter their roll number and view their subject-wise marks.
- Admins to add new student details and enter marks for specific subjects.


## Technologies Used

- Java (Servlets and JSP)
- HTML & CSS
- JavaScript 
- MySQL (Database)
- JDBC (Java Database Connectivity)
- Apache Tomcat (Server)


## Project Structure

StudentResultPortal (Project Root)
│
├── src/main/java
│ ├── A_Servlet
│ │ └── AdminServlet.java
│ └── R_Servlet
│ └── ResultServlet.java
│
├── webapp
│ ├── META-INF
│ └── WEB-INF
│ ├── lib
│ │ └── mysql-connector-j-9.3.0.jar
│ ├── web.xml
│ ├── admin.html
│ ├── index.html
│ └── result.jsp

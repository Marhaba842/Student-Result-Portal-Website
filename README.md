Project Title - Student Result Portal

Description:
A simple Java-based website for managing and displaying student results. The application allows:
1)Students to enter their roll number and view their subject-wise marks.
2)Admins to add new student details and enter their marks for specific subjects.

Technologies Used:
Java (Servlets and JSP)
HTML, CSS
JavaScript 
MySQL (Database)
Apache Tomcat (Server)
JDBC (Database connectivity)


Project Structure:

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


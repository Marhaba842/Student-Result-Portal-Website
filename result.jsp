<%@ page import="java.util.*, java.sql.*" %>
<%
String rollNumber = request.getParameter("roll_no");
if (rollNumber == null || rollNumber.trim().isEmpty()) {
    out.println("<h3>Error: Roll number is required.</h3>");
    return;
}

String studentName = "";
String studentCourse = "";
List<String[]> resultList = new ArrayList<>();

Connection connection = null;
PreparedStatement studentQuery = null;
PreparedStatement resultQuery = null;
ResultSet studentResultSet = null;
ResultSet marksResultSet = null;

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_portal", "root", "eram1234");

    studentQuery = connection.prepareStatement("SELECT * FROM students WHERE roll_no = ?");
    studentQuery.setString(1, rollNumber);
    studentResultSet = studentQuery.executeQuery();

    if (studentResultSet.next()) {
        studentName = studentResultSet.getString("name");
        studentCourse = studentResultSet.getString("course");

        resultQuery = connection.prepareStatement("SELECT subject, marks FROM results WHERE roll_no = ?");
        resultQuery.setString(1, rollNumber);
        marksResultSet = resultQuery.executeQuery();

        while (marksResultSet.next()) {
            resultList.add(new String[]{
                marksResultSet.getString("subject"),
                String.valueOf(marksResultSet.getInt("marks"))
            });
        }
    } else {
        out.println("<h3>No student found with Roll No: " + rollNumber + "</h3>");
        return;
    }
} catch (Exception exception) {
    out.println("<h3>Error: " + exception.getMessage() + "</h3>");
} finally {
    try { if (studentResultSet != null) studentResultSet.close(); } catch (Exception e) {}
    try { if (marksResultSet != null) marksResultSet.close(); } catch (Exception e) {}
    try { if (studentQuery != null) studentQuery.close(); } catch (Exception e) {}
    try { if (resultQuery != null) resultQuery.close(); } catch (Exception e) {}
    try { if (connection != null) connection.close(); } catch (Exception e) {}
}
%>

<h2>Student Result</h2>
<p><strong>Name:</strong> <%= studentName %></p>
<p><strong>Course:</strong> <%= studentCourse %></p>

<% if (resultList.isEmpty()) { %>
    <p>No results available for this student.</p>
<% } else { %>
    <table border="1" cellpadding="8">
        <tr>
            <th>Subject</th>
            <th>Marks</th>
        </tr>
        <% for (String[] row : resultList) { %>
            <tr>
                <td><%= row[0] %></td>
                <td><%= row[1] %></td>
            </tr>
        <% } %>
    </table>
<% } %>

<br>
<a href="index.html">Back to Home</a>

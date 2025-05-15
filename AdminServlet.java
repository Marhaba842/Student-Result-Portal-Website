package A_Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(name = "AdminServlet1", urlPatterns = { "/AdminServlet1" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        String studentId = request.getParameter("roll_no");
        String studentName = request.getParameter("name");
        String studyProgram = request.getParameter("course");
        String testSubject = request.getParameter("subject");
        int numericGrade = Integer.parseInt(request.getParameter("marks"));

        String url = "jdbc:mysql://localhost:3306/student_portal";
        String username = "root";
        String password = "eram1234";

        try (
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement checkStudentStmt = conn.prepareStatement("SELECT * FROM students WHERE roll_no = ?");
            PreparedStatement insertStudentStmt = conn.prepareStatement("INSERT INTO students VALUES (?, ?, ?)");
            PreparedStatement checkSubjectStmt = conn.prepareStatement("SELECT * FROM results WHERE roll_no = ? AND subject = ?");
            PreparedStatement insertMarksStmt = conn.prepareStatement("INSERT INTO results (roll_no, subject, marks) VALUES (?, ?, ?)");
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            checkStudentStmt.setString(1, studentId);
            ResultSet studentRs = checkStudentStmt.executeQuery();

            if (!studentRs.next()) {
                insertStudentStmt.setString(1, studentId);
                insertStudentStmt.setString(2, studentName);
                insertStudentStmt.setString(3, studyProgram);
                insertStudentStmt.executeUpdate();
            }

            checkSubjectStmt.setString(1, studentId);
            checkSubjectStmt.setString(2, testSubject);
            ResultSet subjectRs = checkSubjectStmt.executeQuery();

            if (subjectRs.next()) {
                response.getWriter().println("<h3>Error: Subject already exists for this student.</h3><a href='admin.html'>Back</a>");
            } else {
                insertMarksStmt.setString(1, studentId);
                insertMarksStmt.setString(2, testSubject);
                insertMarksStmt.setInt(3, numericGrade);
                insertMarksStmt.executeUpdate();

                response.getWriter().println("<h3>Assessment data recorded successfully!</h3><a href='admin.html'>Back to Admin</a>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error occurred: " + e.getMessage() + "</h3>");
        }
    }
}

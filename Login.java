import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		Connection con=null;
		java.sql.Statement st;
		ResultSet rs;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "123456789");
			st=con.createStatement();
			rs=st.executeQuery("select * from login where acc_no="+uname+"");
			if(rs.next())
			{
				if(rs.getString(2).equals(pass))
				{
					HttpSession session = request.getSession();
					session.setAttribute("username", uname);
					response.sendRedirect("welcome.jsp");
					response.sendRedirect("accountDetails.jsp");
					response.sendRedirect("transfer.jsp.jsp");
				}
				else
				{
					response.sendRedirect("login.jsp");
					
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}	
	}
}
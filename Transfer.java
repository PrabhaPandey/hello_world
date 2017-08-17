import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.Float;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accno1 = request.getParameter("accno1");
		String accno2 = request.getParameter("accno2");
		float amt = Float.parseFloat(request.getParameter("amt"));
		Connection con=null;
		java.sql.Statement st;

		ResultSet rs;
		ResultSet rs1;
		int i,j,k;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "123456789");
			st=con.createStatement();
			i=st.executeUpdate("insert into transaction (deb_acc_no,cre_acc_no,deb_amount) values('"+accno1+"','"+accno2+"','"+amt+"');");
			rs=st.executeQuery("select bal from coustomer where acc_no='"+accno2+"';");
			rs.next();
			float b=Float.parseFloat(rs.getString("bal"));
			float bal=b+amt;
			j=st.executeUpdate("update coustomer set bal= "+bal+" where acc_no='"+accno2+"';");
			rs1=st.executeQuery("select bal from coustomer where acc_no='"+accno1+"';");
			rs1.next();
			float s=Float.parseFloat(rs1.getString("bal"));
			float bal1=s-amt;
			k=st.executeUpdate("update coustomer set bal= "+bal1+" where acc_no='"+accno1+"';");
			
			if(i==1 && j==1 && k==1){response.sendRedirect("transferS.jsp");}
			else{response.sendRedirect("transferF.jsp");}
		}
		catch(Exception e){
			System.out.println(e);
		}	
	}
}
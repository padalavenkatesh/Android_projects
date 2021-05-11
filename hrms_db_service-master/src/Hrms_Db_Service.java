
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class Hrms_Db_Service{
	
public String hrmsService(String FirstName, String LastName)throws Exception
{

	
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/cif", "root", "mysql");
		
		System.out.println("connected successfully");
		PreparedStatement pstmt=conn.prepareStatement("insert into HRMS_TIMESHEET values(?,?)");
		pstmt.setString(1, FirstName);
		pstmt.setString(2, LastName);

		pstmt.executeUpdate();
		pstmt.close();
		conn.close();


	return "Record inserted sucessfully";
}

}
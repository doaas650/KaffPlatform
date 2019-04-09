package App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.widgets.List;





public class TestConnection {
	String username="root";
	String password="00001";
	String URL="jdbc:mysql://localhost:3306/kaff";
	String selectQuery="select bookTitle , bookLevel,bookType,bookPrice, available, edition from book where bookTitle= ?";
	String signinQuery="select adminID , adminPassword from signin where adminID= ? and adminPassword=?";
	PreparedStatement state;
	PreparedStatement sign;
	BookInformation BI;
	Connection con;
	
	public TestConnection()  {
	 try {
		con=DriverManager.getConnection(URL, username, password);
	
		state=con.prepareStatement(selectQuery);
		sign=con.prepareStatement(signinQuery);
		
		
		
	} 
	 catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	}
	public boolean siin(String username,String password) throws SQLException {

		//Signin signin= new Signin(username, password);
		ResultSet resultSign=null;
		sign.setString(1, username);
		sign.setString(2,password);
		resultSign=sign.executeQuery();
		while(resultSign.next()) {
			sign.setString(1, username);
			sign.setString(2,password);
			System.out.println(resultSign.getString("username"));
			System.out.println(resultSign.getString("passwords"));
		}

	
resultSign.close();
return false;
}
	
	
	public ArrayList<BookInformation> get(String title) throws SQLException{
		
		ArrayList<BookInformation> result=null;
		ResultSet resultset=null;
		state.setString(1, title);
		resultset=state.executeQuery();
		result=new ArrayList<BookInformation>();
		while(resultset.next()) {
			
			result.add(new BookInformation(resultset.getString("bookTitle"),resultset.getInt("bookLevel"),resultset.getString("bookType"),resultset.getDouble("bookPrice"),resultset.getBoolean("available"),resultset.getString("edition")));
			//System.out.println(resultset.getString("fname"));
			//System.out.println(resultset.getString("salary"));
		}
		resultset.close();
		return result;
	
	
		
		}
	

}

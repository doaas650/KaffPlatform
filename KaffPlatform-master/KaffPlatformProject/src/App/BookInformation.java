package App;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookInformation {

	public String BooksTitle;
	public int level;
	public String For;
	public double price;
	public boolean state;
	public String edition;

	public BookInformation() {
	}

	public BookInformation(String BooksTitle, int level, String For, double price, boolean state, String edition) {

		this.BooksTitle = BooksTitle;
		this.level = level;
		this.state = state;
		this.price = price;
		this.For = For;
		this.edition = edition;
	}
	String r;

	public ArrayList<BookInformation> getTitle(String title) throws SQLException {
		Database.openConnection();
		String selectQuery = "select bookTitle , bookLevel,bookType,bookPrice, available, edition from book where bookTitle= ?";
		PreparedStatement state = Database.getConnection().prepareStatement(selectQuery);
		ArrayList<BookInformation> result = null;
		ResultSet resultset = null;
		
		state.setString(1, title);
		resultset = state.executeQuery();
		
		result = new ArrayList<BookInformation>();
		while (resultset.next()) {

			result.add(new BookInformation(resultset.getString("bookTitle"), resultset.getInt("bookLevel"),
					resultset.getString("bookType"), resultset.getDouble("bookPrice"),
					resultset.getBoolean("available"), resultset.getString("edition")));
		}
		
		resultset.close();
		return result;

	}
	public String res() {
		return r ;
	}

}
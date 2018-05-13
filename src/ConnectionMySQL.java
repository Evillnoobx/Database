import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class ConnectionMySQL {

	public static void main(String[] args) throws Exception {
		getConnection();
	}
	
	public static void post() throws Exception{
		final String name = "Snowflake";
		final String email = "snowflake@example.com";
		final String address = "321 cold";
		
		try{
			Connection conn = getConnection();
			PreparedStatement posted = conn.prepareStatement("INSERT INTO accounts(name, email, address) values("+name+","+email+","+address+")");
			posted.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		finally{
			System.out.println("Insert Complete.");
		}
	}
	
	public static void createTable() throws Exception{
		try{
			Connection conn = getConnection();
		
			PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS logs(id int NOT NULL AUTO_INCREMENT, time_on DATETIME, time_off DATETIME, PRIMARY KEY(id))");
			create.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		finally{
			System.out.println("Function Complete.");
		};
	}
		
	public static Connection getConnection() throws Exception{
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/finley", "guesto", "passwor");
			System.out.println("Connection Success.");
			
			String query = "SELECT * FROM accounts";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				System.out.println("ID: " +rs.getString("id") + " Name: " +rs.getString("name"));
			}
			
			return conn;
			
		} catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}
}

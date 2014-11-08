/**
 * Database connection helper. This class contains SQL connections between the program and the database.
 * It handles all of the necessary queries in the project.
 * @author denizoztreves
 *
 */

import java.sql.*;

public class DatabaseHelper {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/";
	static final String DB_NAME = "TAAS";
	static final String DB_UNAME = "TAAS"; 
	static final String DB_PASS = "SAAT";
	//Database credentials.

	public DatabaseHelper(){

	}

	private Connection connectToDatabase() throws InstantiationException, IllegalAccessException{

		Connection conn = null;
		
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL+DB_NAME, DB_UNAME, DB_PASS);
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}

	private void testDB(String username, String password){
		

		Statement stmt;
		try {
			Connection c = connectToDatabase();
			stmt = c.createStatement();

			String sql;
			sql = "SELECT * FROM Person WHERE mail = '" + username +"'";
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				int id  = rs.getInt("ID");
				String mail = rs.getString("mail");
				String first = rs.getString("name");
				String last = rs.getString("surname");

				//Display values
				System.out.print("ID: " + id);
				System.out.print(", Mail: " + mail);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private boolean authorizeUser(String username, String password){
		boolean result = false;

		return result;
	}


	public static void main(String[] args){

		DatabaseHelper dbh = new DatabaseHelper();
		dbh.testDB("ahmethoca@ku.edu.tr", "ahmethoca");
	}
}

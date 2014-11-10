package Helper;

/**
 * Database connection helper. This class contains SQL connections between the program and the database.
 * It handles all of the necessary queries in the project.
 * @author denizoztreves
 *
 */

import java.sql.*;

import Model.*;

public class DatabaseHelper {

	//Database credentials.
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/";
	static final String DB_NAME = "TAAS";
	static final String DB_UNAME = "TAAS"; 
	static final String DB_PASS = "SAAT";

	// Encryption properties
	private EncryptionHelper bcrypt = null;

	public DatabaseHelper(){

		// Initialize the encyrptor object
		bcrypt = new EncryptionHelper();
	}

	private Connection connectToDatabase() throws InstantiationException, IllegalAccessException{

		Connection conn = null;

		try {
			Class.forName(JDBC_DRIVER).newInstance();
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL+DB_NAME, DB_UNAME, DB_PASS);

		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}



	/**
	 * @category DB Transaction
	 * @param username
	 * @return
	 */

	private String getEncryptedPasswordFromDB(String username){
		Connection c;
		String res = "";
		try {
			c = connectToDatabase();
			String sql = "Select * from Person where mail=?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet result = ps.executeQuery();
			int count = 0;

			while(result.next()) {
				if (count>1){
					res = "";
					break;
				}
				res = result.getString("password");
				count++;	
			}
			c.close();
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

		return res;

	}

	/**
	 *  Authirizes the user from login page
	 *  @category Action
	 * @param username
	 * @param plainPassword
	 * @return 
	 */

	public boolean authorizeUser(String username, String plainPassword){
		boolean result = false;
		// get the username from db
		// check if it is equal
		// check passwords
		String encryptedPassFromDB = getEncryptedPasswordFromDB(username);

		if (!username.isEmpty() && !encryptedPassFromDB.isEmpty())
		{	
			if(bcrypt.checkpw(plainPassword, encryptedPassFromDB)){
				result = true;
			}
		}


		return result;
	}


	//select d.ID, i.ID from department as d join instructor as i on d.`ID` = i.`Department_ID` where i.`Department_ID` =1;

	public Department getDepartmentInformation(int dept_id){

		Connection c;
		Department dept = null;
		try {
			c = connectToDatabase();
			String sql = "Select * from Department where id=?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, dept_id);

			ResultSet result = ps.executeQuery();

			while(result.next()) {
				String n = result.getString("department_name");
				String code = result.getString("code");
				String f = result.getString("faculty");

				dept = new Department(dept_id, n, code, f);
			}
			
			c.close();
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

		return dept;

	}

	public Instructor getInstructorFromID(int personID){
		Connection c;
		Instructor ins = null;
		try {
			c = connectToDatabase();
			String sql = "Select * from Instructor where person_id=?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, personID);

			ResultSet result = ps.executeQuery();

			while(result.next()) {
				int instrID = result.getInt("ID");
				int deptID = result.getInt("department_ID");
				Department d = getDepartmentInformation(deptID);
				ins = new Instructor(instrID, personID,d);
				
			}
			
			c.close();
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

		return ins;

	}

	/**
	 * Returns the user after authorization from it's username
	 * @param username
	 * @return
	 */
	public Person getAuthorizedPerson(String username){

		Connection c;
		Person p = null;
		try {
			c = connectToDatabase();
			String sql = "select count(id) as count, Person.*  from Person where mail =?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
		
			int count = 1 ; // rs.getInt(1);
			
			if(count > 1){
				System.err.println("ERROR:Bu mail ile birden fazla kullanici var");
				System.exit(1);
			}else{
				while(rs.next()){
					int personID = rs.getInt("ID");
					String fname = rs.getString("name");
					String lname = rs.getString("surname");
					String mail = rs.getString("mail");
					if(rs.getInt("jobType") == 1) // Person is an Instructor
					{
						boolean isAdmin = rs.getBoolean("isAdmin");
						p = new Person(personID, fname, lname, mail, isAdmin, JobType.INSTRUCTOR);
					}else { // Person is an Assistant
						
					}
				}
			}

			c.close();
		}catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

		return p;
	}
}

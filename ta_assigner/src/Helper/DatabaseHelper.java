package Helper;

/**
 * Database connection helper. This class contains SQL connections between the program and the database.
 * It handles all of the necessary queries in the project.
 * @author denizoztreves
 *
 */

import java.sql.*;
import java.util.ArrayList;

import Model.*;

public class DatabaseHelper {

	//Database credentials.
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/";
	static final String DB_NAME = "TAAS";
	static final String DB_UNAME = "TAAS"; 
	static final String DB_PASS = "SAAT";

	private static int selectedYear = 2014;
	private static String selectedSemester = "FALL";
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


	public ArrayList<Course> getTeachingInformationForInstructor(int instrID){
		Connection c;
		ArrayList<Course> coursesHistory = new ArrayList<Course>();
		try {
			c = connectToDatabase();
			String sql = "Select Course_ID,Section_ID from teaches where Instructor_ID=?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, instrID);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				int courseID = rs.getInt("Course_ID");
				int sectionID = rs.getInt("Section_ID");

				Course course = getCourseInfoFromID(courseID);

				coursesHistory.add(course);
			}
			c.close();

		}catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

		return coursesHistory;
	}

	public ArrayList<AcademicStuff> getEventsForACourse(int cID){ // TODO
		Connection c;
		ArrayList<AcademicStuff> as = new ArrayList<AcademicStuff>();
		AcademicStuff a = null;
		try {
			c = connectToDatabase();
			String sql = "select * from section where Course_ID =? and semester =? AND year =?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cID);
			ps.setString(2, selectedSemester);
			ps.setInt(3, selectedYear);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				a = new AcademicStuff();
				a.sectionNumber = rs.getInt("sectionNumber");
				
				
				// TODO : burada sikinti var cok if elseif 
				Academics ac = Academics.getAcademicStuff(rs.getInt("academicType"));
				a.type = ac;
				
				//Getting day
				Day d = Day.getDayFromString(rs.getString("day"));
				TimeSlot ts = TimeSlot.getTimeSlot(rs.getInt("timeslot"));
				Pair<Day,TimeSlot> scheduled = new Pair<Day, TimeSlot>(d, ts);
				a.time = scheduled;
				
				as.add(a); // add this academic stuff to arraylist
			}
			c.close();

		}catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

		return as;
	}

	public Course getCourseInfoFromID(int cID){

		Connection c;
		Course course = null;

		try {
			c = connectToDatabase();
			String sql = "Select * From Course where ID =?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cID);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String title = rs.getString("title");
				String deptCode = rs.getString("Department_Code");
				int courseNumber = rs.getInt("number");
				int asstCount = rs.getInt("assistant_count");

				course = new Course(cID,title,deptCode,courseNumber,asstCount);
			}
			c.close();

		}catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

		return course;
	}

}

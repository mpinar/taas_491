package Model;

import java.util.ArrayList;

public class Instructor extends Person
{
	public int id;
	public Request requests;
	Department department;
	public ArrayList<Course> teaches;
	public Instructor(int inst_id, int personID,Department d){
		super(personID);
		this.id = inst_id;
		department = d;
		teaches = new ArrayList<Course>();
	}

	public void setSuperFields(String fname, String lname, String mail,boolean admin){
		
		super.name = fname;
		super.surname = lname;
		super.username = mail;
		super.isAdmin = admin;
		super.job = JobType.INSTRUCTOR;
	}
	
	
	public String toString(){
		String s =	"Instructor\nID:"+ id+ "\nDepartment: "+department ;
				
				return s;
	}
	
	public void setTeaches(String semester, int year){
		//get the courses this teaching for semester 'fall' 2014
		// add them to arraylist
	}
}


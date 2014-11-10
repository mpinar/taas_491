package Model;

public class Instructor extends Person
{
	public int id;
	public Request requests;
	Department department;
	public Instructor(int inst_id, int personID,Department d){
		super(personID);
		this.id = inst_id;
		department = d;
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
}


package Model;


public class Person
{
	public int id;
	public String name;
	public String surname;
	public String username;
	public String password;
	public JobType job;
	public boolean isAdmin;
	
	public Person(){
		super();
	}
	
	public Person(int pid){
		super();
		id = pid;
	}

	public Person(String fname, String lname, String mail, boolean admin, JobType j){
		name = fname;
		surname = lname;
		username = mail;
		isAdmin = admin;
		
		job = j;
		
	}
	public Person(int id, String fname, String lname, String mail, boolean admin, JobType j ){
		
		this.id = id;
		name = fname;
		surname = lname;
		username = mail;
		isAdmin = admin;
		
		job = j;
	}
	
	public String toString(){
		String s = "Person\nID: "+id+"\nname: "+name+"\nsurname: "+surname+"\nusername: "+username+"\nJob: "+job+"\nisAdmin: "+isAdmin;
		return s;
	}
}


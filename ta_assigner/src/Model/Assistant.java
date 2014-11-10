package Model;

public class Assistant extends Person
{
	
	public int id;
	public Instructor advisor;	
	public Course schedule;
	public Course academicBackground;
	public Course teachingBackground;
	public Request requests;
	public Department department;
	
	public Assistant(){
		super();
	}

}


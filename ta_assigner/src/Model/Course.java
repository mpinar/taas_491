package Model;

public class Course
{
	public int id;
	public String title;
	public String code;
	public int number;
	public int assistantCount;
	public AcademicStuff activities;
	public Assistant assistants;
	public Instructor instructors;
	
	
	public Course(){
		super();
	}


	public Course(int id, String title, String deptCode, int courseNumber,int asstCount) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.title = title;
		code = deptCode;
		number = courseNumber;
		assistantCount = asstCount;
		
	}

}


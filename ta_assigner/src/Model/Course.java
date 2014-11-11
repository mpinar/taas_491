package Model;

import java.util.List;

public class Course
{
	public int id;
	public String title;
	public String code;
	public int number;
	public int assistantCount;
	public int year;
	public String semester;
	public List<AcademicStuff> activities;
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

	public String toString(){
		String s = null;

		s = "["+code+" "+number+" - " + title + "]";
		return s; 
	}
}


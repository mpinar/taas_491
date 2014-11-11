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

	public int setMaxAssistantCount(){
		int max = 0;
		
		int activityCount = activities.size();
		
		if(activityCount<2){
			max =1;
		}else if(activityCount <3){
			max = 2;
			
		}else if(activityCount<5){
			max = 3;
			
		}else if(activityCount <8){
			max = 4;
			
		}else if(activityCount <10){
			max = 6;
			
		}else if(activityCount >10){
			max = 8;
			
		}
		return max;
	}
	public String toString(){
		String s = null;

		s = "["+code+" "+number+" - " + title + "]";
		return s; 
	}
}


package Model;

public enum JobType
{
	SUPER_INSTRUCTOR,INSTRUCTOR, ASSISTANT;


	public static JobType getJobType(int i){

		JobType j = null;
		switch (i) {
		case 1:
			j = INSTRUCTOR;
			break;
		case 2:
			j = SUPER_INSTRUCTOR;
			break;
		case 3:
			j = ASSISTANT;
			break;

		default:
			System.out.println("Jobtype Error");
			break;
		}
		return j;
	}
}

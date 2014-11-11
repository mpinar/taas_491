package Model;

public enum Day
{
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;


	public static Day getDayFromString (String s){

		Day d = null;

		if(s.compareToIgnoreCase("mon") == 0){
			
			d = MONDAY;
		}else if(s.compareToIgnoreCase("tue") == 0){
			d = TUESDAY;
		}else if(s.compareToIgnoreCase("wed") == 0){
			d = WEDNESDAY;
		}else if(s.compareToIgnoreCase("thu") == 0){
			d = THURSDAY;
		}else if(s.compareToIgnoreCase("fri") == 0){
			d = FRIDAY;
		}else{
			System.out.println("Day error");
		}
		return d;

	}
}

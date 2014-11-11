package Model;

public enum TimeSlot
{
	B1, B2, B3, B4, B5, B6, B7;


	public static TimeSlot getTimeSlot(int t){
		TimeSlot ts = null;

		switch (t) {
		case 1:
			ts = B1;
			break;
		case 2:
			ts = B2;
			break;
		case 3:
			ts = B3;
			break;
		case 4:
			ts = B4;
			break;
		case 5:
			ts = B5;
			break;
		case 6:
			ts = B6;
			break;
		case 7:
			ts = B7;
			break;

		default:
			System.out.println("Timeslot error");
			break;
		}
		return ts;
	}
}

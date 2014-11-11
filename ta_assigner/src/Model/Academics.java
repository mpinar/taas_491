package Model;

public enum Academics {

	LECTURE,PS,LAB;

	public static Academics getAcademicStuff(int a){
		Academics ac = null;


		switch (a) {
		case 1: //Lecture
			ac = LECTURE;
			break;
		case 2: // Lab
			ac = LAB;
			break;
		case 3: //PS
			ac = PS;
			break;

		default:
			System.out.println("Academics error");
			break;
		}

		return ac;
	}
}

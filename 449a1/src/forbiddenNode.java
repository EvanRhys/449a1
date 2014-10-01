
public class forbiddenNode extends Node{

	public forbiddenNode(char mach, char task) {
		if ((mach >= 49) && (mach <= 56))
			computer = mach;
		else 
			System.out.println("Error while parsing input file");
		if ((task >= 65) && (task <= 72))
			super.task = task;
		else 
			System.out.println("Error while parsing input file");
		super.next = null;
	}
}

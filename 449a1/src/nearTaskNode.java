
public class nearTaskNode extends Node{

	public nearTaskNode(char task, char secondTask) {
		if (task == secondTask)
			System.out.println("Error while parsing input file");
		if ((task >= 65) && (task <= 72))
			super.task = task;
		else 
			System.out.println("Error while parsing input file");
		if ((secondTask >= 65) && (secondTask <= 72))
			super.secondTask = secondTask;
		else 
			System.out.println("Error while parsing input file");
		super.next = null;
	}
}

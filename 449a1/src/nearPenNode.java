/***************
 * CPSC 449 Assignment 1 
 * Too-Near Penalty Node subclass
 * @author team 1-1 
 * for the Too-near penalty data structure
 */
public class nearPenNode extends Node{
/****
 * @param task First task being added, i in (i,i+1,x)
 * @param secondTask Second task being added, i+1 in (i,i+1,x)
 * @param penalty Penalty being added, x in (i,i+1,x)
 * @throws ParsingInputException
 * @throws InvalidTaskException
 */
	public nearPenNode(char task, char secondTask, int penalty) throws ParsingInputException{
		if (task == secondTask)
			throw new ParsingInputException();
		if ((task >= 65) && (task <= 72))
			super.task = task;
		else 
			throw new InvalidTaskException();
		if ((secondTask >= 65) && (secondTask <= 72))
			super.secondTask = secondTask;
		else 
			throw new InvalidTaskException();
		super.penalty = penalty;
		super.next = null;
	}

}

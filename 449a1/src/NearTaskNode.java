/***************
 * CPSC 449 Assignment 1 
 * Too-Near Task Node subclass
 * @author team 1-1 
 * for the Too-near Task data structure
 */
public class NearTaskNode extends Node{
/****
 * @param task First task being added i, in (i, i+1)
 * @param secondTask Second task being added i+1, in (i, i+1)
 * @throws InvalidInputException thrown when either task is invalid, or both tasks are the same.
 */
	public NearTaskNode(char task, char secondTask) throws InvalidInputException {
		if (task == secondTask)
			throw new InvalidInputException();
		if ((task >= 65) && (task <= 72))
			super.task = task;
		else 
			throw new InvalidInputException();
		if ((secondTask >= 65) && (secondTask <= 72))
			super.secondTask = secondTask;
		else 
			throw new InvalidInputException();
		super.next = null;
	}
}

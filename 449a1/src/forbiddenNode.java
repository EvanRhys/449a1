/***************
 * CPSC 449 Assignment 1 
 * Forbidden Node subclass
 * @author team 1-1 
 * for the forbidden machine data structure
 */
public class forbiddenNode extends Node{
/****
 * @param mach the machine being added
 * @param task the task being added
 * @throws InvalidInputException thrown when invalid task or machine number
 */
	public forbiddenNode(char mach, char task) throws InvalidInputException {
		if ((mach >= 49) && (mach <= 56))
			computer = mach - 48;
		else 
			throw new InvalidInputException();
		if ((task >= 65) && (task <= 72))
			super.task = task;
		else 
			throw new InvalidInputException();
		super.next = null;
	}
}

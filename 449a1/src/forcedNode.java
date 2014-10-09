/***************
 * CPSC 449 Assignment 1 
 * Forced Node subclass
 * @author team 1-1 
 * for the Forced Partial Assignment data structure
 */
public class forcedNode extends NodeWComp{
/****
 * @param mach machine number being added
 * @param task task being added
 * @throws InvalidInputException thrown when either the task or machine number is invalid
 */
	public forcedNode(char mach, char task)throws InvalidInputException{
		if ((mach >= 49) && (mach <= 56))
			computer = mach - 48;
		else 
			throw new InvalidInputException();
		if ((task >= 65) && (task <= 72))
			super.task = task;
		else 
			throw new InvalidInputException();
	}
}

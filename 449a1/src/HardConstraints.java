/***************
 * CPSC 449 Assignment 1 
 * Hard Constraints class
 * @author team 1-1 
 * Used to determine if validity of a task/machine assignment
 */
public class HardConstraints {
	private Node[] forcedPart;// forcedPart; 
	private Node[] forbiddenMachine;//forbiddenMachine;
	private Node[] tooNearTasks;// tooNearTasks
	
	public HardConstraints(Node[] A, Node[] B, Node[] C){
		forcedPart = A;
		forbiddenMachine = B;
		tooNearTasks = C;
	}
	/****
	 * @param mach current machine number
	 * @param cur current task
	 * @param las task at machine number -1
	 * @return true if current machine/task assignment is valid
	 * @return false if current machine/task assignment is invalid
	 */
	public boolean constraint(int mach, char cur, char las){
		if (forbiddenMachine(mach, cur) && tooNearTasks(cur, las))
			return true;
		else
			return false;
		
	}
	/****
	 * @param mach current machine number
	 * @return task if there is a forced partial assignment for the machine number
	 * @return char X if there is no assignment
	 */
	public char forcePartAssignment(int mach){
		Node current = forcedPart[mach - 1];
		
		if (current != null)
			return current.getTask();
		else
			return 'X';		
	}	
	/****
	 * @param mach current machine number
	 * @param curr current task
	 * @return true if task curr isn't forbidden to be on machine mach
	 * @return false if task curr is forbidden to be on machine mach
	 */
	public boolean forbiddenMachine(int mach, char curr){
		Node current= forbiddenMachine[mach - 1];
	
		while (current != null)
		{
			if (curr == current.getTask())
				return false;
			current = current.getNext();		
		}
		return true;
	}	
	/****
	 * @param curr current task
	 * @param last task before current task
	 * @return true if task curr isn't too near to last
	 * @return false if task curr is too near to last
	 */
	public boolean tooNearTasks(char curr, char last){ 
		Node current= tooNearTasks[(last-1) %8]; 
		while (current != null)
		{
			if (curr == current.getSecondTask())
				return false;
			current = current.getNext();		
		}
		return true;
	}
}

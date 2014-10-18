/***************
 * CPSC 449 Assignment 1 
 * Soft Constraints class
 * @author team 1-1 
 * Used to calculate all penalties 
 * 
 * Version 2
 * changed SoftConstraints, toonearpen1/2
 */
import java.util.ArrayList;

public class SoftConstraints {
	int[][] machinePenalty;
	ArrayList<int[]> tooNearPenalty;

	public SoftConstraints(Node [] A, int[][] B){
		int i;
		int [] penalty; //changed since new array is created in the loop, toonearpen1/2
		NodeWPen current;
		tooNearPenalty = new ArrayList<int[]>();
		
		for(i = 0; i < 8; i++){
			if (A[i] != null){
				current = (NodeWPen) A[i];
				while(current != null){
					penalty = new int[3]; //changed to create new array every time, toonearpen1/2
					penalty[0] = (current.getTask() - 1) % 8;
					penalty[1] = (current.getSecondTask() - 1) % 8;
					penalty[2] = current.getPenalty();
					tooNearPenalty.add(penalty);
					current = (NodeWPen)current.getNext();
				}				
			}
		}		
		machinePenalty = B;
	}
	public SoftConstraints(ArrayList<int[]> A, int[][] B){
		machinePenalty = B;
		tooNearPenalty = A;
	}
	//Precond: Class has been initialized
	//Returns a penalty based on assigning a task to a specific machine
	public int getMachinePenalty(int a, char b){
		int i = machinePenalty[a-1][((Character.getNumericValue(b)-10))];
		return(i);
	}
	
	//precond: Class has been initialized
	//Returns a penalty based on assigning 2 tasks on machines right next to eachother
	public int getTooNearPenalty(char a, char b){
		int current = (Character.getNumericValue(a)-10);
		int next = (Character.getNumericValue(b)-10);
		int i = 0;
		for(int[] penalty : tooNearPenalty){
			if(penalty[0] == current){			//if the first task on our list is current
				if(penalty[1] == next){			//and the second is next
					i = penalty[2];				//apply penalty
				}
			}
		}
		return(i);
	}
	/****
	 * @param mach current machine number
	 * @param curr current task
	 * @param last task at machine number - 1
	 * @return total penalty count
	 * 
	 * one method that calculates and returns penalty calculated
	 */
	public int getPenalty(int mach, char curr, char last){
		int penalty;
		
		penalty = getMachinePenalty(mach, curr);
		penalty = penalty + getTooNearPenalty(last, curr);
		return penalty;
	}
}

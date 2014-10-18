/***************
 * CPSC 449 Assignment 1 
 * Node class
 * @author team 1-1 
 * Super class of all of the different subclass nodes
 * Allows simpler design in the program
 * Contains all getters and setters
 */
public class Node {
	protected Node next;		
	protected char task;
	protected char secondTask;

	
	public Node(){}
	public Node(Node next){
		this.next = next;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public char getTask() {
		return task;
	}
	public void setTask(char task) {
		this.task = task;
	}
	public char getSecondTask() {
		return secondTask;
	}
	public void setSecondTask(char secondTask) {
		this.secondTask = secondTask;
	}
}

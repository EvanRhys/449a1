
public class Node {
	public Node next;
	public char computer;
	public char task;
	public char secondTask;
	public int penality;
	
	public Node(){
		
	}
	public Node(Node next){
		this.next = next;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public char getComputer() {
		return computer;
	}
	public void setComputer(char computer) {
		this.computer = computer;
	}
	public int getPenality() {
		return penality;
	}
	public void setPenality(int penality) {
		this.penality = penality;
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

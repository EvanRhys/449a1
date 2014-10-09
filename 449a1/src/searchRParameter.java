
public class searchRParameter {
	private String remaining;
	private String current;
	private int machineNumb;
	private int penalty;
	private char lastTask;
	
	public searchRParameter(){}
	public searchRParameter(String remaining, String current, int machineNumb, int penalty, char lastTask){
		this.remaining = remaining;
		this.current = current;
		this.machineNumb = machineNumb;
		this.penalty = penalty;
		this.lastTask = lastTask;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public int getMachineNumb() {
		return machineNumb;
	}
	public void setMachineNumb(int machineNumb) {
		this.machineNumb = machineNumb;
	}
	public int getPenalty() {
		return penalty;
	}
	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
	public char getLastTask() {
		return lastTask;
	}
	public void setLastTask(char lastTask) {
		this.lastTask = lastTask;
	}
	public String getRemaining(){
		return remaining;
	}
	public void setRemaining(String remaining) {
		this.remaining = remaining;
	}
}


public class SimpleObject {
	private boolean boolVar = false;
	private char charVar = 'a';
	private int intVar = 0;
	private double doubVar = 0.0;
	
	public SimpleObject(){}
	public SimpleObject(boolean boolVar, char charVar, int intVar, double doubVar){
		this.boolVar = boolVar;
		this.charVar = charVar;
		this.intVar = intVar;
		this.doubVar = doubVar;
	}	
	public void setBoolVar(boolean boolVar){
		this.boolVar = boolVar;
	}
	public void setCharVar(char charVar){
		this.charVar = charVar;
	}
	public void setIntVar(int intVar){
		this.intVar = intVar;
	}
	public void setDoubVar(double doubVar){
		this.doubVar = doubVar;
	}
	private int Double(int a)
	{
		return a*a;
	}
}

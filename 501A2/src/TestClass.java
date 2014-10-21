
public class TestClass {
	private int att = 1;
	public TestClass(){}
	public TestClass(int i){att = i;}
	public int getAtt() throws NullPointerException{
		return att;
	}
}

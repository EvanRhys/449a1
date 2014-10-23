
public class TestClass {
	private int att = 1;
	private TestNode nodes;
	private ClassA V;
	
	public TestClass(){
		nodes = new TestNode();
		V = new ClassA();
	}
	public TestClass(int i){att = i;}
	public int getAtt() throws NullPointerException{
		return att;
	}
}


public class ReferenceObject {
	private SimpleObject ref1;
	private SimpleObject ref2;
	public ReferenceObject(){
		ref1 = new SimpleObject(true, 'b', 4, 5.4);
		ref2 = new SimpleObject(false, 'c', 5, 4.5);
	}
	public ReferenceObject(boolean boolVar, char charVar, int intVar, double doubVar){
		ref1 = new SimpleObject(boolVar, charVar, intVar, doubVar);
	}
	public void setRef1(char varChar, int varInt){
		ref1.setCharVar(varChar);
		ref1.setIntVar(varInt);
	}
	public void setRef1(SimpleObject SO){
		ref1 = SO;
	}
	public void setRef2(SimpleObject SO){
		ref2 = SO;
	}
	public SimpleObject get(int ref)
	{
		if (ref == 1)
			return ref1;
		else if(ref == 2)
			return ref2;
		return null;
	}
}

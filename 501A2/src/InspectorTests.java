import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class InspectorTests {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private Object objInspect = null;
	private Method inspectMethod = null;
		
	@Before
	public void setUp(){
		Class objInspectClass = null;
		try{
			objInspectClass = Class.forName("ObjectInspector");
			objInspect = objInspectClass.newInstance(); 
		}catch (Exception e){
			System.out.println("Unable create instance of your object inspector");
		}
		try{
			Class[] param = { Object.class, boolean.class };
			inspectMethod = objInspectClass.getDeclaredMethod("inspect", param);
		}catch (Exception e){
			System.out.println("Unable to find inspection method");
		}
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}
	@After
	public void tearDown(){
		objInspect = null;
		inspectMethod = null;
		System.setOut(null);
		System.setErr(null);
	}
	@Test
	public void test() {
		try{
			Object[] param = { "ClassA", new Boolean(false)};
			inspectMethod.invoke(objInspect, param);
			String output = "inside inspector: ClassA (recursive = false)\n" +
							"Method: getVal:\n" +
							"	Return Type: int\n"+
							"	Modifiers: 1\n"+
							"Method: printSomething:\n"+
							"	Return Type: void\n"+
							"	Modifiers: 2\n"+
							"Method: run:\n"+
							"	Return Type: void\n"+
							"	Modifiers: 1\n"+
							"Method: toString:\n"+
							"	Return Type: java.lang.String\n"+
							"	Modifiers: 1\n"+
							"Method: setVal:\n"+
							"	Parameter: int\n"+
							"	Exception: java.lang.Exception\n"+
							"	Return Type: void\n"+
							"	Modifiers: 1\n"+
							"Constructor: ClassA:\n"+
							"	Modifiers: 1\n"+
							"Constructor: ClassA:\n"+
							"	Parameter: int\n"+
							"	Modifiers: 1\n"+
							"Field: val = 3\n";
			assertEquals(output, outContent.toString());
		}catch(Exception e){
		}
	
	}

}

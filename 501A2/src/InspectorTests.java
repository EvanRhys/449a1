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
	private Class inspectClass = null;
		
	@Before
	public void setUp(){
		try{
			inspectClass = Class.forName("Inspector");
			objInspect = inspectClass.newInstance(); 
		}catch (Exception e){
			System.out.println("Unable to create instance of your object inspector");
		}

		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}
	@After
	public void tearDown(){
		objInspect = null;
		inspectClass = null;
		System.setOut(null);
		System.setErr(null);
	}
	@Test
	public void methodParam(){
		Class testClass = null;
		Method testMethod = null;
		Method inspectMethod = null;
		
		try{
			Class[] param = { Method.class };
			inspectMethod = inspectClass.getDeclaredMethod("getMethodParameterTypes", param);
		}catch(Exception e){
			fail("Unable to get inspector method");
		}
		try{
			testClass = Class.forName("TestSubClass");
		}catch(Exception e){
			e.printStackTrace();
			fail("Unable to create test object " + errContent.toString());
		}
		try{
			Class[] param = { int.class };
			testMethod = testClass.getDeclaredMethod("setAtt2", param);
		}catch(Exception e){
			e.printStackTrace();
			fail("Unable get test Method" + errContent.toString());
		}
		try{
			Object[] param = { testMethod };
			inspectMethod.invoke(objInspect, param);		
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at invoke " + errContent.toString());
		}
		try{
			String results = "	Parameter: int\n";
			assertEquals(results, outContent.toString());
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at assert" + errContent.toString());
		}
	}
	@Test
	public void methodException(){
		Class testClass = null;
		Method testMethod = null;
		Method inspectMethod = null;
		
		try{
			Class[] param = { Method.class };
			inspectMethod = inspectClass.getDeclaredMethod("getMethodExceptions", param);
		}catch(Exception e){
			fail("Unable to get inspector method " + errContent.toString());
		}
		try{
			testClass = Class.forName("TestClass");
		}catch(Exception e){
			e.printStackTrace();
			fail("Unable to create test object " + errContent.toString());
		}
		try{
			Class[] param = {};
			testMethod = testClass.getDeclaredMethod("getAtt", param);
		}catch(Exception e){
			e.printStackTrace();
			fail("Unable get test Method " + errContent.toString());
		}
		try{
			Object[] param = { testMethod };
			inspectMethod.invoke(objInspect, param);		
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at invoke " + errContent.toString());
		}
		try{
			String results = "	Exception: java.lang.NullPointerException\n";
			assertEquals(results, outContent.toString());
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at assert" + errContent.toString());
		}
	}
	@Test
	public void methodReturn(){
		Class testClass = null;
		Method testMethod = null;
		Method inspectMethod = null;
		
		try{
			Class[] param = { Method.class };
			inspectMethod = inspectClass.getDeclaredMethod("getMethodReturnType", param);
		}catch(Exception e){
			fail("Unable to get inspector method " + errContent.toString());
		}
		try{
			testClass = Class.forName("TestClass");
		}catch(Exception e){
			e.printStackTrace();
			fail("Unable to create test object " + errContent.toString());
		}
		try{
			Class[] param = {};
			testMethod = testClass.getDeclaredMethod("getAtt", param);
		}catch(Exception e){
			e.printStackTrace();
			fail("Unable get test Method " + errContent.toString());
		}
		try{
			Object[] param = { testMethod };
			inspectMethod.invoke(objInspect, param);		
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at invoke " + errContent.toString());
		}
		try{
			String results = "	Return Type: int\n";
			assertEquals(results, outContent.toString());
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at assert" + errContent.toString());
		}
	}
	@Test
	public void methodMod(){
		Class testClass = null;
		Method testMethod = null;
		Method inspectMethod = null;
		
		try{
			Class[] param = { Method.class };
			inspectMethod = inspectClass.getDeclaredMethod("getMethodModifiers", param);
		}catch(Exception e){
			fail("Unable to get inspector method " + errContent.toString());
		}
		try{
			testClass = Class.forName("TestClass");
		}catch(Exception e){
			e.printStackTrace();
			fail("Unable to create test object " + errContent.toString());
		}
		try{
			Class[] param = {};
			testMethod = testClass.getDeclaredMethod("getAtt", param);
		}catch(Exception e){
			e.printStackTrace();
			fail("Unable get test Method " + errContent.toString());
		}
		try{
			Object[] param = { testMethod };
			inspectMethod.invoke(objInspect, param);		
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at invoke " + errContent.toString());
		}
		try{
			String results = "	Modifiers: 1\n";
			assertEquals(results, outContent.toString());
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at assert" + errContent.toString());
		}
	}
	@Test 
	public void methodsAll(){
		Class testClass = null;
		Method inspectMethod = null;
		
		try{
			Class[] param = { Class.class };
			inspectMethod = inspectClass.getDeclaredMethod("inspectMethods", param);
		}catch(Exception e){
			fail("Unable to get inspector method " + errContent.toString());
		}
		try{
			testClass = Class.forName("TestClass");
		}catch(Exception e){
			e.printStackTrace();
			fail("Unable to create test object " + errContent.toString());
		}
		try{
			Object[] param = { testClass };
			inspectMethod.invoke(objInspect, param);		
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at invoke " + errContent.toString());
		}
		try{
			String results = "Method: getAtt:\n" +
					"	Exception: java.lang.NullPointerException\n" +
					"	Return Type: int\n" +
					"	Modifiers: 1\n";
			assertEquals(results, outContent.toString());
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at assert" + errContent.toString());
		}
	}
	@Test
	public void conParam(){
		Class testClass = null;
		Constructor testCon = null;
		Method inspectMethod = null;
		
		try{
			Class[] param = { Constructor.class };
			inspectMethod = inspectClass.getDeclaredMethod("getConstructorParameterTypes", param);
		}catch(Exception e){
			fail("Unable to get inspector Method " + errContent.toString());
		}
		try{
			Class[] param = { int.class };
			testClass = Class.forName("TestClass");
			testCon = testClass.getDeclaredConstructor(param);
		}catch(Exception e){
			e.printStackTrace();
			fail("Unable to create test object " + errContent.toString());
		}
		try{
			Object[] param = { testCon };
			inspectMethod.invoke(objInspect, param);		
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at invoke " + errContent.toString());
		}
		try{
			String results = "	Parameter: int\n";
			assertEquals(results, outContent.toString());
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at assert" + errContent.toString());
		}
	}
	@Test 
	public void  conMod(){
		Class testClass = null;
		Constructor testCon = null;
		Method inspectMethod = null;
		
		try{
			Class[] param = { Constructor.class };
			inspectMethod = inspectClass.getDeclaredMethod("getConstructorModifiers", param);
		}catch(Exception e){
			fail("Unable to get inspector method " + errContent.toString());
		}
		try{
			Class[] param = { int.class };
			testClass = Class.forName("TestClass");
			testCon = testClass.getDeclaredConstructor(param);
		}catch(Exception e){
			e.printStackTrace();
			fail("Unable to create test object " + errContent.toString());
		}
		try{
			Object[] param = { testCon };
			inspectMethod.invoke(objInspect, param);		
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at invoke " + errContent.toString());
		}
		try{
			String results = "	Modifiers: 1\n";
			assertEquals(results, outContent.toString());
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at assert" + errContent.toString());
		}
	}
	@Test
	public void conAll(){
		Class testClass = null;
		Method inspectMethod = null;
		
		try{
			Class[] param = { Class.class };
			inspectMethod = inspectClass.getDeclaredMethod("inspectConstructors", param);
		}catch(Exception e){
			fail("Unable to get inspector method " + errContent.toString());
		}
		try{
			testClass = Class.forName("TestClass");
		}catch(Exception e){
			e.printStackTrace();
			fail("Unable to create test object " + errContent.toString());
		}
		try{
			Object[] param = { testClass };
			inspectMethod.invoke(objInspect, param);		
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at invoke " + errContent.toString());
		}
		try{
			String results1 = "Constructor TestClass:\n" +
							"	Modifiers: 1\n" +
							"Constructor TestClass:\n" + 
							"	Parameter: int\n" +
							"	Modifiers: 1\n";
			String results2 = "Constructor TestClass:\n" +
					"	Parameter: int\n" +
					"	Modifiers: 1\n" +
					"Constructor TestClass:\n" +					
					"	Modifiers: 1\n";
			assertTrue(outContent.toString().equals(results1) || outContent.toString().equals(results2));
		}catch(Exception e){
			e.printStackTrace();
			fail("Failed at assert" + errContent.toString());
		}
	}
}

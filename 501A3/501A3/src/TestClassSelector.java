import static org.junit.Assert.*;

import java.lang.reflect.*;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;


public class TestClassSelector {

	private Object obj;
	private Class objClass;
	
	@Before
	public void setUp(){
		obj = new ClassSelector();
		objClass = obj.getClass();
	}
	@After
	public void tearDown(){
		obj = null;
		objClass = null;
	}
	@Test
	public void testHandleBool() {
		String input = "True";
		Scanner in = new Scanner(input);
		Class [] param = {Scanner.class};
		Object [] mParam = {in}; 
		try{
			Method m = objClass.getDeclaredMethod("handleBoolean", param);
			m.setAccessible(true);
			boolean results = (boolean) m.invoke(obj, mParam);
			assertEquals(results, true);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			fail();
		}
	}
	@Test
	public void testHandleChar() {
		String input = "T";
		Scanner in = new Scanner(input);
		Class [] param = {Scanner.class};
		Object [] mParam = {in}; 
		try{
			Method m = objClass.getDeclaredMethod("handleChar", param);
			m.setAccessible(true);
			char results = (char) m.invoke(obj, mParam);
			assertEquals(results, 'T');
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			fail();
		}
	}
	@Test
	public void testHandleInt() {
		String input = "3";
		Scanner in = new Scanner(input);
		Class [] param = {Scanner.class};
		Object [] mParam = {in}; 
		try{
			Method m = objClass.getDeclaredMethod("handleInt", param);
			m.setAccessible(true);
			int results = (int) m.invoke(obj, mParam);
			assertEquals(results, 3);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			fail();
		}
	}
	@Test
	public void testHandleDobule() {
		String input = "3.454";
		Scanner in = new Scanner(input);
		Class [] param = {Scanner.class};
		Object [] mParam = {in}; 
		try{
			Method m = objClass.getDeclaredMethod("handleDouble", param);
			m.setAccessible(true);
			double results = (double) m.invoke(obj, mParam);
			assertEquals(results, 3.454, 1);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			fail();
		}
	}

}

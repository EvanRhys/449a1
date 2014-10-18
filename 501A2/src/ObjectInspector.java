/*==========================================================================
File: ObjectInspector.java
Purpose:Demo Object inspector for the Asst2TestDriver

Location: University of Calgary, Alberta, Canada
Created By: Jordan Kidney
Created on:  Oct 23, 2005
Last Updated: Oct 23, 2005

***********************************************************************
If you are going to reproduce this code in any way for your asignment 
rember to include my name at the top of the file toindicate where you
got the original code from
***********************************************************************


========================================================================*/

import java.util.*;
import java.lang.reflect.*;


public class ObjectInspector
{
    public ObjectInspector() { }

    //-----------------------------------------------------------
    public void inspect(Object obj, boolean recursive)
    {
	Vector objectsToInspect = new Vector();
	Class ObjClass = obj.getClass();

	System.out.println("inside inspector: " + obj + " (recursive = "+recursive+")");
	
	inspectMethods(obj, ObjClass);
	//inspect the current class
	inspectFields(obj, ObjClass, objectsToInspect);
	
	if(recursive)
	    inspectFieldClasses( obj, ObjClass, objectsToInspect, recursive);
	   
    }
    /*
     * name of declaring class
     * immediate superclass
     * interfaces the class implements
     * methods
     * 	-exceptions
     * 	-parameter types
     * 	-return type
     * 	-modifiers
     * constructors
     * 	-parameter types
     * 	-modifiers
     * fields
     * 	-types
     * 	-modifier
     * 	-current value, if object and recursive = false print pointer value
     */
    //-----------------------------------------------------------
    private void inspectFieldClasses(Object obj,Class ObjClass,
				     Vector objectsToInspect,boolean recursive)
    {
	
	if(objectsToInspect.size() > 0 )
	    System.out.println("---- Inspecting Field Classes ----");
	
	Enumeration e = objectsToInspect.elements();
	while(e.hasMoreElements())
	    {
		Field f = (Field) e.nextElement();
		System.out.println("Inspecting Field: " + f.getName() );
		
		try
		    {
			System.out.println("******************");
			inspect( f.get(obj) , recursive);
			System.out.println("******************");
		    }
		catch(Exception exp) { exp.printStackTrace(); }
	    }
    }
    //-----------------------------------------------------------
    private void inspectFields(Object obj,Class ObjClass,Vector objectsToInspect)
  
    {
	
	if(ObjClass.getDeclaredFields().length >= 1)
	    {
		Field f = ObjClass.getDeclaredFields()[0];
		
		f.setAccessible(true);
		
		if(! f.getType().isPrimitive() ) 
		    objectsToInspect.addElement( f );
		
		try
		    {
			
			System.out.println("Field: " + f.getName() + " = " + f.get(obj));
		    }
		catch(Exception e) {}    
	    }

	if(ObjClass.getSuperclass() != null)
	    inspectFields(obj, ObjClass.getSuperclass() , objectsToInspect);
    }
    /* methods
     * 	-parameter types
     * 	-exceptions
     * 	-return type
     *	-modifiers
     */
    private void getMethodParameterTypes(Method m){
		if(m.getParameterTypes().length >= 1){
			for(int i = 0; i < m.getParameterTypes().length; i++){
				Class c = m.getParameterTypes()[i];
				System.out.println("	Parameter: " + c.getName());
			}
		}
    }
    private void getMethodExceptions(Method m){
    	if(m.getExceptionTypes().length >= 1){
    		for(int i = 0; i < m.getExceptionTypes().length; i++){
    			Class c = m.getExceptionTypes()[i];
    			System.out.println("	Exception: " + c.getName());
    		}
    	}
    }
    private void getMethodReturnType(Method m){
    	Class c = m.getReturnType();
    	System.out.println("	Return Type: " + c.getName());
    }
    private void getMethodModifiers(Method m){
    	int numbModifiers = m.getModifiers();
    	System.out.println("	Modifiers: " + numbModifiers);
    }
    private void inspectMethods(Object obj, Class ObjClass)
    {
    	if (ObjClass.getDeclaredMethods().length >= 1){
    		for (int i = 0; ObjClass.getDeclaredMethods().length > i; i++ ){
    			Method m = ObjClass.getDeclaredMethods()[i];
    			try{
    				System.out.println("Method: " + m.getName() + ";");
    				getMethodParameterTypes(m);
    				getMethodExceptions(m);
    				getMethodReturnType(m);
    				getMethodModifiers(m);
    			}
    			catch(Exception e){}
    		}
    	}
    }
}



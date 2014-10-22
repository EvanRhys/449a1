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


public class Inspector
{
	Vector inspectedObjects;	
    public Inspector() 
    { 
    	inspectedObjects = new Vector();
    }
    /*
     * name of declaring class
     * immediate superclass
     */
    //-----------------------------------------------------------
    public void inspect(Object obj, boolean recursive)
    {
		Vector objectsToInspect = new Vector();
		Vector interfacesToInspect = new Vector();
		Class ObjClass = obj.getClass();
		Class Super = ObjClass.getSuperclass();
	
		inspectedObjects.addElement(ObjClass.getName());
		
		System.out.println("inside inspector: " + ObjClass.getName() + " (recursive = "+recursive+")");
		if(Super != null)
			System.out.println("SuperClass: " + Super.getName());
				
		inspectInterfaces(ObjClass, interfacesToInspect);
		inspectConstructors(ObjClass);
		inspectMethods(ObjClass);		
		//inspect the current class
		//inspectFields(obj, ObjClass, objectsToInspect);
		if(Super != null){
			if(! inspectedObjects.contains(Super.getName())){
				System.out.println("Inspecting superclass of " + ObjClass.getName() + ": ");
				System.out.println("========================================");
				try{
					inspect(Super.newInstance(), recursive);
				}catch(Exception e){
					e.printStackTrace();
				}
				System.out.println("========================================");
			}
		}
		//if(recursive)
	  	//  inspectFieldClasses( obj, ObjClass, objectsToInspect, recursive);
    }
	
    /*
     * fields
     * 	-types
     * 	-modifier
     * 	-current value, if object and recursive = false print pointer value
     */
    //-----------------------------------------------------------
    public void inspectFieldClasses(Object obj,Class ObjClass,
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
    public void inspectFields(Object obj,Class ObjClass,Vector objectsToInspect)  
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
    /*
     * interfaces the class implements 
     */
    public void inspectInterfaces(Class ObjClass, Vector toInspect){
    	Class[] interfaces = ObjClass.getInterfaces();
    	
    	for(int i = 0; i < interfaces.length; i++){
    		toInspect.addElement(interfaces[i]);
    		System.out.println("Interface: " + interfaces[i].getName());
    	}
    }
    /* methods
     * 	-parameter types
     * 	-exceptions
     * 	-return type
     *	-modifiers
     */
    public void getMethodParameterTypes(Method m){
		if(m.getParameterTypes().length >= 1){
			for(int i = 0; i < m.getParameterTypes().length; i++){
				Class c = m.getParameterTypes()[i];
				System.out.println("	Parameter: " + c.getName());
			}
		}
    }
    public void getMethodExceptions(Method m){
    	if(m.getExceptionTypes().length >= 1){
    		for(int i = 0; i < m.getExceptionTypes().length; i++){
    			Class c = m.getExceptionTypes()[i];
    			System.out.println("	Exception: " + c.getName());
    		}
    	}
    }
    public void getMethodReturnType(Method m){
    	Class c = m.getReturnType();
    	System.out.println("	Return Type: " + c.getName());
    }
    public void getMethodModifiers(Method m){
    	int numbModifiers = m.getModifiers();
    	System.out.println("	Modifiers: " + numbModifiers);
    }
    public void inspectMethods(Class ObjClass)
    {
    	if (ObjClass.getDeclaredMethods().length >= 1){
    		for (int i = 0; ObjClass.getDeclaredMethods().length > i; i++ ){
    			Method m = ObjClass.getDeclaredMethods()[i];
    			try{
    				System.out.println("Method: " + m.getName() + ":");
    				getMethodParameterTypes(m);
    				getMethodExceptions(m);
    				getMethodReturnType(m);
    				getMethodModifiers(m);
    			}
    			catch(Exception e){}
    		}
    	}
    }
    /* constructors
    * 	-parameter types
    * 	-modifiers
    */
    public void getConstructorParameterTypes(Constructor con){
		if(con.getParameterTypes().length >= 1){
			for(int i = 0; i < con.getParameterTypes().length; i++){
				Class c = con.getParameterTypes()[i];
				System.out.println("	Parameter: " + c.getName());
			}
		}
    }
    public void getConstructorModifiers(Constructor c){
    	int numbModifiers = c.getModifiers();
    	System.out.println("	Modifiers: " + numbModifiers);
    }
    public void inspectConstructors(Class ObjClass){
    	if(ObjClass.getDeclaredConstructors().length >= 1){
    		for (int i = 0; ObjClass.getDeclaredConstructors().length > i; i++){
    			Constructor c = ObjClass.getDeclaredConstructors()[i];
    			try{
    				System.out.println("Constructor: " + c.getName() + ":");
    				getConstructorParameterTypes(c);
    				getConstructorModifiers(c);
    			}catch (Exception e){}
   			}
   		}
   	}
}




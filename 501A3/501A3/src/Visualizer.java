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
/*
 * Evan Kalynchuk 
 * CPSC 501 Inspector class
 * 
 * 
 */
import java.util.*;
import java.lang.reflect.*;


public class Visualizer
{
	//Includes all classes already inspected
	Vector inspectedObjects;
	//Used to prevent primitive classes from arrays to be inspected
	
    public Visualizer() 
    { 
    	inspectedObjects = new Vector();  	
    }
    /*
     * name of declaring class
     * immediate superclass
     */
    //-----------------------------------------------------------
    //Object Inspector, used to inspect base objects
    public void inspect(Object obj, boolean recursive, boolean full)
    {
		Vector fieldsToInspect = new Vector();
		Vector interfacesToInspect = new Vector();
		Class ObjClass = obj.getClass();
			
		//inspectedObjects.addElement(ObjClass.getName());
		
		System.out.println("Class: " + obj + " (recursive = "+recursive+")");
		
		//inspect the current class
		if(full){
			inspectSuperClass(ObjClass);
			inspectInterfaces(ObjClass, interfacesToInspect);
			inspectConstructors(ObjClass);
			inspectMethods(ObjClass);
		}
		inspectFields(obj, ObjClass, fieldsToInspect);

		//inspect attached classes
		recursiveSuperClass(ObjClass, recursive, full);
		recursiveInterface(ObjClass, interfacesToInspect, recursive, full);
		if(recursive)
			recursiveField( obj, ObjClass, fieldsToInspect, recursive, full);
    }
    
    //Class inspector, used to inspect meta classes
    public void inspect(Class ObjClass, boolean recursive, boolean full)
    {
		Vector interfacesToInspect = new Vector();
			
		inspectedObjects.addElement(ObjClass.getName());
		
		System.out.println("Class: " + ObjClass.getName() + " (recursive = "+recursive+")");
	
		if(full)
		{
			inspectSuperClass(ObjClass);
			inspectInterfaces(ObjClass, interfacesToInspect);
			inspectConstructors(ObjClass);
			inspectMethods(ObjClass);
		}
		//inspect the current class
		inspectFields(ObjClass);

		recursiveSuperClass(ObjClass, recursive, full);
		recursiveInterface(ObjClass, interfacesToInspect, recursive, full);
    }
    //************************************************************************************************
    //SuperClass Inspecting Methods
    public void inspectSuperClass(Class ObjClass){
    	Class Super = ObjClass.getSuperclass();
    	
    	if(Super != null)
			System.out.println("SuperClass: " + Super.getName());
    }
	public void recursiveSuperClass(Class ObjClass, boolean recursive, boolean full){
		Class Super = ObjClass.getSuperclass();
		
		if(Super != null){
			if(! inspectedObjects.contains(Super.getName())){
				System.out.println("========================================");
				System.out.println("Inspecting superclass of " + ObjClass.getName() + ": ");
				inspect(Super, recursive, full);
				System.out.println("End of superclass " + Super.getName());
				System.out.println("========================================");
			}
		}
	}
    //************************************************************************************************
	//Field Inspecting Methods
    /*
     * fields
     * 	-types
     * 	-modifier
     * 	-current value, if object and recursive = false print pointer value
     */
    //-----------------------------------------------------------
    public void recursiveField(Object obj, Class ObjClass, Vector fieldsToInspect, boolean recursive, boolean full)
    {			
		Enumeration e = fieldsToInspect.elements();
		while(e.hasMoreElements())
		{
			Field f = (Field) e.nextElement();
			System.out.println("Inspecting Field of " + ObjClass.getName() + ": " + f.getName() );
			
			try{		
				Object fieldObject = f.get(obj);
				if(fieldObject.getClass().isArray()){
					for(int i = 0; i < Array.getLength(fieldObject); i++){
						Object element = Array.get(fieldObject, i);
						
						System.out.println("******************");
						if(! ArrayPrimitives.contains(element.getClass().getName()))
							inspect(element, recursive, full);
						System.out.println("******************");
					}
				}else{
					System.out.println("******************");
					inspect(fieldObject, recursive, full);
					System.out.println("******************");
				}
			}catch(NullPointerException npe){
				System.out.println("Field " + f.getName() + " has not initialized");
			}catch(Exception exp) { exp.printStackTrace(); }
			
	    }
    }
    public void inspectFields(Class ObjClass){
    	if ( ObjClass.getDeclaredFields().length >=1 ){
    		for(int i = 0; i < ObjClass.getDeclaredFields().length; i++){
    			Field f = ObjClass.getDeclaredFields()[i];
    			int mod = f.getModifiers();
    			if(f.getType().isArray())
    				System.out.println("Field: " + f.getType().getCanonicalName() + ":" + f.getName());
    			else
    				System.out.println("Field: " + f.getType() + ":" + f.getName());
    			System.out.println("	Modifiers: " + Modifier.toString(mod));
    				
    		}
    	}
    }
    public void inspectFields(Object obj, Class ObjClass, Vector objectsToInspect)  
    {
		if(ObjClass.getDeclaredFields().length >= 1){
			for(int i = 0; i < ObjClass.getDeclaredFields().length; i++){
				Field f = ObjClass.getDeclaredFields()[i];
				
				f.setAccessible(true);
				if(! f.getType().isPrimitive() ) 
				    objectsToInspect.addElement( f );
				
				try{
					Object value = f.get(obj);
					int mod = f.getModifiers();
					
					if(value != null){
						if( value.getClass().isArray() ){
							System.out.println("Field: " + f.getName() + ":" + value.getClass().getCanonicalName() + ":" + Array.getLength(value));
							for(int j = 0; j < Array.getLength(value); j++){
								Object element = Array.get(value, j);
								System.out.println("	Value at " + "["+j+"]" + " = "  + element);
							}
							
						}else{							
							System.out.println("Field: " + f.getName() + ":" + f.getType() + " = " + value);
						}
					}else
						System.out.println("Field: " + f.getName() + ":" + f.getType().getCanonicalName() + " = null");
					System.out.println("	Modifiers: " + Modifier.toString(mod));
				}
				catch(Exception e) {
					e.printStackTrace();
				}    
			}
		}
    }
    
    //************************************************************************************************
    /* Interface Inspecting Methods
     * interfaces the class implements 
     */
    public void inspectInterfaces(Class ObjClass, Vector toInspect){
    	Class[] interfaces = ObjClass.getInterfaces();
    	
    	for(int i = 0; i < interfaces.length; i++){
    		toInspect.addElement(interfaces[i]);
    		System.out.println("Interface: " + interfaces[i].getName());
    	}
    }
    public void recursiveInterface(Class ObjClass, Vector interfaces, boolean recursive, boolean full){
    	Enumeration en = interfaces.elements();
    	
    	while(en.hasMoreElements()){
    		Class inter= (Class) en.nextElement();
    		if(! inspectedObjects.contains(inter.getName())){
	    		System.out.println("========================================");
	    		System.out.println("Inspecting Interface of " + ObjClass.getName() + ": ");
	    		inspect(inter, recursive, full); 
	    		System.out.println("End of interface " + inter.getName());
	    		System.out.println("========================================");
    		}
    	}
    }
    //************************************************************************************************
    /* Method Inspecting Methods
     *  methods
     * 	-parameter types
     * 	-exceptions
     * 	-return type
     *	-modifiers
     */    
    public void getMethodParameterTypes(Method m){
		if(m.getParameterTypes().length >= 1){
			for(int i = 0; i < m.getParameterTypes().length; i++){
				Class c = m.getParameterTypes()[i];
				if(c.isArray())
					System.out.println("	Parameter: " + c.getCanonicalName());
				else
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
    	if(c.isArray())
    		System.out.println("	Return Type: " + c.getCanonicalName());
    	else
    		System.out.println("	Return Type: " + c.getName());
    }
    public void getMethodModifiers(Method m){
    	int numbModifiers = m.getModifiers();
   
    	System.out.println("	Modifiers: " + Modifier.toString(numbModifiers));
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
    
    //************************************************************************************************
    /* Contructor Inspecting Methods
     *  constructors
    * 	-parameter types
    * 	-modifiers
    */
    public void getConstructorParameterTypes(Constructor con){
		if(con.getParameterTypes().length >= 1){
			for(int i = 0; i < con.getParameterTypes().length; i++){
				Class c = con.getParameterTypes()[i];
				if(c.isArray())
					System.out.println("	Parameter: " + c.getCanonicalName());
				else
					System.out.println("	Parameter: " + c.getName());
			}
		}
    }
    public void getConstructorModifiers(Constructor c){
    	int numbModifiers = c.getModifiers();
    	System.out.println("	Modifiers: " + Modifier.toString(numbModifiers));
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
    //************************************************************************************************
}




import java.util.Enumeration;
import java.util.IdentityHashMap;
import java.util.Vector;
import java.lang.reflect.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Serializer
{
	private String outputFileName = "SerializedObject";
	private FileWriter fw;
	private BufferedWriter bw;
	private IdentityHashMap<Integer, String> map;
	private Vector<Object> objectsToSerialize;
	
	public Serializer () throws IOException
	{
		fw = new FileWriter(outputFileName);
		bw = new BufferedWriter(fw);			
		map = new IdentityHashMap<Integer, String>();
		objectsToSerialize = new Vector<Object>();
	}
	public Serializer (String outputFileName) throws IOException
	{
		this.outputFileName = outputFileName;
		fw = new FileWriter(this.outputFileName);
		bw = new BufferedWriter(fw);			
		map = new IdentityHashMap<Integer, String>();
		objectsToSerialize = new Vector<Object>();
	}
	//Calls methods to serialize object which return a string
	//Write the string to the file 
	public boolean serialize(Object object)
	{
		try{
			String document = "";
			document += "<?xml version=\"1.0\"?>\n";
			document += "<Document>\n";
			document += serializeObject(object);
			Enumeration<Object> e = objectsToSerialize.elements();
			while(e.hasMoreElements())
			{
				document += serializeObject(e.nextElement());
			}
			document += "</Document>\n";
		
			bw.write(document);
			bw.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}		
		
		return true;
	}
	private String serializeObject(Object obj) throws Exception
	{
		String doc = "";
		if(map.containsKey(obj.hashCode()))
			return doc;
		
		Class objClass = obj.getClass();
		Field [] classFields = objClass.getDeclaredFields();
		Field f;		
		
		map.put(obj.hashCode(), objClass.getName());		
		doc += "\t<Object id = " + obj.hashCode() + ">\n"; 
		doc += "\t\t<Name>" + objClass.getName() + "</Name>\n";
		if(classFields.length >= 1){			
			doc += "\t\t<Fields>\n";
			for (int i = 0; i < classFields.length; i++){
				f = classFields[i];
				doc += serializeField(obj, f);
			}
			doc += "\t\t</Fields>\n";
		}
		doc += "\t</Object>\n"; 
		
		return doc;
	}
	private String serializeField(Object obj, Field f) throws Exception
	{
		String doc = "";
		String tabs = "\t\t\t";
		f.setAccessible(true);
		doc += tabs + "<Field>\n";
		doc += tabs + "\t<Class>" + f.getDeclaringClass() + "</Class>\n";
		doc += tabs + "\t<Name>" + f.getName() + "</Name>\n";
		
		if(f.getType().isPrimitive())
			doc += tabs + "\t<Value>" + f.get(obj) + "</Value>\n";
		else if(f.getType().isArray())
		{
			doc += serializeArray((tabs +"\t"), f.get(obj));
		}
		else
		{
			objectsToSerialize.add(f.get(obj));
			doc += tabs + "\t<Value>" + f.get(obj).hashCode() + "</Value>\n";
		}
		doc += tabs + "</Field>\n";
		return doc;
	}
	private String serializeArray(String tabs, Object obj) throws Exception 
	{
		String doc = "";
		Object element;
		for(int i = 0; i < Array.getLength(obj); i++){
			element = Array.get(obj, i);
			doc += tabs + "<Index>" + i + "</Index>\n";
			if(element.getClass().isArray())
			{
				doc += serializeArray((tabs +"\t"), element);
			}
			else if(ArrayPrimitives.contains(element.getClass().getName()))
			{
				doc += tabs + "\t<Value>" + element + "</Value>\n";
			}
			else
			{
				objectsToSerialize.add(element);
				doc += tabs + "\t<Value>" + element.hashCode() + "</Value>\n";
			}
		}
		
		return doc;
	}
}

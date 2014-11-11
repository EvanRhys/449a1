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
			document += "<serialized>\n";
			document += serializeObject(object);
			Enumeration<Object> e = objectsToSerialize.elements();
			while(e.hasMoreElements())
			{
				document += serializeObject(e.nextElement());
			}
			document += "</serialized>\n";
		
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
		doc += "<Object id = \"" + obj.hashCode() + "\">\n"; 
		doc += "<Class>" + objClass.getName() + "</Class>\n";
		if(classFields.length >= 1){			
			doc += "<Fields>\n";
			for (int i = 0; i < classFields.length; i++){
				f = classFields[i];
				doc += serializeField(obj, f);
			}
			doc += "</Fields>\n";
		}
		doc += "</Object>\n"; 
		
		return doc;
	}
	private String serializeField(Object obj, Field f) throws Exception
	{
		String doc = "";
		f.setAccessible(true);
		doc += "<Field>\n";
		doc += "<Class>" + f.getDeclaringClass() + "</Class>\n";
		doc += "<Name>" + f.getName() + "</Name>\n";
		doc += "<Type>" + f.getType() + "</Type>\n";
		if(f.getType().isPrimitive())
			doc += "<Value>" + f.get(obj) + "</Value>\n";
		else if(f.getType().isArray())
			doc += serializeArray(f.get(obj));
		else
		{
			objectsToSerialize.add(f.get(obj));
			System.out.println(doc);
			doc += "<Value>" + f.get(obj).hashCode() + "</Value>\n";
		}
		
		doc += "</Field>\n";
		return doc;
	}
	private String serializeArray(Object obj) throws Exception 
	{
		String doc = "";
		Object element;
		doc += "<Length>" + Array.getLength(obj) + "</Length>\n";
		doc += "<Values>\n";
		for(int i = 0; i < Array.getLength(obj); i++){
			element = Array.get(obj, i);			
			if(element.getClass().isArray())
			{
				doc += serializeArray(element);
			}
			else if(ArrayPrimitives.contains(element.getClass().getName()))
			{
				doc += "<Value>" + element + "</Value>\n";
			}
			else
			{
				objectsToSerialize.add(element);
				doc += "<Value>" + element.hashCode() + "</Value>\n";
			}
		}
		doc += "</Values>\n";
		return doc;
	}
}

import java.lang.reflect.*;
import java.util.IdentityHashMap;
import java.io.File;

import javax.xml.parsers.*;

import org.w3c.dom.*;

public class Deserializer {
	private String filename = "SerializedObject";
	private IdentityHashMap<Integer, Object> map;
	private Object rootObject = null;
	
	public Deserializer()
	{
		map = new IdentityHashMap<Integer, Object>();
	}
	public Deserializer(String filename)
	{
		map = new IdentityHashMap<Integer, Object>();
		this.filename = filename;
	}
	
	public Object deserialize() throws Exception
	{
		File file = new File(filename);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		doc.normalize();
		
		NodeList document = doc.getChildNodes();
		NodeList nodes = document.item(0).getChildNodes();
		buildObjects(nodes);
		setObjects(nodes);
				
		return rootObject;
	}
	
	private void buildObjects(NodeList nodes) throws Exception
	{
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element) node;
				int HashCode = Integer.parseInt(element.getAttribute("id"));
				String ClassName = element.getElementsByTagName("Class").item(0).getTextContent();
				Class objClass = Class.forName(ClassName);
				Object obj = objClass.newInstance();
				if (rootObject == null)
					rootObject = obj;
				map.put(HashCode, obj);
			}
		}
	}
	private void setObjects(NodeList nodes) throws Exception
	{
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element) node;
				int HashCode = Integer.parseInt(element.getAttribute("id"));
				Object obj = map.get(HashCode);
				NodeList fields = element.getElementsByTagName("Fields");
				NodeList field = fields.item(0).getChildNodes();
				
				for(int j = 0; j < field.getLength(); j++)
				{
					Node fieldNode = field.item(i);
					setField(obj, fieldNode);
				}
			}
		}
	}
	private void setField(Object obj, Node field)throws Exception
	{	
		if(field.getNodeType() == Node.ELEMENT_NODE)
		{
			Element element = (Element) field;
			String type = element.getElementsByTagName("Type").item(0).getTextContent();
			if(ArrayPrimitives.isPrimitive(type))
				setPrimitive(obj, element);			
			else if(type.contains("["))
				setArray(obj, element);			
			else if(type.startsWith("class"))			
				setReference(obj, element);			
			else
				throw new Exception();				
			
		}
	}
	private void setPrimitive(Object obj, Element element) throws Exception
	{
		String type = element.getElementsByTagName("Type").item(0).getTextContent();
		String name = element.getElementsByTagName("Name").item(0).getTextContent();		
		String value = element.getElementsByTagName("Value").item(0).getTextContent();
		Class objClass = obj.getClass();
		
		Object trueValue = getTrueValue(type, value);
		Field f = objClass.getDeclaredField(name);
		f.set(obj, trueValue);
	}
	private void setReference(Object obj, Element element) throws Exception
	{
		String name = element.getElementsByTagName("Name").item(0).getTextContent();		
		String value = element.getElementsByTagName("Value").item(0).getTextContent();
		Class objClass = obj.getClass();
		
		Field f = objClass.getDeclaredField(name);
		Object trueValue = map.get(Integer.parseInt(value));
		f.set(obj, trueValue);
	}
	private void setArray(Object obj, Element element) throws Exception
	{
		String type = element.getElementsByTagName("Type").item(0).getTextContent();
		String name = element.getElementsByTagName("Name").item(0).getTextContent();		
		NodeList values = element.getElementsByTagName("Value");
		
		Class objClass = obj.getClass();
		
		String [] typeToken = type.split(" ");	
		System.out.println(typeToken[0]);
		System.out.println(typeToken[1]);
		Class arrayClass = Class.forName(typeToken[1]);		
	}
	
	private Object getTrueValue(String type, String value)
	{
		switch(type)
		{
		case "int":
			return Integer.parseInt(value);
		case "char":
			return value.charAt(0);
		case "boolean":
			return Boolean.parseBoolean(value);
		case "double":
			return Double.parseDouble(value);
		case "float":
			return Float.parseFloat(value);
		case "short":
			return Short.parseShort(value);
		case "long":
			return Long.parseLong(value);
		default:
			return null;
		}
	}
}

import java.lang.reflect.*;
import java.util.HashMap;
import java.io.File;

import javax.xml.parsers.*;

import org.w3c.dom.*;

public class Deserializer {
	private String filename = "SerializedObject";
	private HashMap<Integer, Object> map;
	private Object rootObject = null;
	
	public Deserializer()
	{
		map = new HashMap<Integer, Object> ();
	}
	public Deserializer(String filename)
	{
		map = new HashMap<Integer, Object> ();
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
				Class<?> objClass = Class.forName(ClassName);
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
				Node fieldsNode = fields.item(0);
				if(fieldsNode.getNodeType() == Node.ELEMENT_NODE){
					Element fieldElement = (Element) fieldsNode;
					NodeList field = fieldElement.getElementsByTagName("Field");
					for(int j = 0; j < field.getLength(); j++)
					{
						Node fieldNode = field.item(j);
						setField(obj, fieldNode);
					}
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
		Class<?> objClass = obj.getClass();
		
		Field f = objClass.getDeclaredField(name);
		Object trueValue = getTrueValue(type, value);
		f.setAccessible(true);
		f.set(obj, trueValue);
	}
	private void setReference(Object obj, Element element) throws Exception
	{
		String name = element.getElementsByTagName("Name").item(0).getTextContent();		
		String value = element.getElementsByTagName("Value").item(0).getTextContent();
		Class<?> objClass = obj.getClass();
		
		Field f = objClass.getDeclaredField(name);
		Object trueValue = map.get(Integer.parseInt(value));
		f.setAccessible(true);
		f.set(obj, trueValue);
	}
	private void setArray(Object obj, Element element) throws Exception
	{
		String type = element.getElementsByTagName("Type").item(0).getTextContent();
		String name = element.getElementsByTagName("Name").item(0).getTextContent();	
		String length = element.getElementsByTagName("Length").item(0).getTextContent();
		NodeList values = element.getElementsByTagName("Value");
		Class<?> objClass = obj.getClass();
		
		String [] typeToken = type.split(" ");
		Class<?> arrayClass = getType(typeToken[1]);
		Object array = Array.newInstance(arrayClass, Integer.parseInt(length));
		buildArray(array, values, typeToken[1].charAt(1));
		
		Field f = objClass.getDeclaredField(name);
		f.setAccessible(true);
		f.set(obj, array);
	}
	private void buildArray(Object array, NodeList values, char type) throws Exception
	{
		for(int i = 0; i < values.getLength(); i++)
		{
			Node value = values.item(i);
			if(value.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element) value;
				String val = element.getTextContent();
				Object trueValue = getTrueValue(type, val);
				Array.set(array, i, trueValue);
			}
		}
	}	
	private Class getType(String type) throws Exception
	{
		switch(type)
		{
		case "[I":
			return int.class;
		case "[C":
			return char.class;
		case "[Z":
			return boolean.class;
		case "[D":
			return double.class;
		case "[F":
			return float.class;
		case "[S":
			return short.class;
		case "[J":
			return long.class;
		case "[B":
			return byte.class;
		default:
			String typeClass = type.substring(2, type.length()-1);
			return Class.forName(typeClass);
		}	
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

	private Object getTrueValue(char type, String value)
	{
		switch(type)
		{
		case 'I':
			return new Integer(value);
		case 'C':
			return new Character(value.charAt(0));
		case 'Z':
			return new Boolean(value);
		case 'D':
			return new Double(value);
		case 'F':
			return new Float(value);
		case 'S':
			return new Short(value);
		case 'J':
			return new Long(value);
		case 'B':
			return new Byte(value);
		case 'L':
			return map.get(Integer.parseInt(value));
		default:
			return null;
		}		
	}
}

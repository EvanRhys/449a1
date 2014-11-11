
public class ArrayPrimitives {
	private static String [] arrayPrimitives = {
		"java.lang.Integer",
		"java.lang.Character",
		"java.lang.Boolean",
		"java.lang.Double",
		"java.lang.Float",
		"java.lang.Short",
		"java.lang.Long"};
	private static String [] primitives = {
		"int",
		"char",
		"boolean",
		"double",
		"float",
		"short",
		"long"};
	
	public static boolean contains(String element)
	{
		for(int i = 0; i < arrayPrimitives.length; i++)
		{
			if (arrayPrimitives[i].equals(element))
				return true;
		}
		return false;
	}
	public static boolean isPrimitive(String element)
	{
		for(int i = 0; i < primitives.length; i++)
		{
			if (primitives[i].equals(element))
				return true;
		}
		return false;
	}
}


public class ArrayPrimitives {
	private static String [] primitives = {
		"java.lang.Integer",
		"java.lang.Character",
		"java.lang.Boolean",
		"java.lang.Double",
		"java.lang.Float",
		"java.lang.Short",
		"java.lang.Long"};
	
	public static boolean contains(String element)
	{
		for(int i = 0; i < primitives.length; i++)
		{
			if (primitives[i].equals(element))
				return true;
		}
		return false;
	}
}

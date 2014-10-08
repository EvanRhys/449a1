import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TreeTests{
	private static Tree tree;
	private static Node[] fcN;
	private static Node[] fbN; 
	private static Node[] tNT; 
	private static Node[] tNP;
	private static int[][] mP;

	@Before
	public void setUp() throws Exception {
		fcN = new Node[8];
		fbN = new Node[8];
		tNT = new Node[8];
		tNP = new Node[8];
		mP = new int[8][8];
	}

	@After
	protected void tearDown() throws Exception {
		fcN = null;
		fbN = null;
		tNT = null;
		tNP = null;
		mP = null;
	}

	@Test
	public void test()
	{
		assertEquals(1,1);
	}
}

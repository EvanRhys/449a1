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
	private static String input;
	private static char empty;

	@Before
	public void setUp() throws Exception {
		fcN = new Node[8];
		fbN = new Node[8];
		tNT = new Node[8];
		tNP = new Node[8];
		mP = new int[8][8];
		input = "ABCDEFGH";
		empty = 'X';
	}

	@After
	public void tearDown() throws Exception {
		fcN = null;
		fbN = null;
		tNT = null;
		tNP = null;
		mP = null;
		input = null;
	}

	@Test
	public void simpleTest()
	{
		fcN[0] = new ForcedNode('1','B');
		
		fbN[1] = new ForbiddenNode('2', 'A');
		
		tNT[2] = new NearTaskNode('D', 'A');
		
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				mP[i][j] = 1;
			}
		}
		
		mP[0][1] = 4;
		
		tree = new Tree(new HardConstraints(fcN, fbN, tNT), new SoftConstraints(tNP, mP));
		tree.searchR(new SearchRParameter(input, "", 1, 0, empty));
		assertEquals("BCDAEFGH", tree.getBestString());
		assertEquals(11, tree.getBestScore());
	}
}

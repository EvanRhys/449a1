import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SoftConstraintTests{

		private static SoftConstraints SC;
		@Before
		public void setUp() throws Exception {
			Node[] tooNearPen = new Node[8];
			int[][] machPen = new int[8][8];
			Node current;
			
			tooNearPen[0] = new nearPenNode('A','B', 299);
			current = tooNearPen[0];
			current.setNext(new nearPenNode('A','C', 75));
			tooNearPen[2] = new nearPenNode('C','D', 61);
			int k = 1;
			for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					machPen[i][j] = k++;
				}
			}
			
			SC = new SoftConstraints(tooNearPen,machPen);
		}
		@After
		public void tearDown() throws Exception {
			SC = null;
		}
		
		@Test(expected = ParsingInputException.class)
		public void testParsingInput(){
			new nearPenNode('A','A',20);
		}
		@Test(expected = InvalidTaskException.class)
		public void InvalidFirstTaskLow(){
			new nearPenNode('9','A',20);
		}
		@Test(expected = InvalidTaskException.class)
		public void InvalidFirstTaskHigh(){
			new nearPenNode('I','A',20);
		}
		@Test(expected = InvalidTaskException.class)
		public void InvalidSecTaskLow(){
			new nearPenNode('A','9',20);
		}
		@Test(expected = InvalidTaskException.class)
		public void InvalidSecTaskHigh(){
			new nearPenNode('A','I',20);
		}
		@Test
		public void machPen(){
			assertEquals(30, SC.getMachinePenalty(4, 'F'));
		}
		@Test
		public void hasTooNear(){
			assertEquals(299, SC.getTooNearPenalty('A','B'));
		}
		@Test
		public void noTooNear(){
			assertEquals(0, SC.getTooNearPenalty('A', 'H'));
		}
		@Test
		public void getPen(){
			assertEquals(86, SC.getPenalty(2, 'C', 'A'));
		}

}

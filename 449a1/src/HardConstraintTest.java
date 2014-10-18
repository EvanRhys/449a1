import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HardConstraintTest {

	private static HardConstraints HC;

	@Before
	public void setUp() throws Exception {
		Node[] forcedPart = new Node[8];
		Node[] forbiddenM = new Node[8];
		Node[] tooNearTask = new Node[8];
		Node current;
		
		forcedPart[1] = new ForcedNode('2', 'A');
		forcedPart[2] = new ForcedNode('3', 'E');
		forcedPart[3] = new ForcedNode('4', 'C');
		forcedPart[7] = new ForcedNode('8', 'H');
		
		forbiddenM[0] = new ForbiddenNode('1', 'C');
		current = forbiddenM[0];
		current.setNext(new ForbiddenNode('1', 'B'));
		current = current.getNext();
		current.setNext(new ForbiddenNode('1', 'D'));
		forbiddenM[6] = new ForbiddenNode('7', 'G');
		
		tooNearTask[0] = new NearTaskNode('A','D');
		tooNearTask[1] = new NearTaskNode('B','C');
		current = tooNearTask[1];
		current.setNext(new NearTaskNode('B','G'));
		current = current.getNext();
		current.setNext(new NearTaskNode('B','H'));
		tooNearTask[4] = new NearTaskNode('E','F');
		
		HC = new HardConstraints( forcedPart, forbiddenM, tooNearTask );
	}

	@After
	public void tearDown() throws Exception {
		HC = null;
	}
	
	@Test(expected =  InvalidInputException.class)
	public void invalidFocredMachLow() {
		new ForcedNode('0', 'A');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidFocredMachHigh(){
		new ForcedNode('9', 'A');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidFocredTaskLow(){
		new ForcedNode('2', '9');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidFocredTaskHigh(){
		new ForcedNode('2', 'I');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidForbidMachLow() {
		new ForbiddenNode('0', 'A');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidForbidMachHigh(){
		new ForbiddenNode('9', 'A');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidForbidTaskLow(){
		new ForbiddenNode('2', '9');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidForbidTaskHigh(){
		new ForbiddenNode('2', 'I');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidNearFirstLow() {
		new NearTaskNode('9', 'A');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidNearFirstHigh(){
		new NearTaskNode('I', 'A');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidNearSecLow(){
		new NearTaskNode('A', '9');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidNearSecHigh(){
		new NearTaskNode('A', 'I');
	}
	@Test
	public void forcedPartAssignmentTrue(){
		assertEquals('A', HC.forcePartAssignment(2));
	}
	@Test
	public void forcedPartAssignmentFalse(){
		assertEquals('X', HC.forcePartAssignment(5));
	}
	@Test
	public void forbiddenMachineTrue(){
		assertEquals(true, HC.constraint(1, 'A', 'H'));
	}
	@Test
	public void forbiddenMachineFalse(){
		assertEquals(false, HC.constraint(1, 'D', 'H'));
	}
	@Test
	public void tooNearTasksTrue(){
		assertEquals(true, HC.constraint(2,'B', 'A'));
	}
	@Test
	public void tooNearTasksFalse(){
		assertEquals(false, HC.constraint(1, 'D', 'A'));
	}
}

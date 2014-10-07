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
		
		forcedPart[1] = new forcedNode('2', 'A');
		forcedPart[2] = new forcedNode('3', 'E');
		forcedPart[3] = new forcedNode('4', 'C');
		forcedPart[7] = new forcedNode('8', 'H');
		
		forbiddenM[0] = new forbiddenNode('1', 'C');
		current = forbiddenM[0];
		current.setNext(new forbiddenNode('1', 'B'));
		current = current.getNext();
		current.setNext(new forbiddenNode('1', 'D'));
		forbiddenM[6] = new forbiddenNode('7', 'G');
		
		tooNearTask[0] = new nearTaskNode('A','D');
		tooNearTask[1] = new nearTaskNode('B','C');
		current = tooNearTask[1];
		current.setNext(new nearTaskNode('B','G'));
		current = current.getNext();
		current.setNext(new nearTaskNode('B','H'));
		tooNearTask[4] = new nearTaskNode('E','F');
		
		HC = new HardConstraints( forcedPart, forbiddenM, tooNearTask );
	}

	@After
	public void tearDown() throws Exception {
		HC = null;
	}
	
	@Test(expected =  InvalidInputException.class)
	public void invalidFocredMachLow() {
		new forcedNode('0', 'A');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidFocredMachHigh(){
		new forcedNode('9', 'A');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidFocredTaskLow(){
		new forcedNode('2', '9');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidFocredTaskHigh(){
		new forcedNode('2', 'I');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidForbidMachLow() {
		new forbiddenNode('0', 'A');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidForbidMachHigh(){
		new forbiddenNode('9', 'A');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidForbidTaskLow(){
		new forbiddenNode('2', '9');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidForbidTaskHigh(){
		new forbiddenNode('2', 'I');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidNearFirstLow() {
		new nearTaskNode('9', 'A');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidNearFirstHigh(){
		new nearTaskNode('I', 'A');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidNearSecLow(){
		new nearTaskNode('A', '9');
	}
	@Test(expected =  InvalidInputException.class)
	public void invalidNearSecHigh(){
		new nearTaskNode('A', 'I');
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
		assertEquals(true, HC.forbiddenMachine(1, 'A'));
	}
	@Test
	public void forbiddenMachineFalse(){
		assertEquals(false, HC.forbiddenMachine(1, 'D'));
	}
	@Test
	public void tooNearTasksTrue(){
		assertEquals(true, HC.tooNearTasks('B', 'A'));
	}
	@Test
	public void tooNearTasksFalse(){
		assertEquals(false, HC.tooNearTasks('D', 'A'));
	}
}

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;



public class TaskOrganizerTest {

	
	public String readFile(String filename){
		String result = "";
		try{
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			result = br.readLine();
			br.close();			
		}catch (FileNotFoundException e){			
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		return result;
	}
	
	@Test
	public void twowrong() {		
		try{
			TaskOrganizer TO =  new TaskOrganizer("2wrong.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("invalid penalty", readFile("outfile1"));
	}

	@Test
	public void asymmetrical() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("asymmetrical.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("Solution ABCDEFHG; Quality:8", readFile("outfile1"));
	}
	@Test
	public void garbage() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("garbage.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("Error while parsing input file", readFile("outfile1"));
	}
	@Test
	public void invalid2() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("invalid2.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("No valid solution possible!", readFile("outfile1"));
	}
	@Test
	public void invalidforbidden() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("invalidforbidden.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("No valid solution possible!", readFile("outfile1"));
	}	@Test
	public void invalidtoonear() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("invalidtoonear.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("No valid solution possible!", readFile("outfile1"));
	}	@Test
	public void machpen1() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("machpen1.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("Solution ABCDEFHG; Quality:36", readFile("outfile1"));
	}
	@Test
	public void machpen2() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("machpen2.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("Solution ABCDEHGF; Quality:45", readFile("outfile1"));
	}	
	@Test
	public void nochoice() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("nochoice.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("Solution HGFEDCBA; Quality:800", readFile("outfile1"));
	}
	@Test
	public void nochoice2() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("nochoice2.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("Solution ABCDEFGH; Quality:8", readFile("outfile1"));
	}
	@Test
	public void testfile1() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("testfile1", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("Solution FAECGBDH; Quality:260", readFile("outfile1"));
	}
	@Test
	public void toonearpen1() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("toonearpen1.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("Solution ABCDEHFG; Quality:16", readFile("outfile1"));
	}
	@Test
	public void toonearpen2() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("toonearpen2.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("Solution ABCDEGFH; Quality:18", readFile("outfile1"));
	}
	@Test
	public void wrongmachine() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("wrongmachine.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("invalid machine/task", readFile("outfile1"));
	}
	@Test
	public void wrongmachpenalty1() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("wrongmachpenalty1.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("machine penalty error", readFile("outfile1"));
	}
	@Test
	public void wrongmachpenalty2() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("wrongmachpenalty2.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("machine penalty error", readFile("outfile1"));
	}
	@Test
	public void wrongnumbertoonear() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("wrongnumbertoonear.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("invalid penalty", readFile("outfile1"));
	}
	@Test
	public void wrongpartialassignment() {
		try{
			TaskOrganizer TO =  new TaskOrganizer("wrongpartialassignment.txt", "outfile1");
			if(TO.readFile())
				TO.runSearch();
		}catch(IOException e){
			e.printStackTrace();
		}
		assertEquals("partial assignment error", readFile("outfile1"));
	}
}

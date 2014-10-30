
public class PrimArrayObject {
	int [] intArray;
	
	public PrimArrayObject(){
		intArray = new int [] { 5,8,13,19,200 };  
	}
	public PrimArrayObject(int i){
		intArray = new int [i];
		for(int j = 0; j < i; j++){
			if(j*2 < 1000)
				intArray[j] = j*2;
			else 
				intArray[j] = j;
		}
	}
	public void set(int point, int value) throws ArrayIndexOutOfBoundsException{
		intArray[point] = value;
	}
	public int get(int point) throws ArrayIndexOutOfBoundsException{
		return intArray[point];
	}
}

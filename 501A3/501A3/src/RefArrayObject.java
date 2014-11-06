
public class RefArrayObject {
	SimpleObject [] sObjects;
	
	public RefArrayObject(){
		sObjects = new SimpleObject[5];
		sObjects[0] = new SimpleObject(true, 'a', 1, 1.1);
		sObjects[1] = new SimpleObject(false, 'b', 2, 2.2);
		sObjects[2] = new SimpleObject(true, 'c', 3, 3.3);
		sObjects[3] = new SimpleObject(false, 'd', 4, 4.4);
		sObjects[4] = new SimpleObject(true, 'e', 5, 5.5);
	}
	public RefArrayObject(int i){
		sObjects = new SimpleObject[i];
		
		for(int j = 0; j < i; j++){
			sObjects[j] = new SimpleObject();
		}
	}
	public SimpleObject get(int point) throws ArrayIndexOutOfBoundsException{
		return sObjects[point];
	}
	public void set( int point, SimpleObject obj ) throws ArrayIndexOutOfBoundsException{
		sObjects[point] = obj;
	}
}

import java.io.*;

public class InFile extends File {	
	public InFile(String fileName) {
		super(fileName);
	}		

	public int read()
	{
		try
		{
			FileReader fr = new FileReader(super.fileName);
			BufferedReader br = new BufferedReader(fr);
			readHeader(br);
			readDataChunk(br);

			br.close();
		}
		catch (FileNotFoundException FNF)
		{
			System.err.println("File " +  super.fileName + " not found. Please check folder containing files");
			return -1;
		}
		catch (IOException IOE)
		{
			IOE.printStackTrace();
			return -1;
		}
		catch (java.lang.NullPointerException NPE)
		{
			NPE.printStackTrace();
			return -1;
		}
		return 0;
	}
	
	/*
	//Wave Header Information
	//Riff chunk descriptor
	protected String ChunkID;
	protected int ChunkSize;
	protected String Format;
	
	//fmt sub-chunk
	protected String Subchunk1ID;
	protected int Subchunk1Size;
	protected short AudioFormat;
	protected short NumChannels;
	protected int SampleRate;
	protected int ByteRate;
	protected short BlockAlign;
	protected short BitsPerSample;
	 */
	private void readHeader(BufferedReader br) throws IOException
	{
		byte [] values;
		//4
		ChunkID = Character.toString((char) br.read())
				+ Character.toString((char) br.read())
				+ Character.toString((char) br.read())
				+ Character.toString((char) br.read());
		
		//8
		values = new byte[4];
		values[0] = (byte) br.read();
		values[1] = (byte) br.read();
		values[2] = (byte) br.read();
		values[3] = (byte) br.read();
		ChunkSize = readIntLSB(values);
		values = null;
		
		//12
		Format = Character.toString((char) br.read())
				+ Character.toString((char) br.read())
				+ Character.toString((char) br.read())
				+ Character.toString((char) br.read());
		
		//16
		Subchunk1ID = Character.toString((char) br.read())
				+ Character.toString((char) br.read())
				+ Character.toString((char) br.read())
				+ Character.toString((char) br.read());
		
		//20
		values = new byte[4];
		values[0] = (byte) br.read();
		values[1] = (byte) br.read();
		values[2] = (byte) br.read();
		values[3] = (byte) br.read();
		Subchunk1Size = readIntLSB(values);
		values = null;
		
		//22
		values = new byte[2];
		values[0] = (byte) br.read();
		values[1] = (byte) br.read();
		AudioFormat = readShortLSB(values);
		values = null;
		
		//24
		values = new byte[2];
		values[0] = (byte) br.read();
		values[1] = (byte) br.read();
		NumChannels = readShortLSB(values);
		values = null;
		
		//28
		values = new byte[4];
		values[0] = (byte) br.read();
		values[1] = (byte) br.read();
		values[2] = (byte) br.read();
		values[3] = (byte) br.read();
		SampleRate = readIntLSB(values);
		values = null;
		
		//32
		values = new byte[4];
		values[0] = (byte) br.read();
		values[1] = (byte) br.read();
		values[2] = (byte) br.read();
		values[3] = (byte) br.read();
		ByteRate = readIntLSB(values);
		values = null;
	
		//34
		values = new byte[2];
		values[0] = (byte) br.read();
		values[1] = (byte) br.read();
		BlockAlign = readShortLSB(values);
		values = null;
		
		//36
		values = new byte[2];
		values[0] = (byte) br.read();
		values[1] = (byte) br.read();
		BitsPerSample = readShortLSB(values);
		values = null;		
	}
	private void readDataChunk(BufferedReader br) throws IOException
	{
		byte [] values;
		
		//???????
		if(Subchunk1Size == 18)
		{
			br.read();
			br.read();
		}
		
		Subchunk2ID = Character.toString((char) br.read())
				+ Character.toString((char) br.read())
				+ Character.toString((char) br.read())
				+ Character.toString((char) br.read());
		
		values = new byte[4];
		values[0] = (byte) br.read();
		values[1] = (byte) br.read();
		values[2] = (byte) br.read();
		values[3] = (byte) br.read();
		Subchunk2Size = readIntLSB(values);
		values = null;
		
		if(debug)
			printHeader();
		
		if(super.NumChannels == 1)
			readSingleChannel(br);
	}
	
	private void readSingleChannel(BufferedReader br) throws IOException
	{
		int temp, i = 0;
		fileData = new int[Subchunk2Size - 8];
		while((temp = br.read()) != -1)	
			fileData[i++] = temp;
	}
	private int readIntLSB(byte [] value)
	{
		int i = (value[3] & 0xFF) << 24;
		i +=  (value[2] & 0xFF) << 16;
		i +=  (value[1] & 0xFF) << 8;
		i +=  value[0] & 0xFF;
		return i; 
	}
	private short readShortLSB(byte [] value)
	{
		int i = (value[1] & 0xFF) << 8;
		i +=  value[0] & 0xFF;
		return (short) i;
	}
}

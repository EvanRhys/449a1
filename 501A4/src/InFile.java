import java.io.*;

public class InFile extends File {	
	public InFile(String fileName) {
		super(fileName);
	}		

	public int read()
	{
		try
		{
			FileInputStream fis = new FileInputStream(super.fileName);

			readHeader(fis);
			readDataChunk(fis);

			fis.close();
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
	private void readHeader(FileInputStream fis) throws IOException
	{
		byte [] values;
		String tempo;
		//4		
		values = new byte[4];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		values[2] = (byte) fis.read();
		values[3] = (byte) fis.read();
		ChunkID = Character.toString((char) values[0])
				+ Character.toString((char) values[1])
				+ Character.toString((char) values[2])
				+ Character.toString((char) values[3]);
		values = null;
		
		//8
		values = new byte[4];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		values[2] = (byte) fis.read();
		values[3] = (byte) fis.read();
		ChunkSize = readIntLSB(values);
		values = null;
		
		//12
		values = new byte[4];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		values[2] = (byte) fis.read();
		values[3] = (byte) fis.read();
		Format = Character.toString((char) values[0])
				+ Character.toString((char) values[1])
				+ Character.toString((char) values[2])
				+ Character.toString((char) values[3]);
		values = null;
		
		//16
		values = new byte[4];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		values[2] = (byte) fis.read();
		values[3] = (byte) fis.read();
		Subchunk1ID = Character.toString((char) values[0])
				+ Character.toString((char) values[1])
				+ Character.toString((char) values[2])
				+ Character.toString((char) values[3]);
		values = null;
		
		//20
		values = new byte[4];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		values[2] = (byte) fis.read();
		values[3] = (byte) fis.read();
		Subchunk1Size = readIntLSB(values);
		values = null;
		
		//22
		values = new byte[2];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		AudioFormat = readShortLSB(values);
		values = null;
		
		//24
		values = new byte[2];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		NumChannels = readShortLSB(values);
		values = null;
		
		//28
		values = new byte[4];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		values[2] = (byte) fis.read();
		values[3] = (byte) fis.read();
		SampleRate = readIntLSB(values);
		values = null;
		
		//32
		values = new byte[4];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		values[2] = (byte) fis.read();
		values[3] = (byte) fis.read();
		ByteRate = readIntLSB(values);
		values = null;
	
		//34
		values = new byte[2];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		BlockAlign = readShortLSB(values);
		values = null;
		
		//36
		values = new byte[2];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		BitsPerSample = readShortLSB(values);
		values = null;		
	}
	private void readDataChunk(FileInputStream fis) throws IOException
	{
		byte [] values;
		
		//Compression Pointers
		if(Subchunk1Size == 18)
		{
			fis.read();
			fis.read();
		}
		
		values = new byte[4];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		values[2] = (byte) fis.read();
		values[3] = (byte) fis.read();
		Subchunk2ID = Character.toString((char) values[0])
				+ Character.toString((char) values[1])
				+ Character.toString((char) values[2])
				+ Character.toString((char) values[3]);
		values = null;
		
		values = new byte[4];
		values[0] = (byte) fis.read();
		values[1] = (byte) fis.read();
		values[2] = (byte) fis.read();
		values[3] = (byte) fis.read();
		Subchunk2Size = readIntLSB(values);
		values = null;
		
		if(debug)
			printHeader();
		
		if(NumChannels == 1)
			readSingleChannel(fis);
	}
	public float[] convertDataToFloat(byte[] byteData)
	{
		float [] floatData = new float[byteData.length/4];
		int j = 0;
		for(int i = 0; i < floatData.length; i++)
		{
			floatData[i] = readFloatLSB(byteData[j], 
										byteData[j+1],
										byteData[j+2],
										byteData[j+3]);
			j+=4;
		}
		return floatData;
	}
	
	private void readSingleChannel(FileInputStream fis) throws IOException
	{
		byte temp;//, i = 0;
		fileData = new byte[Subchunk2Size - 8];
		for(int i = 0; i < Subchunk2Size - 8; i++)
		{
			temp = (byte) fis.read();
			fileData[i] = temp;
		}
	}
	private float readFloatLSB(byte a, byte b, byte c, byte d)
	{
		float i = (d & 0xff) << 24;
			i += (c & 0xff) << 16;
			i += (b & 0xff) << 8;
			i += (a & 0xff);
		return i;
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

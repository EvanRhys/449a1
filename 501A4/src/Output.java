import java.io.*;
import java.nio.ByteBuffer;

public class Output extends File {

	public Output(String fileName) {
		super(fileName);
	}
	
	public int writeToFile()
	{
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			
			buildHeader();
			writeHeader(fos);
			writeData(fos);
			
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	
	public byte [] convertFloatToData(float[] floatData)
	{
		byte [] byteData = new byte[floatData.length*4];
		
		int j = 0;
		for(int i = 0; i < floatData.length; i++)
		{
			byte[] temp = convertFloatToLSB(floatData[i]);
			byteData[j] = temp[0];
			byteData[j+1] = temp[1];
			byteData[j+2] = temp[2];
			byteData[j+3] = temp[3];
			j += 4;
		}		
		
		return byteData;
	}
	private void buildHeader()
	{
		ChunkID = "RIFF";
		if(fileName.contains(".wav"))
			Format = "WAVE";
		Subchunk1ID = "fmt ";
		Subchunk1Size = 16;
		AudioFormat = 1;
		NumChannels = 1;
		SampleRate = 44100;
		BitsPerSample = 16;
		
		ByteRate = SampleRate * (NumChannels * (BitsPerSample/8));
		
		BlockAlign = (short) (NumChannels * (BitsPerSample/8));
		Subchunk2ID = "data";
		Subchunk2Size = fileData.length + 8;
		
		ChunkSize = Subchunk2Size + 36;
	}	
	private void writeHeader(FileOutputStream fos) throws IOException
	{
		fos.write(ChunkID.getBytes());
		fos.write(convertIntToLSB(ChunkSize));
		fos.write(Format.getBytes());
		fos.write(Subchunk1ID.getBytes());
		fos.write(convertIntToLSB(Subchunk1Size));
		fos.write(convertShortToLSB(AudioFormat));
		fos.write(convertShortToLSB(NumChannels));
		fos.write(convertIntToLSB(SampleRate));
		fos.write(convertIntToLSB(ByteRate));
		fos.write(convertShortToLSB(BlockAlign));
		fos.write(convertShortToLSB(BitsPerSample));
		if(Subchunk1Size == 18)
		{
			fos.write(0x00);
			fos.write(0x00);
		}
		fos.write(Subchunk2ID.getBytes());
		fos.write(convertIntToLSB(Subchunk2Size));
	}
	private void writeData(FileOutputStream fos) throws IOException
	{
		for(int i = 0; i < fileData.length; i++)
			fos.write(fileData[i]);
	}
	private byte[] convertFloatToLSB(float value)
	{	
		return ByteBuffer.allocate(4).putFloat(value).array();
	}
	private byte[] convertIntToLSB(int value)
	{
		byte [] values = new byte [4];
		values[3] = (byte) ((value >> 24) & 0xFF);
		values[2] = (byte) ((value >> 16) & 0xFF);
		values[1] = (byte) ((value >> 8) & 0xFF);
		values[0] = (byte) ((value) & 0xFF);
		return values;
	}
	private byte[] convertShortToLSB(short value)
	{
		byte [] values = new byte [2];
		values[1] = (byte) ((value >> 8) & 0xFF);
		values[0] = (byte) ((value) & 0xFF);
		return values;	
	}
	
}
 
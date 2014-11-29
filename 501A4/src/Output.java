import java.io.*;

public class Output extends File {

	public Output(String fileName) {
		super(fileName);
	}
	public int writeToFile()
	{
		try {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			
			writeHeader(bw);
			writeData(bw);
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	
	public void convertData()
	{
		
	}
	
	private void writeHeader(BufferedWriter bw) throws IOException
	{
		bw.write(ChunkID.toCharArray());
		bw.write(convertIntToLSB(ChunkSize));
		bw.write(Format.toCharArray());
		bw.write(Subchunk1ID.toCharArray());
		bw.write(convertIntToLSB(Subchunk1Size));
		bw.write(convertShortToLSB(AudioFormat));
		bw.write(convertShortToLSB(NumChannels));
		bw.write(convertIntToLSB(SampleRate));
		bw.write(convertIntToLSB(ByteRate));
		bw.write(convertShortToLSB(BlockAlign));
		bw.write(convertShortToLSB(BitsPerSample));
		bw.write(Subchunk2ID.toCharArray());
		bw.write(convertIntToLSB(Subchunk2Size));
	}
	private void writeData(BufferedWriter bw) throws IOException
	{
		for(int i = 0; i < fileData.length; i++)
			bw.write(fileData[i]);
	}
	private char[] convertIntToLSB(int value)
	{
		char [] values = new char [4];
		values[3] = (char) ((value >> 24) & 0xFF);
		values[2] = (char) ((value >> 16) & 0xFF);
		values[1] = (char) ((value >> 8) & 0xFF);
		values[0] = (char) ((value) & 0xFF);
		return values;
	}
	private char[] convertShortToLSB(short value)
	{
		char [] values = new char [2];
		values[1] = (char) ((value >> 8) & 0xFF);
		values[0] = (char) ((value) & 0xFF);
		return values;	
	}
	
}
 
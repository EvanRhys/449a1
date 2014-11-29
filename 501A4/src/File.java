public class File {
	protected boolean debug = true;
	
	protected String fileName;
	protected float [] data;
	protected int dataLength;
	
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
	
	//data sub-chunk
	protected String Subchunk2ID;
	protected int Subchunk2Size;	
	protected int [] fileData;
	
	public File (String fileName)
	{
		this.fileName = fileName;
	}
	public int[] getFileData() {
		return fileData;
	}
	public float[] getData() {
		return data;
	}
	public void setData(float[] data){
		this.data = data;
	}
	public int getDataLength() {
		return dataLength;
	}	
	protected void printHeader()
	{
		System.out.println("File Name: \t\t" +fileName);
		System.out.println("Chunk Id: \t\t" +ChunkID);
		System.out.println("Chunk Size: \t\t" +ChunkSize);
		System.out.println("Format: \t\t" +Format);
		System.out.println("Sub-Chunk 1 ID: \t" +Subchunk1ID);
		System.out.println("Sub-Chunk 1 Size: \t" +Subchunk1Size);
		System.out.println("Audio Format: \t\t" +AudioFormat);
		System.out.println("Number of Channels: \t" +NumChannels);
		System.out.println("Sample Rate: \t\t" +SampleRate);
		System.out.println("Byte Rate: \t\t" +ByteRate);
		System.out.println("Block Align: \t\t" +BlockAlign);
		System.out.println("Bits Per Sample: \t" +BitsPerSample);	
		System.out.println("Sub-Chunk 2 ID: \t" +Subchunk2ID);
		System.out.println("Sub-Chunk 2 Size: \t" +Subchunk2Size + "\n");
	}
}

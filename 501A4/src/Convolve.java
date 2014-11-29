import java.io.FileNotFoundException;


public class Convolve {
	private static long start;
	private static long end;
	private static long diff;
	
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		
		if(args.length == 3){
			Input in = new Input(args[0]);
			IR ir = new IR(args[1]);
			Output out = new Output(args[2]);
			if(in.read() == -1)
				System.exit(-1);
			if(ir.read() == -1)
				System.exit(-1);
			
			
			out.ChunkID = in.ChunkID;
			out.ChunkSize = in.ChunkSize;
			out.Format = in.Format;
			out.Subchunk1ID = in.Subchunk1ID;
			out.Subchunk1Size = in.Subchunk1Size;
			out.AudioFormat = in.AudioFormat;
			out.NumChannels = in.NumChannels;
			out.SampleRate = in.SampleRate;
			out.ByteRate = in.ByteRate;
			out.BlockAlign = in.BlockAlign;
			out.BitsPerSample = in.BitsPerSample;
			out.Subchunk2ID = in.Subchunk2ID;
			out.Subchunk2Size = in.Subchunk2Size;
			out.fileData = in.fileData;
			/*
			float [] temp = new float [in.getDataLength() + ir.getDataLength() - 1];
			out.setData(temp);
			convolve(in.getData(), in.getDataLength(), ir.getData(),
					ir.getDataLength(), out.getData(), out.getDataLength());
			out.convertData();
			//*/	
			
			if(out.writeToFile() == -1)
				System.exit(-1);			
		}else{
			System.err.println("Arguments: InputFileName IRFileName OutputFileName");
			System.exit(-1);	
		}
		end = System.currentTimeMillis();
		System.out.printf("Start Time = " + start + "\nEnd Time = " + end + "\nTotal = " + (end - start));		
	}
	public static int convolve (float x[], int N, float h[], int M, float y[], int P)
	{
		//Make sure file lengths are correct
		if( N + M != P-1)
			return -1;			
		
		int n, m, percent = 0;
		
		for(n = 0; n < P; n++)
			y[n] = (float) 0.0;
		
		for(n = 0; n < N; n++)
		{
			if((n/N*100) > percent){
				System.out.println(percent + "% Complete...");
				percent+=5;
			}						
			for(m = 0; m < M; m++)
				y[n+m] += x[n] * h[m];
		}
		
		return 0;
	}

}

public class Convolve {
	private static long start;
	private static long end;
	
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		
		if(args.length == 3){
			InFile in = new InFile(args[0]);
			InFile ir = new InFile(args[1]);
			Output out = new Output(args[2]);
		
			if(in.read() == -1)
				System.exit(-1);
			if(ir.read() == -1)
				System.exit(-1);			
			
			in.data = in.convertDataToFloat(in.fileData);
			in.dataLength = in.data.length;
			
			ir.data = ir.convertDataToFloat(ir.fileData);
			ir.dataLength = ir.data.length;
			
			/*
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
			//*/

			//*
			float [] temp = new float [in.getDataLength() + ir.getDataLength()];
			out.setData(temp);
			out.dataLength = out.data.length;
			if(convolve(in.getData(), in.getDataLength(), ir.getData(),
					ir.getDataLength(), out.getData(), out.getDataLength()) == -1)
				System.out.println("Convolve Failed");
			out.fileData = out.convertFloatToData(out.data);
			//*/	
			
			//*
			if(out.writeToFile() == -1)
				System.exit(-1);
			//*/			
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
		if( N + M != P)
			return -1;			
		
		int n, m, percent = 0;
		
		for(n = 0; n < P; n++)
			y[n] = (float) 0.0;
		
		for(n = 0; n < N; n++)
		{
			if(((double)n/(double)N*100) > percent){
				System.out.println(percent + "% Complete...");
				percent+=5;
			}						
			for(m = 0; m < M; m++)
				y[n+m] += x[n] * h[m];
		}
		System.out.println(percent + "% Complete...");
		return 0;
	}

}

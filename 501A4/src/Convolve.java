public class Convolve {
	private static long start;
	private static long end;
	private final static double PI = 3.14592653589793;
	private final static double TWO_PI = PI * 2;
	
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
			//out.data = ir.data;
			//*
			float [] temp = new float [in.getDataLength() + ir.getDataLength()];
			out.setData(temp);
			out.dataLength = out.data.length;
			if(convolve(in.getData(), in.getDataLength(), ir.getData(),
					ir.getDataLength(), out.getData(), out.getDataLength()) == -1)
				System.out.println("Convolve Failed");
			//*/
			out.fileData = out.convertFloatToData(out.data);

			if(out.writeToFile() == -1)
				System.exit(-1);
			
		}else{
			System.err.println("Arguments: InputFileName IRFileName OutputFileName");
			System.exit(-1);	
		}
		end = System.currentTimeMillis();
		
		System.out.printf("Start Time = " + convertToDate(start) +
				"\nEnd Time = " + convertToDate(end) + "\nTotal = " +
				convertToDate(end - start));		
	}
	
	public static String convertToDate(long milli){
		String date;
		int seconds, minutes, hours;
		long x = milli / 1000;
		
		seconds = (int) x % 60;
		x /= 60;
		minutes = (int) x % 60;
		x /= 60;
		hours = (int) x % 24;
		
		date = String.format("Hours: %d, Minutes %d, Seconds %d", hours, minutes, seconds);
		
		return date;
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
	private static void SWAP(double data[], long i, long j)
	{
		double temp = data[(int) i];
		data[(int) i] = data[(int) j];
		data[(int) j] = temp;
 	}
	
	public static int four1(double data[], int nn, int isign){
		long n, mmax, m, j, istep, i;
		double wtemp, wr, wpr, wpi, wi, theta;
		double tempr, tempi;
		
		n = nn << 1;
		j = 1;
		
		for (i = 1; i < n; i += 2){
			if(j > i){
				SWAP(data, i, j);
				SWAP(data, i+1, j+1);
			}
			m = nn;
			while(m >= 2 && j > m){
				j -= m;
				m >>= 1;
			}
			j += m;
		}
		
		mmax = 2;
		while(n > mmax){
			istep = mmax << 1;
			theta = isign * (TWO_PI / mmax);
			wtemp = Math.sin(0.5 * theta);
			wpr = -2.0 * wtemp * wtemp;
			wpi = Math.sin(theta);
			wr = 1.0;
			wi = 0.0;
			
			for(m = 1; m <mmax; m +=2){
				for(i = m; i <= n; i += istep){
					j = i + mmax;
					tempr = wr * data[(int) j] - wi * data[(int) j+1];
					tempi = wr * data[(int) j+1] + wi * data[(int) j];
					data[(int) j] = data[(int) i] - tempr;
					data[(int) j+1] = data[(int) i + 1] - tempi;
					data[(int) i] += tempr;
					data[(int) i+1] += tempi;
				}
				wr = (wtemp = wr) * wpr -wi * wpi + wr;
				wi = wi * wpr + wtemp * wpi + wi;
			}
			mmax = istep;
		}
		
		return 0;
	}

}

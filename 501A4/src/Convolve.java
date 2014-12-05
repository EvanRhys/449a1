import java.math.BigInteger;

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
			
			in.data = in.convertDataToDouble(in.fileData);
			in.dataLength = in.data.length;

			ir.data = ir.convertDataToDouble(ir.fileData);
			ir.dataLength = ir.data.length;
			//out.data = in.data;
			/*DFT convolve;
			double [] temp = new double [in.getDataLength() + ir.getDataLength()];
			out.setData(temp);
			out.dataLength = out.data.length;
			if(convolve(in.getData(), in.getDataLength(), ir.getData(),
					ir.getDataLength(), out.getData(), out.getDataLength()) == -1)
				System.out.println("Convolve Failed");
			//*/
			//*FFT convolve;
			in.data = buildImaginary(in.data, in.dataLength);
			in.dataLength = in.data.length;
			ir.data = buildImaginary(ir.data, ir.dataLength);
			ir.dataLength = ir.data.length;
			
			if(in.dataLength<2 || (in.dataLength&(in.dataLength-1))!= 0){
				int n = Integer.highestOneBit(in.dataLength);
				n <<= 1;
				in.data = zeroPadding(in.data, n);
				in.dataLength = in.data.length;
			}
			ir.data = zeroPadding(ir.data, in.dataLength);
			ir.dataLength = ir.data.length;
			four1(in.data, in.dataLength/2, 1);
			four1(ir.data, in.dataLength/2, 1);
			double [] temp = new double [in.getDataLength()];
			out.setData(temp);
			out.dataLength = out.data.length;
			FFTConvolve(in.data, in.dataLength, ir.data, ir.dataLength, out.data, out.dataLength);
			scale(out.data, out.dataLength/2);
			four1(out.data, out.dataLength/2, -1);

			//*/
			
			out.fileData = out.convertDoubleToData(out.data);

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
	
	private static String convertToDate(long milli){
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
	
	private static int convolve (double x[], int N, double h[], int M, double y[], int P)
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
	private static double [] buildImaginary(double x[], int N){
		double [] temp = new double[N * 2];
		int j = 0;
		
		for(int i = 0;  i < temp.length; i+=2){
			temp[i] = x[j];
			temp[i+1] = 0.0;
			j++;
		}
		
		return temp;
	}
	private static double [] zeroPadding(double h[], int N){
		double [] temp = new double [N];
		int i;
		for(i = 0; i < h.length; i++)
			temp[i]	= h[i];
		for(;i < N; i++)
			temp[i] = 0.0;		
		
		return temp;
	}
	private static int FFTConvolve(double x[], int N, double h[], int M, double y[], int P)
	{
		int n, percent = 0;
		
		for(n = 0; n < N; n+=2){
			if(((double)n/(double)N*100) > percent){
				System.out.println(percent + "% Complete...");
				percent+=5;
			}	
			
			y[n] = (x[n] * h[n]) - (x[n+1] * h[n+1]);
			y[n+1] = (x[n+1] * h[n]) + (x[n] * h[n+1]);
		}
		
		System.out.println(percent + "% Complete...");
	
		return 0;
	}
	private static void SWAP(double data[], int j, int i)
	{
		double temp = data[i];
		data[i] = data[j];
		data[j] = temp;
 	}
	private static void scale(double data[], int N)
	{
		for(int k = 0, i = 0; k < N; k++, i+=2)
		{
			data[i] /= (double)N;
			data[i+1] /= (double)N;
		}
	}
	/* Java version of four1 was taken from:
	 * https://code.google.com/p/scalalab/wiki/JavaFFTvsNative
	 */
	private static void four1(final double[] data, final int n, final int isign) 
	{
		int nn,mmax,m,j,istep,i;
		double wtemp,wr,wpr,wpi,wi,theta,tempr,tempi;
		if (n<2 || (n&(n-1))!= 0) throw new IllegalArgumentException("n must be power of 2 in four1");
		nn = n << 1;
		j = 1;
		for (i=1;i<nn;i+=2) {
			if (j > i) {
				SWAP(data,j-1,i-1);
		        SWAP(data,j,i);
		    }
		    m=n;
		    while (m >= 2 && j > m) 
		    {
		    	j -= m;
		        m >>= 1;
		    }
		    j += m;
		}
		mmax=2;
		while (nn > mmax) {
			istep=mmax << 1;
		    theta=isign*(6.28318530717959/mmax);
		    wtemp= Math.sin(0.5*theta);
		    wpr = -2.0*wtemp*wtemp;
		    wpi=Math.sin(theta);
		    wr=1.0;
		    wi=0.0;
		    for (m=1;m<mmax;m+=2) {
		    	for (i=m;i<=nn;i+=istep) {
		    		j=i+mmax;
		    		tempr=wr*data[j-1]-wi*data[j];
		    		tempi=wr*data[j]+wi*data[j-1];
		    		data[j-1]=data[i-1]-tempr;
		    		data[j]=data[i]-tempi;
		    		data[i-1] += tempr;
		    		data[i] += tempi;
		        }
		        wr=(wtemp=wr)*wpr-wi*wpi+wr;
		        wi=wi*wpr+wtemp*wpi+wi;
		    }
		    mmax=istep;
		}
	}
}

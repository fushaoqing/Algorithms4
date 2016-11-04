import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.ThreeSum;

public class DoublingRatio {
	public static double timeTrial(int n){
		int[] a=new int[n];
		for(int i=0;i<n;i++) a[i]=StdRandom.uniform(-1000000,1000000);
		StopWatch s=new StopWatch();
		int cnt=ThreeSum.count(a);
		return s.elapsedTime();
	}

	public static void main(String[] args) {
		// 打印出数量为N的耗时和N+N的耗时的比值，发现最终趋于固定值8
		double prev=timeTrial(125);
		for(int N=250;true;N+=N){
			double time=timeTrial(N);
			StdOut.printf("%6d %7.1f",N,time);
			StdOut.printf("%5.1f\n",time/prev);
			prev=time;
		}
	}
}

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.ThreeSum;

public class DoubleLingTest {
	public static double timeTrial(int n){
		int[] a=new int[n];
		for(int i=0;i<n;i++) a[i]=StdRandom.uniform(-1000000,1000000);
		StopWatch s=new StopWatch();
		int cnt=ThreeSum.count(a);
		return s.elapsedTime();
	}

	public static void main(String[] args) {
		for(int N=250;true;N+=N){
			double time=DoubleLingTest.timeTrial(N);
			System.out.printf("%d %5.1fseconds\n",N,time);
		}
	}
}

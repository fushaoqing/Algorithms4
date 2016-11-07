package ex_1_4_3;
import edu.princeton.cs.algs4.*;
public class DoubleLingTestDraw {
	public static double timeTrial(int n){
		int[] a=new int[n];
		for(int i=0;i<n;i++) a[i]=StdRandom.uniform(-1000000,1000000);
		StopWatch s=new StopWatch();
		int cnt=ThreeSum.count(a);
		return s.elapsedTime();
	}

	public static void main(String[] args) {
		StdDraw.setPenRadius(0.01);
		StdDraw.setXscale(0.0,10.0);
		StdDraw.setYscale(-5.0,1.0);
		StdDraw.setPenColor(StdDraw.RED);
		for(int N=250;true;N+=N){
			double time=DoubleLingTestDraw.timeTrial(N);
			//StdDraw.point(N,time);
			double lN=Math.log(N*1.0);
			double ltime=Math.log(time);
			if(ltime>=1.0) StdDraw.setCanvasSize(N*2, (int)(time*2));
			StdDraw.point(lN,ltime);
			System.out.printf("%5.1f %5.1f seconds\n",lN,ltime);
		}
	}
}

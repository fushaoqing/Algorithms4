
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class SortCompare {

	public static double time(String arg,Double[] a){
		StopWatch s=new StopWatch();
		if(arg.equals("Selection"))  Bubble.sort(a);
		if(arg.equals("Bubble"))  	 Selection.sort(a);
		if(arg.equals("Insertion"))  Insertion.sort(a);
		if(arg.equals("Shell"))  	 Shell.sort(a);
		return s.elapsedTime();
		//System.out.println(arg0);
	}
	
	public static double timeRandomInputs(String arg,int N,int T){
		double total=0;
		Double[] a=new Double[N];
		for(int t=0;t<T;t++){//重构T次随机数组，并排序
			for(int i=0;i<N;i++){
				a[i]=StdRandom.uniform();
			}
		total+=time(arg,a);//求出T次排序的总时间
		if(!Example.isSorted(a)) return -1.0;
		}
		return total;
	}
	
	public static void main(String[] args){
		String arg1="Selection";
		String arg2="Bubble";
		String arg3="Insertion";
		String arg4="Shell";
		int N=1000;
		int T=100;
		double t1=SortCompare.timeRandomInputs(arg1, N, T);
		double t2=SortCompare.timeRandomInputs(arg2, N, T);
		double t3=SortCompare.timeRandomInputs(arg3, N, T);
		double t4=SortCompare.timeRandomInputs(arg4, N, T);
		StdOut.printf("%.3f\n", t1);
		StdOut.printf("%.3f\n", t2);
		StdOut.printf("%.3f\n", t3);
		StdOut.printf("%.3f\n", t4);
	}
	
}

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class CPM {
	public static void main(String[] args) {
		In in=new In(args[0]);
		int N=Integer.parseInt(in.readLine());
		//System.out.println(N);
		EdgedWeightedDigraph G=new EdgedWeightedDigraph(2*N+2);
		int s=2*N;
		int t=2*N+1;

		for(int i=0;i<N;i++){
			String[] a=in.readLine().split("\\s+");//"\\s+"为正则表达式，表示一个或多个空格，回车
			double duration=Double.parseDouble(a[0]);
			G.addEdge(new DirectedEdge(i,i+N,duration));
			G.addEdge(new DirectedEdge(s,i,0.0));
			G.addEdge(new DirectedEdge(i+N,t,0.0));
			for(int j=1;j<a.length;j++){
				int successor=Integer.parseInt(a[j]);
				G.addEdge(new DirectedEdge(i+N,successor,0.0));//受限制的边,表示前一个任务没完，后一个任务在等待权重为0.
			}
		}
		
		AcyclicLP lp=new AcyclicLP(G,s);
		StdOut.println("start time:");
		for(int i=0;i<N;i++)
			StdOut.printf("%4d %5.1f\n",i,lp.distTo(i));//任务i的开始时间
		StdOut.printf("Finish time:%5.1f\n",lp.distTo(t));//所有任务完成的总时间
	}

}

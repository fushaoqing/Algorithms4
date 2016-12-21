import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BellmanFordSP {

	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private boolean[] onQ;//onQ[v]=true,表示点v在队列中
	private Queue<Integer> queue;
	private int count;//表示relax的次数
	private Iterable<DirectedEdge> cycle;
	
	public BellmanFordSP(EdgedWeightedDigraph G,int s){
		edgeTo=new DirectedEdge[G.V()];
		distTo=new double[G.V()];
		onQ=new boolean[G.V()];
		for(int v=0;v<G.V();v++){
			distTo[v]=Double.POSITIVE_INFINITY;
		}
		distTo[s]=0.0;
		queue=new Queue<Integer>();
		queue.enqueue(s);
		onQ[s]=true;
		while(!queue.isEmpty()&&!hasNegativeCycle()){
			int v=queue.dequeue();
			onQ[v]=false;
			relax(G,v);
		}
	}
	
	public void relax(EdgedWeightedDigraph G,int v){//放松一个点等于放松这个点发出的所有边
		for(DirectedEdge e:G.adj(v)){
			int w=e.to();
			if(distTo[w]>distTo[v]+e.weight()){
				distTo[w]=distTo[v]+e.weight();//更新路径的权值
				edgeTo[w]=e;//更新路径
				if(!onQ[w]){
					queue.enqueue(w);
					//System.out.println(w);
					onQ[w]=true;
				}
			}
			
			if(count++%G.V()==0)//当所有点刚好都被放松完时，检查此时的edgeTo数组中是否出现环
				findNegativeCycle();//若有环，则必为负权重环
		}
	}
	
	private void findNegativeCycle(){
		int V=edgeTo.length;
		EdgedWeightedDigraph spt=new EdgedWeightedDigraph(V);
		for(int v=0;v<V;v++){
			if(edgeTo[v]!=null)
				spt.addEdge(edgeTo[v]);
		}
		EdgedWeightedDirectedCycle edc=new EdgedWeightedDirectedCycle(spt);
		cycle=edc.cycle();
	}
	
	public boolean hasNegativeCycle(){
		return cycle!=null;
	}
	
	public Iterable<DirectedEdge> NegativeCycle(){
		return cycle;
	}
	
	public double distTo(int v){
		return distTo[v];
	}
	
	public boolean hasPathTo(int v){
		return distTo[v]<Double.POSITIVE_INFINITY;//没有通达路径时，距离等于无穷大
	}
	
	public Iterable<DirectedEdge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<DirectedEdge> stack=new Stack<DirectedEdge>();
		for(DirectedEdge e=edgeTo[v];e!=null;e=edgeTo[e.from()])//顶点对应的边为null
			stack.push(e);
		return stack;
	}
	
	public static void main(String[] args){
		EdgedWeightedDigraph G=new EdgedWeightedDigraph(new In(args[0]));
		int s=0;
		BellmanFordSP dsp=new BellmanFordSP(G,0);
		System.out.println(dsp.hasNegativeCycle());
		for(int t=0;t<G.V();t++){
			StdOut.print(s+" to "+t);
			StdOut.printf(" (%4.2f) ",dsp.distTo(t));
			if(dsp.hasPathTo(t))
				for(DirectedEdge e:dsp.pathTo(t))
					StdOut.print(e+" ");
			StdOut.println("");
		}
	}
}

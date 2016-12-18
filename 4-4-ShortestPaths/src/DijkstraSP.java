import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraSP {
	private int weight;
	private DirectedEdge[] edgeTo;//Edge[w]表示到某一点的边
	private double[] distTo;//distTo[w]表示到某一点的边的权值	
	private IndexMinPQ<Double> pq;
	
	public DijkstraSP(EdgedWeightedDigraph G,int s){
		edgeTo=new DirectedEdge[G.V()];
		distTo=new double[G.V()];
		for(int v=0;v<G.V();v++){
			distTo[v]=Double.POSITIVE_INFINITY;
		}
		distTo[s]=0.0;
		pq=new IndexMinPQ<Double>(G.V());
		pq.insert(s, distTo[s]);
		while(!pq.isEmpty()){
			relax(G,pq.delMin());//不断放松每个点，直到s到每一个顶点的路径都为最短为止
		}
	}
	
	private void relax(EdgedWeightedDigraph G,int v){
		for(DirectedEdge e:G.adj(v)){
			int w=e.to();
			if(distTo[w]>distTo[v]+e.weight()){//如果有到w的更短的路径，就用更短的路径去替换
				distTo[w]=distTo[v]+e.weight();
				edgeTo[w]=e;
				if(pq.contains(w)) pq.change(w, distTo[w]);
				else 			   pq.insert(w, distTo[w]);
			}
				
		}
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
		DijkstraSP dsp=new DijkstraSP(G,0);
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

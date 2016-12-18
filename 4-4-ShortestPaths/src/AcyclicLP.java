import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class AcyclicLP {
/*将relax倒过来，求无环图中的最长路径*/
	private DirectedEdge[] edgeTo;//Edge[w]表示到某一点的边
	private double[] distTo;//distTo[w]表示到某一点的边的权值	
	
	public AcyclicLP(EdgedWeightedDigraph G,int s){
		edgeTo=new DirectedEdge[G.V()];
		distTo=new double[G.V()];
		for(int v=0;v<G.V();v++){
			distTo[v]=Double.NEGATIVE_INFINITY;//把无穷大改为无穷小
		}
		distTo[s]=0.0;
		
		DepthFirsteOrder dfo=new DepthFirsteOrder(G);
		for(int v:dfo.reversePost())
			relax(G,v);	
	}
	
	private void relax(EdgedWeightedDigraph G,int v){
		for(DirectedEdge e:G.adj(v)){
			int w=e.to();
			if(distTo[w]<distTo[v]+e.weight()){//如果有到w的更短的路径，就用更短的路径去替换
				distTo[w]=distTo[v]+e.weight();
				edgeTo[w]=e;
			}		
		}
	}
	
	public double distTo(int v){
		return distTo[v];
	}
	
	public boolean hasPathTo(int v){
		return distTo[v]>Double.NEGATIVE_INFINITY;
		
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
		int s=5;
		AcyclicLP alp=new AcyclicLP(G,s);
		for(int t=0;t<G.V();t++){
			StdOut.print(s+" to "+t);
			StdOut.printf(" (%4.2f) ",alp.distTo(t));
			if(alp.hasPathTo(t))
				for(DirectedEdge e:alp.pathTo(t))
					StdOut.print(e+" ");
			StdOut.println("");
		}
	}
}

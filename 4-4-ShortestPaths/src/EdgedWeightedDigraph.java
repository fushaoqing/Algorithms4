import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgedWeightedDigraph {

	private int V;
	private int E;
	private Bag<DirectedEdge>[] adj;
	
	public EdgedWeightedDigraph(int V){
		this.V=V;
		this.E=0;
		this.adj=(Bag<DirectedEdge>[])new Bag[V];
		for(int v=0;v<V;v++){
			adj[v]=new Bag<DirectedEdge>();
		}
	}
	
	public EdgedWeightedDigraph(In in){
		this(in.readInt());
		int E=in.readInt();
		for(int i=0;i<E;i++){
			int v=in.readInt();
			int w=in.readInt();
			double weight=in.readDouble();
			DirectedEdge e=new DirectedEdge(v,w,weight);
			addEdge(e);
		}
	}

	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public void addEdge(DirectedEdge e){
		adj[e.from()].add(e);//将边添加到起点对应的背包中
	}
	
	public void addEdge(int v,int w,double weight){
		DirectedEdge e=new DirectedEdge(v,w,weight);
		addEdge(e);
	}
	public Iterable<DirectedEdge> adj(int v){
		return adj[v];
	}
	
	public Iterable<DirectedEdge> edges(){
		Bag<DirectedEdge> all=new Bag<DirectedEdge>();
		for(int v=0;v<V;v++)
			for(DirectedEdge e:adj[v])
				all.add(e);
		return all;//返回图中所有边
	}
	
	public static void main(String[] args) {
		
	}
}

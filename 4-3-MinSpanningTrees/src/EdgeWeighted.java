import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeighted {

	private int V;
	private int E;
	private Bag<Edge>[] adj;
	public EdgeWeighted(int V){
		this.V=V;
		this.E=0;
		adj=(Bag<Edge>[])new Bag[V];
		for(int v=0;v<V;v++){
			adj[v]=new Bag<Edge>();
		}
	}
	
	public EdgeWeighted(In in){
		this(in.readInt());
		int E=in.readInt();
		for(int i=0;i<E;i++){
			int v=in.readInt();
			int w=in.readInt();
			double weight=in.readDouble();
			Edge e=new Edge(v,w,weight);
			addEdge(e);
		}
	}
	
	public void addEdge(Edge e){
		int v=e.either();
		int w=e.other(v);
		adj[v].add(e);//两个点公用一条边
		adj[w].add(e);
		E++;
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	
	public Iterable<Edge> edges(){
		Bag<Edge> b=new Bag<Edge>();
		for(int v=0;v<V;v++){
			for(Edge e:adj[v])
				if(e.other(v)>v)//由于两点共用一条边，故保证不重复加入
					b.add(e);
		}
		return b;
	}
	
	public static void main(String[] args) {
		EdgeWeighted edw=new EdgeWeighted(new In(args[0]));
		for(Edge e:edw.edges())
			System.out.println(e);
	}
}

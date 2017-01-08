import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Digraph {
	
	private int V;
	private int E;
	private Bag<Integer>[] adj;
	public Digraph(int V){
		this.V=V;
		this.E=0;
		adj=(Bag<Integer>[])new Bag[V];
		for(int v=0;v<V;v++)
			adj[v]=new Bag<Integer>();
	}
	
	public Digraph(In in){
		this(in.readInt());
		int E=in.readInt();
		for(int i=0;i<E;i++){
			int v=in.readInt();
			int w=in.readInt();
			addEdge(v,w);
		}
	}
	public void addEdge(int v,int w){
		adj[v].add(w);//单向添加
		this.E++;
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public Digraph reverse(){
		Digraph d=new Digraph(V);
		for(int v=0;v<V;v++)
			for(int w:adj(v)){
				d.addEdge(w,v);//反向添加到反图中
			}
		return d;	
	}
	
	public String toString(){
		String s=V+" vertices "+E+" edges\n";
		for(int v=0;v<V;v++){
			s+=v+": ";//打印顶点
			for(int w:this.adj(v))
				s+=w+" ";//打印与顶点相邻的点
			s+="\n";
		}
		return s;
	}
	public static void main(String[] args) {
		Digraph d=new Digraph(new In(args[0]));
		System.out.print(d);
	}
}

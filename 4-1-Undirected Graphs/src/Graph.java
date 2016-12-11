import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.In;
/*测试文件："D:\Notepad\算法\Algorithms4\4-1-Undirected Graphs\src\tinyG.txt"*/
public class Graph {
	private int V;
	private int E;
	private Bag<Integer>[] adj;
	
	public Graph(int V){
		this.V=V;
		this.E=0;
		adj=(Bag<Integer>[])(new Bag[V]);//不能创建泛型数组，需要强制转换
		for(int i=0;i<V;i++)
			adj[i]=new Bag<Integer>();
	}
	
	public Graph(In in){
		this(in.readInt());
		int E=in.readInt();
		for(int i=0;i<E;i++){
			int v=in.readInt();
			int w=in.readInt();
			addEdge(v,w);
		}
	}
	
	public void addEdge(int v,int w){
		adj[v].add(w);
		adj[w].add(v);
		this.E++;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
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
		Graph G=new Graph(new In (args[0]));
		int s=Integer.parseInt(args[1]);//输入顶点
		System.out.println(G);
		DepthFirstPath dfp=new DepthFirstPath(G,s);
		BreadFirstPath bfp=new BreadFirstPath(G,s);
		for(int v=0;v<G.V();v++){
			if(bfp.hasPath(v))
				System.out.print(v+" ");
		}
		System.out.println("");
		
		if(bfp.count()!=G.V())//若任意两点都是连通的，那么图被称作连通图，则该图是连通的
			System.out.print("Not");
		System.out.print(" Connected"+"\n");
		System.out.println(bfp.count());
		
		/*for(int p:bfp.pathTo(3)){
			if(p==s) System.out.print(p);
			else System.out.print("-"+p);
		}	*/
	}
}

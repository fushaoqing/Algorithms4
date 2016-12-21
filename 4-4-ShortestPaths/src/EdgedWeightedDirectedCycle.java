import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class EdgedWeightedDirectedCycle {
//通过查找是否有负权重环，找到汇率积大于1的环进行套汇
	private boolean[] marked;
	private boolean[] onStack;
	private DirectedEdge[] edgeTo;
	private Stack<DirectedEdge> cycle;	
	public EdgedWeightedDirectedCycle(EdgedWeightedDigraph G){
		marked=new boolean[G.V()];
		edgeTo=new DirectedEdge[G.V()];
		onStack=new boolean[G.V()];
		for(int s=0;s<G.V();s++){
			if(!marked[s])
				dfs(G,s);
		}
	}
	
	public void dfs(EdgedWeightedDigraph G,int v){
		marked[v]=true;
		onStack[v]=true;
		for(DirectedEdge e:G.adj(v)){
			if(this.hasCycle()) return;
			int w=e.to();
			if(!marked[w]){
				edgeTo[w]=e;
				dfs(G,w);
			}
			else if(onStack[w]){
				cycle=new Stack<DirectedEdge>();
				DirectedEdge f=e;
				while(f.from()!=w){
					cycle.push(f);
					f=edgeTo[f.from()];
				}
				cycle.push(f);
				return;
			}
		}
		onStack[v]=false;
		//System.out.print(" "+v);
	}
	
	public boolean hasCycle(){
		return cycle!=null;
	}
	
	public Iterable<DirectedEdge> cycle(){
		return cycle;
	}
	
	public static void main(String[] args) {
		EdgedWeightedDigraph G=new EdgedWeightedDigraph(new In(args[0]));
		System.out.println(G);
		EdgedWeightedDirectedCycle d=new EdgedWeightedDirectedCycle(G);
		System.out.println("");
		for(boolean t:d.onStack)
			System.out.print(" "+t);
		System.out.println("");
		for(DirectedEdge e:d.cycle())
			System.out.print(" "+e);
	}
}

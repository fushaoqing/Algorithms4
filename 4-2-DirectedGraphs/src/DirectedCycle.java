import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {

	private boolean[] marked;
	private boolean[] onStack;
	private int[] edgeTo;
	private Stack<Integer> cycle;	
	public DirectedCycle(Digraph G){
		marked=new boolean[G.V()];
		edgeTo=new int[G.V()];
		onStack=new boolean[G.V()];
		for(int s=0;s<G.V();s++){
			if(!marked[s])
				dfs(G,s);
		}
	}
	
	public void dfs(Digraph G,int v){
		marked[v]=true;
		onStack[v]=true;
		for(int w:G.adj(v)){
			if(this.hasCycle()) return;
			else if(!marked[w]){
				edgeTo[w]=v;
				dfs(G,w);
			}
			else if(onStack[w]){//将环上的点压入栈
				cycle=new Stack<Integer>();
				for(int x=v;x!=w;x=edgeTo[x]){
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v]=false;
		//System.out.print(" "+v);
	}
	
	public boolean hasCycle(){
		return cycle!=null;
	}
	
	public Iterable<Integer> cycle(){
		return cycle;
	}
	
	public static void main(String[] args) {
		Digraph G=new Digraph(new In(args[0]));
		System.out.println(G);
		DirectedCycle d=new DirectedCycle(G);
		System.out.println("");
		for(boolean t:d.onStack)
			System.out.print(" "+t);
		System.out.println("");
		for(int c:d.cycle())
			System.out.print(" "+c);
	}
}

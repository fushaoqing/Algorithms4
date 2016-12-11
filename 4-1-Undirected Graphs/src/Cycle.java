import edu.princeton.cs.algs4.In;

public class Cycle {
	/*判断是否是无环图*/
	private boolean[] marked;//储存每个点
	private boolean hasCycle;
	public Cycle(Graph G){
		marked =new boolean[G.V()];
		for(int s=0;s<G.V();s++)//遍历所有分量
			if(!marked[s])
				dfs(G,s,s);
	}
	
	private void dfs(Graph G,int v,int u){//从v出发，判断能否走通到u
		marked[v]=true;
		System.out.println(v);
		for(int w:G.adj(v)){
			if(!marked[w]){
				System.out.print(w+" ");
				dfs(G,w,v);
			}
			else if(w==u) hasCycle=true;
		}
	}
	
	public boolean hadCycle(){
		return hasCycle;
	}
	
	public static void main(String[] args){
		Graph G=new Graph(new In("F:\\算法\\Algorithms4\\4-1-Undirected Graphs\\src\\tinyCG.txt"));
		Cycle c=new Cycle(G);
		System.out.println(c.hadCycle());
	}
}

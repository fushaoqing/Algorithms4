import edu.princeton.cs.algs4.In;

public class TwoColor {

	private boolean[] marked;//标记检查过颜色的点
	private boolean[] color;
	private boolean isTowColorble=true;
	public TwoColor(Graph G){
		marked=new boolean[G.V()];
		color=new boolean[G.V()];
		for(int s=0;s<G.V();s++){
			if(!marked[s])
				dfs(G,s);
		}
	}
	
	public void dfs(Graph G,int v){
		marked[v]=true;
		for(int w:G.adj(v)){
			if(!marked[w]){
				color[w]=!color[v];
				dfs(G,w);
			}
			else if(color[w]==color[v]) isTowColorble=false;
		}
	}
	
	public boolean isBipartite(){
		return isTowColorble;
	}
	
	public static void main(String[] args){
		Graph G=new Graph(new In("F:\\算法\\Algorithms4\\4-1-Undirected Graphs\\src\\tinyCG.txt"));
		TwoColor t=new TwoColor(G);
		System.out.println(t.isBipartite());
	}
}

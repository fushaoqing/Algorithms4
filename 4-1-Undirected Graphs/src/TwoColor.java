
public class TwoColor {

	private boolean[] marked;
	private boolean[] color;
	private boolean isTowColorble=true;
	public TwoColor(Graph G){
		color=new boolean[G.V()];
	}
	
	public void dfs(Graph G,int v){
		marked[v]=true;
		for(int w:G.adj(v)){
			if(!marked[w]){
				color[w]=!color[v];
				dfs(G,w);
			}
			else 	
		}
	}
}

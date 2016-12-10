
public class Cycle {
	/*判断是否是无环图*/
	private boolean[] marked;
	private boolean hasCycle;
	public Cycle(Graph G){
		marked =new boolean[G.V()];
		for(int s=0;s<G.V();s++)//遍历所有点，判断是否能通过其他点连回自身
			if(!marked[s])
				dfs(G,s,s);
	}
	
	private void dfs(Graph G,int v,int u){
		marked[v]=true;
		for(int w:G.adj(v)){
			if(!marked[w])
				dfs(G,w,v);
			else if(w==v) hasCycle=true;
		}
			
	}
	
	public boolean hadCycle(){
		return hasCycle;
	}
	
	public static void main(String[] args){
		
	}
}

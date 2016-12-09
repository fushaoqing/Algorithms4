import edu.princeton.cs.algs4.In;

public class CC {

	private boolean[] marked;
	private int[] id;//id相同的点表示在同一分量上
	private int[] size;//储存每个连通分量上的点个数
	private int count;//连通分量个数
	
	public CC(Graph G){
		marked=new boolean[G.V()];
		id=new int[G.V()];
		size=new int[G.V()];
		
		for(int v=0;v<G.V();v++){
			if(!marked[v]){
				dfs(G,v);//把同一个分量的所有点连上
				count++;
			}
		}
	}
	
	public void dfs(Graph G,int v){
		marked[v]=true;
		size[count]++;
		for(int w:G.adj(v)){
			if(!marked[w]){
				id[w]=count;
				dfs(G,w);
			}	
		}
	}
	
	public int size(int v){
		return size[v];
	}
	
	public int count(){
		return count;
	}
	
	public static void main(String[] args){
		In in = new In(args[0]);
        Graph G = new Graph(in);
        CC cc = new CC(G);
        System.out.println(cc.count());
	}
}

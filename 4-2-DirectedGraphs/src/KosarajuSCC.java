import edu.princeton.cs.algs4.In;

public class KosarajuSCC {
//计算图中的强连通分量
	private boolean[] marked;
	private int[] id;//id相同的点表示在同一分量上
	private int count;//连通分量个数
	public KosarajuSCC(Digraph G){
		marked=new boolean[G.V()];
		id=new int[G.V()];
		DepthFirsteOrder order=new DepthFirsteOrder(G.reverse());
		for(int s:order.reversePost()){//第一次在反图中深度优先搜索得到逆后序排列
			//第二次按逆后序排列在原图中深度优先搜索
			if(!marked[s]){
				dfs(G,s);	
				count++;
			}
		}
	}
	
	public void dfs(Digraph G,int v){
		marked[v]=true;
		id[v]=count;
		for(int w:G.adj(v)){
			if(!marked[w])
				dfs(G,w);
		}
	}
	
	public boolean strongConnected(int v,int w){
		return id[v]==id[w];
	}
	
	
	public int id(int v){
		return id[v];
	}
	
	public int count(){
		return count;
	}
	public static void main(String[] args) {
		Digraph G=new Digraph(new In(args[0]));
		KosarajuSCC kcc=new KosarajuSCC(G);
		System.out.println(kcc.count()+" components");
	}

}

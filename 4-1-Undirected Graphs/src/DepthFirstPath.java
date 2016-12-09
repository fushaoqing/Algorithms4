import edu.princeton.cs.algs4.Stack;
public class DepthFirstPath {
	/*以深度搜索优先，沿着一条路径搜索到底，知道没有相邻点再换路径*/
	private boolean[] marked;//记录各个顶点是否被dfs过
	private int[] edgeTo;//记录顶点到各个点的一条路径
	private int s;
	private int count;
	public DepthFirstPath(Graph G,int s){//深度优先搜索需要一幅图和一个顶点
		marked=new boolean[G.V()];
		edgeTo=new int[G.E()];
		this.s=s;
		dfs(G,s);
	}
	public void dfs(Graph G,int v){
		marked[v]=true;
		count++;
		for(int w:G.adj(v)){
			if(!marked[w]){
				edgeTo[w]=v;
				dfs(G,w);
			}	
		}
	}
	
	public boolean hasPath(int v){
		return marked[v];
	}
	
	public int count(){//返回与s连通的总点数
		return count;
	}
	public Iterable<Integer> pathTo(int v){
		if(!hasPath(v)) return null;
		Stack<Integer> stack=new Stack<Integer>();
		for(int x=v;x!=this.s;x=edgeTo[x]){
			stack.push(x);
		}
		stack.push(s);
		return stack;
	}
}

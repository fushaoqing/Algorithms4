import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class DirectedDepthFirstPaths {
/*求有向图中顶点到某一点的路径*/
	private boolean[] marked;//记录各个顶点是否被dfs过
	private int[] edgeTo;//记录顶点到各个点的一条路径
	private int s;
	private int count;
	public DirectedDepthFirstPaths(Digraph G,int s){//深度优先搜索需要一幅图和一个顶点
		marked=new boolean[G.V()];
		edgeTo=new int[G.E()];
		this.s=s;
		dfs(G,s);
	}
	public void dfs(Digraph G,int v){
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
	public static void main(String[] args) {
		In in = new In(args[0]);
        Digraph G = new Digraph(in);
        DirectedDepthFirstPaths ddfp=new DirectedDepthFirstPaths(G,0);
        System.out.println(ddfp.hasPath(3));
        for(int w:ddfp.pathTo(3)){
        	if(w==0)
        		System.out.print(0);
        	else System.out.print("-"+w);
        }
        System.out.println("");
	}

}

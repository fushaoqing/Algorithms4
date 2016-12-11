import edu.princeton.cs.algs4.Queue;

import edu.princeton.cs.algs4.Stack;

public class BreadFirstPath {
	/*以广度搜索优先，当出现相邻点时，从相邻点逐个搜索至下一个相邻点*/
	private boolean[] marked;//记录各个顶点是否被dfs过
	private int[] edgeTo;//记录顶点到各个点的一条路径
	private int s;
	private int count;
	
	public BreadFirstPath(Graph G,int s){
		marked=new boolean[G.V()];
		edgeTo=new int[G.V()];
		this.s=s;
		bfs(G,s);
	}
	
	public void bfs(Graph G,int v){
		Queue<Integer> queue=new Queue<Integer>();
		marked[v]=true;//标记起点
		queue.enqueue(v);
		while(!queue.isEmpty()){
			int s=queue.dequeue();
			for(int w:G.adj(s)){
				if(!marked[w]){
					marked[w]=true;
					count++;
					edgeTo[w]=s;
					queue.enqueue(w);
				}	
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

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DirectedBreadthFirstDirectedPaths {
	/*以广度搜索优先，当出现相邻点时，从相邻点逐个搜索至下一个相邻点*/
	private boolean[] marked;//记录各个顶点是否被dfs过
	private int[] edgeTo;//记录顶点到各个点的一条路径
	private int s;
	private int count;
	
	public DirectedBreadthFirstDirectedPaths(Digraph G,int s){
		marked=new boolean[G.V()];
		edgeTo=new int[G.E()];
		this.s=s;
		bfs(G,s);
	}
	
	public void bfs(Digraph G,int v){
		Queue<Integer> queue=new Queue<Integer>();
		marked[v]=true;
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
	public static void main(String[] args) {
		In in = new In(args[0]);
        Digraph G = new Digraph(in);
        DirectedBreadthFirstDirectedPaths dbfp=new DirectedBreadthFirstDirectedPaths(G,8);
        System.out.println(dbfp.hasPath(11));
        for(int w:dbfp.pathTo(11)){
        	if(w==8)
        		System.out.print(8);
        	else System.out.print("-"+w);
        }
        System.out.println("");
	}
}


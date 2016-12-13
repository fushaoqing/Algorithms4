import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedDFS {

	private boolean[] marked;//数组用于标记点是否达到
	private int count;
	public DirectedDFS(Digraph d,int s){//找出可由顶点到达的点，判断单点可达性
		marked=new boolean[d.V()];
		dfs(d,s);
	}
	
	public DirectedDFS(Digraph d,Iterable<Integer> sources){//判断多点可达性
		marked=new boolean[d.V()];
		for(int w:sources)
			dfs(d,w);
	}
	
	public void dfs(Digraph d,int v){
		marked[v]=true;
		count++;
		for(int w:d.adj(v)){
			if(!marked[w])
				dfs(d,w);
		}
	}
	
	public boolean marked(int v){
		return marked[v];
	}
	
	public int count(){
		return count;
	}
	public static void main(String[] args) {
		In in = new In(args[0]);
        Digraph G = new Digraph(in);
        Bag<Integer> sources = new Bag<Integer>();
        for (int i = 1; i < args.length; i++) {
            int s = Integer.parseInt(args[i]);
            sources.add(s);
        }

        DirectedDFS dfs = new DirectedDFS(G, sources);//输入一个点集
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) StdOut.print(v + " ");
        }
        StdOut.println();
	}

}

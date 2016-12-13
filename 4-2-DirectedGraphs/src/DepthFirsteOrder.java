import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirsteOrder {
	private boolean[] marked;//记录各个顶点是否被dfs过
	private Queue<Integer> pre;//前序排列
	private Queue<Integer> post;//后序排列
	private Stack<Integer> reversePost;//逆后序排列
	public DepthFirsteOrder(Digraph G){//深度优先搜索需要一幅图和一个顶点
		marked=new boolean[G.V()];
		pre=new Queue<Integer>();
		post=new Queue<Integer>();
		reversePost=new Stack<Integer>();
		for(int s=0;s<G.V();s++)
			if(!marked[s])
				dfs(G,s);
	}
	
	public void dfs(Digraph G,int v){
		pre.enqueue(v);//将所有点按照深度优先的顺序排列
		marked[v]=true;
		for(int w:G.adj(v)){
			if(!marked[w]){
				dfs(G,w);
			}
		}
		post.enqueue(v);
		reversePost.push(v);
	}
	
	public Iterable<Integer> pre(){
		return pre;
	}
	
	public Iterable<Integer> post(){
		return post;
	}
	
	public Iterable<Integer> reversePost(){
		return reversePost;
	}
	
	public static void main(String[] args){//拓扑排序解决优先级调度问题
		String filename = args[0];
		String sepration=args[1];
		DirectedSymbolGraph sg = new DirectedSymbolGraph(filename,sepration);
		DirectedCycle cycleFinder=new DirectedCycle(sg.G());
		 //System.out.println(cycleFinder.hasCycle());
		 if(!cycleFinder.hasCycle()){//若图中无环，才可进行拓扑排序
			DepthFirsteOrder dfo=new DepthFirsteOrder(sg.G());
			Iterable<Integer> order=dfo.reversePost();
			for(int o:order)
				 System.out.println(sg.name(o));
		}
		 
	}
}

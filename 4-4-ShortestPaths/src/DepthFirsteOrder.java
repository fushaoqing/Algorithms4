import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirsteOrder {
	private boolean[] marked;//记录各个顶点是否被dfs过
	private Queue<Integer> pre;//前序排列
	private Queue<Integer> post;//后序排列
	private Stack<Integer> reversePost;//逆后序排列
	public DepthFirsteOrder(EdgedWeightedDigraph G){//深度优先搜索需要一幅图和一个顶点
		marked=new boolean[G.V()];
		pre=new Queue<Integer>();
		post=new Queue<Integer>();
		reversePost=new Stack<Integer>();
		for(int s=0;s<G.V();s++)
			if(!marked[s])
				dfs(G,s);
	}
	
	public void dfs(EdgedWeightedDigraph G,int v){
		pre.enqueue(v);//将所有点按照深度优先的顺序排列
		marked[v]=true;
		for(DirectedEdge e:G.adj(v)){
			int w=e.to();
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
		
	}
}

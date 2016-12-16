import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST {

	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;
	
	public LazyPrimMST(EdgeWeighted G){
		marked=new boolean[G.V()];
		mst=new Queue<Edge>();
		pq=new MinPQ<Edge>(); 
		
		for(int s=0;s<G.V();s++)
			if(!marked[s])
				lazyPrim(G,s);	
	}
	
	public void lazyPrim(EdgeWeighted G,int s){
		visit(G,s);//假设G是连通的
		while(!pq.isEmpty()){
			Edge e=pq.delMin();
			int v=e.either();//取出一条边的两个点
			int w=e.other(v);
			if(marked[v]&&marked[w]) continue;//边失效
			mst.enqueue(e);
			if(!marked[v]) visit(G,v);//先遍历点的相邻边，在找出其中有效的最小边
			if(!marked[w]) visit(G,w);
		}
	}
	public void visit(EdgeWeighted G,int v){
		marked[v]=true;
		for(Edge e:G.adj(v)){
			int w=e.other(v);
			if(!marked[w])
				pq.insert(e);
		}
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public double weight(){
		double weightSum=0.0;
		for(Edge e:mst)
			weightSum+=e.weight();
		return weightSum;
	}
	
	public static void main(String[] args){
		EdgeWeighted edw=new EdgeWeighted(new In(args[0]));
		LazyPrimMST lmst=new LazyPrimMST(edw);
		System.out.println(lmst.weight());
		for(Edge e:lmst.edges())
			System.out.println(e);
	}
}

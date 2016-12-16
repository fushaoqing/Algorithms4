import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class KruskaMST {

	private Queue<Edge> mst;
	public KruskaMST(EdgeWeighted G){
		mst=new Queue<Edge>();
		MinPQ<Edge> pq=new MinPQ<Edge>();
		for(Edge e:G.edges())
			pq.insert(e);
		UF uf=new UF(G.V());
		
		while(!pq.isEmpty()&&mst.size()<G.V()-1){
			Edge e=pq.delMin();
			int v=e.either();
			int w=e.other(v);
			if(uf.connected(v, w)) continue;//v-w边失效
			uf.union(v, w);//将边加入最小生成树中
			mst.enqueue(e);
		}
	}
	
	public double weight(){
		double weightSum=0.0;
		for(Edge e:mst)
			weightSum+=e.weight();
		return weightSum;
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public static void main(String[] args) {
		EdgeWeighted edw=new EdgeWeighted(new In(args[0]));
		KruskaMST kmst=new KruskaMST(edw);
		System.out.println(kmst.weight());
		for(Edge e:kmst.edges())
			System.out.println(e);
	}
}

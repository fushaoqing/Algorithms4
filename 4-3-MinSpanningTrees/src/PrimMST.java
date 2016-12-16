import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;

public class PrimMST {

	private int weight;
	private boolean[] marked;
	private Edge[] edgeTo;//Edge[w]表示到某一点的边
	private double[] distTo;//distTo[w]表示到某一点的边的权值	
	private IndexMinPQ<Double> pq;//带索引的优先队列，索引表示w，索引存储weight
	
	public PrimMST(EdgeWeighted G){
		marked=new boolean[G.V()];
		edgeTo=new Edge[G.V()];
		distTo=new double[G.V()];
		for(int w=0;w<distTo.length;w++)
			distTo[w]=Double.POSITIVE_INFINITY;//初始值为正无穷，只要有w的weight就添加进去
		pq=new IndexMinPQ<Double>(G.V());
		
		for(int s=0;s<G.V();s++){//遍历所有连通分量，找出每个分量的最小生成树，各个数的wight加起来就是图的总weight
			if (!marked[s])
				prim(G,s);
		}
	}
	
	public void prim(EdgeWeighted G,int s){
		distTo[s]=0.0;
		pq.insert(s, 0.0);//从定点0开始
		while(!pq.isEmpty()){
			visit(G,pq.delMin());
		}
	}
	
	public void visit(EdgeWeighted G,int v){
		marked[v]=true;
		for(Edge e:G.adj(v)){
			int w=e.other(v);
			if(marked[w]) continue;//marked[w]表示v已经在最小生成树中，则w-v失效
			if(e.weight()<distTo[w]){//只保存w到最小生成树最小的那条边
				edgeTo[w]=e;
				distTo[w]=e.weight();
				if(pq.contains(w)) pq.change(w, e.weight());
				else 			   pq.insert(w, e.weight());
			}
		}
	}
	
	public double weight(){
		double weightSum=0.0;
		for(double x:distTo)
			weightSum+=x;
		return weightSum;
	}
	
	public Iterable<Edge> edges(){
		 Queue<Edge> mst = new Queue<Edge>();
		 for(int v = 0; v < edgeTo.length; v++ ){
			 if(edgeTo[v]!=null)
				 mst.enqueue(edgeTo[v]);
		 }
		 return mst;
	}
	public static void main(String[] args) {
		EdgeWeighted edw=new EdgeWeighted(new In(args[0]));
		PrimMST pmst=new PrimMST(edw);
		System.out.println(pmst.weight());
		for(Edge e:pmst.edges())
			System.out.println(e);
	}

}

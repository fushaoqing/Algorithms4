import edu.princeton.cs.algs4.In;

public class DijkstraSPAll {

	private DijkstraSP[] all;
	public DijkstraSPAll(EdgedWeightedDigraph G){
		all=new DijkstraSP[G.V()];
		for(int v=0;v<G.V();v++){
			all[v]=new DijkstraSP(G,v);//以每个点作为顶点，创建DijkstraSP
		}
	}
	
	public Iterable<DirectedEdge> path(int s,int v){
		return all[s].pathTo(v);
	}
	
	public double distTo(int s,int v){
		return all[s].distTo(v);
	}
	
	public static void main(String[] args) {
		EdgedWeightedDigraph G=new EdgedWeightedDigraph(new In(args[0]));
		DijkstraSPAll dspall=new DijkstraSPAll(G);
		for(DirectedEdge e:dspall.path(1, 2))
			System.out.println(e);
		System.out.println(dspall.distTo(1, 2));
	}

}

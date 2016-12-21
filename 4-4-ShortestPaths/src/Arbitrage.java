import edu.princeton.cs.algs4.In;

public class Arbitrage {
	public static void main(String[] args){
		In in=new In(args[0]);
		int V=in.readInt();
		String[] name=new String[V];
		EdgedWeightedDigraph G=new EdgedWeightedDigraph(V);
		for(int v=0;v<V;v++){
			name[v]=in.readString();
			for(int w=0;w<V;w++){
				double rate=in.readDouble();
				DirectedEdge e=new DirectedEdge(v,w,-Math.log(rate));//将汇率取对数再取反
				G.addEdge(e);
			}
		}
		
		BellmanFordSP bsp=new BellmanFordSP(G,0);
		if(bsp.hasNegativeCycle()){
			double stake=1000.0;
			for(DirectedEdge e:bsp.NegativeCycle()){
				System.out.printf("%10.5f %s",stake,name[e.from()]);
				stake*=(Math.exp(-e.weight()));
				System.out.printf("=%10.5f %s\n",stake,name[e.to()]);
			}
		}else System.out.println("No Arbitrage Opportunity");
		 
	}
}

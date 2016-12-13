import edu.princeton.cs.algs4.In;

public class TransitiveClosure {

	private DirectedDFS[] all;
	
	public TransitiveClosure(Digraph G){
		all=new DirectedDFS[G.V()];
		for(int v=0;v<G.V();v++){
			all[v]=new DirectedDFS(G,v);//all[v]表示v可达的点的集合
		}		
	}
	
	public boolean reachBle(int v,int w){//判断v到w的可达性
		return all[v].marked(w);
	}
	
	public void printClosure(){
		System.out.print("  ");
		for(int v=0;v<all.length;v++){
			System.out.print(v+" ");
		}
		System.out.println("");
		for(int v=0;v<all.length;v++){
			System.out.print(v);
			for(int w=0;w<all.length;w++){
				if(reachBle(v,w))
					System.out.print(" "+"T");
				else System.out.print("  ");
			}
			System.out.println("");
		}
	}
	public static void main(String[] args) {
		Digraph G=new Digraph(new In(args[0]));
		TransitiveClosure t=new TransitiveClosure(G);
		t.printClosure();

	}

}

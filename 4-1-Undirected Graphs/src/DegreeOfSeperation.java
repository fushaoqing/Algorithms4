import edu.princeton.cs.algs4.StdIn;

public class DegreeOfSeperation {
	public static void main(String[] args) {
		SymbolGraph sg=new SymbolGraph("F://算法//Algorithms4//4-1-Undirected Graphs//src//routes.txt"," ");
		Graph G=sg.graph();
		String source="JFK";//选取顶点
		if(!sg.contains(source))
		{System.out.println(source+"not in database");return;}

		int s=sg.index(source);//得到名称为"JFK"的索引
		BreadFirstPath bfp=new BreadFirstPath(G,s);//广度优先求名称之间的最短路径
		
		/*for(int v:G.adj(sg.index("DFW")))
			System.out.println(sg.name(v));*/
		while(!StdIn.isEmpty()){
			String sink=StdIn.readLine();
			if(sg.contains(sink)){
				int t=sg.index(sink);
				if(bfp.hasPath(t)){
					for(int v:bfp.pathTo(t))
						System.out.println(" "+sg.name(v));//打印出s到sink的最短路径
				}else System.out.println("Not Connected");
			}else System.out.println("Not Connected");
		}
	}
}

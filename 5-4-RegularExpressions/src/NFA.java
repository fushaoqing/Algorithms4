import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class NFA {

	private char[] re;
	private Digraph G;
	private int M;
	//int count=0;
	public NFA(String regexp){
		Stack<Integer> ops=new Stack<Integer>();
		re=regexp.toCharArray();//将模式转换为字符数组
		M=re.length;
		G=new Digraph(M+1);
		for(int i=0;i<M;i++){
			//将'('和'|'压入栈中
			if(re[i]=='('||re[i]=='|'){
				ops.push(i);
			}else if(re[i]==')'){
				int or=ops.pop();
				if(re[or]=='|'){
					int lp=ops.pop();
					G.addEdge(lp, or+1);
					G.addEdge(or, i);
				}
			}
			if(i<M-1&&re[i+1]=='*'){
				G.addEdge(i, i+1);
				G.addEdge(i+1, i);
			}
			if(re[i]=='('||re[i]=='*'||re[i]==')')
				G.addEdge(i, i+1);
		}
	}
	
	public boolean search(String txt){
		Bag<Integer> pc=new Bag<Integer>();
		DirectedDepthFirstPaths dfp=new DirectedDepthFirstPaths(G,0);
		for(int v=0;v<G.V();v++)
			if(dfp.hasPath(v))
				pc.add(v);//找到初始状态0可达点
		for(int i=0;i<txt.length();i++){
			Bag<Integer> match=new Bag<Integer>();//match为点v可达到的集合
			for(int v:pc)
				if(v<M)
					if(re[v]==txt.charAt(i)||re[v]=='.')
						match.add(v+1);//匹配转换
			pc=new Bag<Integer>();
			/*for(int v:match)
				System.out.println(re[v]);
			System.out.println(++count);*/
			dfp=new DirectedDepthFirstPaths(G,match);
			for(int v=0;v<G.V();v++)
				if(dfp.hasPath(v))
					pc.add(v);//匹配了部分字符后(match),所有非匹配转换所能到达的点
		}
		
		for(int v:pc)
			if(v==M)
				return true;
		return false;
	}
	
	public static void main(String[] args) {
		String regexp1="(.*"+"(A*B|AC)D"+".*)";
		NFA nfa=new NFA(regexp1);
		In in=new In(args[0]);
		while(in.hasNextLine()){
			String txt=in.readLine();
			if (nfa.search(txt))
				System.out.println(txt);
		}
		String regexp2="(A*|(A*BA*BA*)*)";
		NFA nfa2=new NFA(regexp2);
		System.out.println(nfa2.search("BABBAAA"));
		System.out.println(nfa2.search("AAA"));
	}
}

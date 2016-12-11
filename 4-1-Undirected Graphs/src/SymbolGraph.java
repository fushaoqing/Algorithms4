import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class SymbolGraph {
/*构造字典来通过名称找到索引*/
	private ST<String,Integer> st=new ST<String,Integer>();//用于存储名称和索引
	private String[] keys;
	private Graph G;
	
public SymbolGraph(String stream,String sp){//输入读取文件和分隔符sp
	In in=new In(stream);
	st=new ST<String,Integer>();
	while(in.hasNextLine()){
		String[] a=in.readLine().split(sp);
		for(String s:a){
			if(!st.contains(s))//避免名称重复
				st.put(s, st.size());//输入值和索引，索引从0开始
		}
	}
	
	keys=new String[st.size()];
	for(String name:st.keys())
		keys[st.get(name)]=name;//构造名称和索引的反向数组
	
	G=new Graph(st.size());
	in=new In(stream);//重新读一遍文件
	while(in.hasNextLine()){
		String[] a=in.readLine().split(sp);
		int v=st.get(a[0]);
		for(int i=1;i<a.length;i++){//将每行的顶点和该行其他点相连
				G.addEdge(v, st.get(a[i]));		
		}
	}
}	

public boolean contains(String s){
	return st.contains(s);
}

public int index(String s){
	return st.get(s);
}

public String name(int v){
	return keys[v];
}

public Graph graph(){
	return G;
}
}

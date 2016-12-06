import java.util.HashSet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
/*索引文件：F:\算法\Algorithms4\3-5-TablesAppication\src\ip.csv
 *F:\算法\AlgorithmsSedgewick-master\3-Searching\3-5-Applications\amino.csv*/
public class LookUpCsv {
	public static void main(String[] args){
		ST<String,String> st=new ST<String,String>();
		In in=new In(args[0]);
		int keyField=Integer.parseInt(args[1]);//截取文件中每一行一部分作为键值
		int valField=Integer.parseInt(args[2]);
		while(in.hasNextLine()){
			String[] token=in.readLine().split(",");
			String key=token[keyField];
			String val=token[valField];
			st.put(key, val);
		}
		
		while(!StdIn.isEmpty()){
			String key=StdIn.readString();
			if(st.contains(key))
				System.out.println(st.get(key));
		}
	}
}

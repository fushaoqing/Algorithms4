import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

public class LookupIndex {
	/*F:\算法\AlgorithmsSedgewick-master\3-Searching\3-5-Applications\amino.csv*/
	public static void main(String[] args) {
		In in=new In(args[0]);//索引数据源
		String sp=args[1];//sp作为分隔符
		ST<String,Queue<String>> st=new ST<String,Queue<String>>();//通过队列实现一对多的索引
		ST<String,Queue<String>> ts=new ST<String,Queue<String>>();//反向索引
		while(!in.isEmpty()){//遍历每一行键和值
			String[] token =in.readLine().split(sp);
			String key=token[0];
			for(int i=1;i<token.length;i++){
				String val=token[i];
				if(!st.contains(key)) st.put(key, new Queue<String>());
				if(!ts.contains(val)) ts.put(val, new Queue<String>());
				st.get(key).enqueue(val);
				ts.get(val).enqueue(key);
			}
		}
		while(!StdIn.isEmpty()){
			String query =StdIn.readString();
			if(st.contains(query))//根据键查出对应的所有值
				for(String each:st.get(query))
					System.out.println(" "+each);
			if(ts.contains(query))//根据值查出对应的所有键
			for(String each:ts.get(query))
				System.out.println(" "+each);
		}
	}

}

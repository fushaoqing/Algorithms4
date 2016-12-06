import java.util.HashSet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
public class WhiteFilter {//过滤掉白名单以外的字符串
	/*白名单：F:\算法\Algorithms4\3-5-TablesAppication\src\list.txt*/
	public static void main(String[] args) {
		HashSet<String> set=new HashSet<String>();
		In in=new In(args[0]);
		while(!in.isEmpty()){
			set.add(in.readString());
		}
		while(!StdIn.isEmpty()){
			String key=StdIn.readString();
			if(!set.contains(key))//黑名单，过滤掉黑名单上外的字符串
				System.out.println(key);
			/*if(set.contains(key))
				System.out.println(key);*/
		}
	}
}

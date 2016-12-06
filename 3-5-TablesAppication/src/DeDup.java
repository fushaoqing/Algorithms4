import java.util.HashSet;
import edu.princeton.cs.algs4.StdIn;
public class DeDup {
	public static void main(String args[]){
		HashSet<String> set=new HashSet<String>();
		while(!StdIn.isEmpty()){
			String key=StdIn.readString();
			if(!set.contains(key)){
				set.add(key);
				System.out.println(key);
			}		
		}
	}
}

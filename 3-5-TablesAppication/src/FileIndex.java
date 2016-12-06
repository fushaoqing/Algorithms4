import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

public class FileIndex {
	public static void main(String[] args){
		//文件是独一无二，不可重复的，所以用SET
		ST<String,SET<File>> st=new ST<String,SET<File>>();
		String[] fileName=new String[2];
		for(String name:args){
			File file=new File(name);
			In in=new In(file);
			while(!in.isEmpty()){
				String word =in.readString();
				if(!st.contains(word)) st.put(word, new SET<File>());//没有key,就创建键值对，再赋值
				st.get(word).add(file);
			}
		}
		
		while(!StdIn.isEmpty()){
			String key=StdIn.readString();
			if(st.contains(key))
				for(File f:st.get(key))
					System.out.println(" "+f.getName());//打印出现该单词的文件名
		}
	}
	
}

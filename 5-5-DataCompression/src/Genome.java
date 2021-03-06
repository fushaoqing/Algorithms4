import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class Genome {
	/*ATAGATGCATAGCGCATAGCTAGATGTGCTAGC标准输出上看不到比特流*/
	public static void compress(){//压缩
		Alphabet DNA=new Alphabet("ACTG");
		String s=BinaryStdIn.readString();//读取字符串流
		int N=s.length();
		BinaryStdOut.write(N);
		for(int i=0;i<N;i++){
			int d=DNA.toIndex(s.charAt(i));//将字符转换为字母表中的索引
			BinaryStdOut.write(d,DNA.lgR());//每次输出bits前两位
		}
		BinaryStdOut.close();
	}
	
	public static void expand(){//解压
		Alphabet DNA=new Alphabet("ACTG");
		int w=DNA.lgR();
		int N=BinaryStdIn.readInt();
		for(int i=0;i<N;i++){
			char c=BinaryStdIn.readChar(w);//每次读取前两位
			BinaryStdOut.write(DNA.toChar(c));
		}
		BinaryStdOut.close();
	}
	
	public static void main(String[] args) {
		if(args[0]=="-") compress();
		if(args[0]=="+") expand();
	}

}

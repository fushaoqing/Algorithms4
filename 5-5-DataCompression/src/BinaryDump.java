import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.StdOut;
/*测试数据：ABRACADABRA!*/
public class BinaryDump {
	public static void main(String[] args) {
		int width=Integer.parseInt(args[0]);
		int cnt;
		for(cnt=0;!BinaryStdIn.isEmpty();cnt++){
			if(width==0) 
				continue;
			if(cnt!=0&&cnt%width==0)
				StdOut.println();//每读取width个字符，换一次行
			if(BinaryStdIn.readBoolean())//读取true,打印1,读取false,打印0
				StdOut.print("1");
			else 
				StdOut.print("0");
		}
		BinaryStdIn.close();
		StdOut.println();
		StdOut.println(cnt+"bits");
	}
}

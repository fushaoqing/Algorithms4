import java.util.Arrays;
import edu.princeton.cs.algs4.*;
/*找出数组中相同的2整数元祖*/
public class ex_1_4_8 {
	public static int count1(int[] a){
		int N=a.length;
		int cnt=0;
		for(int i=0;i<N;i++){
			for(int j=i+1;j<N;j++){
				if(a[i]==a[j]) cnt++;
			}
		}
		return cnt;
	}
	
	public static int count2(int[] a){
		int N=a.length;
		int cnt=0;
		Arrays.sort(a);
		for(int i=0;i<N;i++){
			if(i+1<N&&a[i+1]==a[i]) cnt++;
		}
		return cnt;
	}
	
	public static void main(String[] args) {
		//命令行参数"F:\算法\Algorithms4\1-4-AnalysisOfAlgorithms\src\1Kints.txt"
		int[] test=In.readInts(args[0]);
		
		System.out.println(ex_1_4_8.count2(test));
	}
}

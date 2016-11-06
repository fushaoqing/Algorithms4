import java.util.Arrays;
import edu.princeton.cs.algs4.*;
/*找出不重复数组中和为0的整数对的数量*/
public class TwoSumFast {
	public static int count(int[] a){
		int cnt=0;
		int N=a.length;
		Arrays.sort(a);
		for(int i=0;i<N;i++){
			/*若查找数不存在数组中，返回-1
		               若返回j>i，说明后面有满足条件的数，cnt++
		               若返回j<i.说明是前面的数，属于重复情况*/
			if(BinarySearch.rank(-a[i],a)>i) cnt++;
		}
		return cnt;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test=In.readInts(args[0]);
		System.out.println(TwoSumFast.count(test));
	}
}

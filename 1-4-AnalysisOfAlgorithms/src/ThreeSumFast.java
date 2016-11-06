import java.util.Arrays;
import edu.princeton.cs.algs4.*;
/*减少了循环,改用归并排序和二分查找，增长数量级由N^3优化为N^2*logN*/
public class ThreeSumFast {
	public static int count(int[] a){
		int N=a.length;
		int cnt=0;
		Arrays.sort(a);
		for(int i=0;i<N;i++){
			for(int j=i+1;j<N;j++){
				/*为避免重复，二分查找的返回索引一定不等于i和j,而嵌套循环中j一定大于i,故只须满足>j*/
				if(BinarySearch.rank(-(a[i]+a[j]),a)>j) cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test=In.readInts(args[0]);
		System.out.println(ThreeSumFast.count(test));
	}
}

package ex_1_4_2;

import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
public class ThreeSumInt {
	/*防止两个较大数相加溢出*/
	private static final int max=2147483647;
	public static int count(int[] a){
		int N=a.length;
		int cnt=0;
		Arrays.sort(a);
		for(int i=0;i<N;i++){
			for(int j=i+1;j<N;j++){
				/*为避免重复，二分查找的返回索引一定不等于i和j,而嵌套循环中j一定大于i,故只须满足>j*/
				if(a[i]>0&&a[j]>0){
					if((max-a[i])<a[j]) 
						System.out.println(a[i]+" and "+a[j]+" overflow out");
				}
				else if(BinarySearch.rank(-(a[i]+a[j]),a)>j) cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) {
		//命令行参数"F:\算法\Algorithms4\1-4-AnalysisOfAlgorithms\src\1Kints.txt"
		int[] test=In.readInts(args[0]);
		System.out.println(ThreeSumInt.count(test));
	}
}

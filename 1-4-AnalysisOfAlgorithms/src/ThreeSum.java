import edu.princeton.cs.algs4.*;
public class ThreeSum {
	//找出一个数组中所有和为0的三整数元祖的数量,增长数量级为N^3,为暴力求解
		public static int count(int[] a){
			int cnt=0;
			int n=a.length;
			for(int i=0;i<n;i++){
				for(int j=i+1;j<n;j++){
					for(int k=j+1;k<n;k++){
						if(a[i]+a[j]+a[k]==0) cnt++;
					}
				}
			}
			return cnt;
		}
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			int[] test=In.readInts(args[0]);
			System.out.println(ThreeSum.count(test));
		}
}

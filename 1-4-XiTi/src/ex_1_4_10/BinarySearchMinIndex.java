package ex_1_4_10;
import java.util.Arrays;
import edu.princeton.cs.algs4.*;
public class BinarySearchMinIndex {
	public static int rank(int key,int[] a){
		int lo=0;
		int lh=a.length-1;
		while(lh>lo){
			int mid=(int)((lo+lh)>>1);
			if(key<a[mid]){
				lh=mid-1;
			}else if(key>a[mid]){
				lo=mid+1;
			}else{
				 while(mid>0&&a[mid-1]==key) mid--;//返回与key匹配的最小索引
				return mid;
			}
		}
		return -1;
	}
	
	public static void main(String[] args){
		int[] whiteList={7,8,10,10,9,20};
		Arrays.sort(whiteList);
				StdOut. println(BinarySearchMinIndex.rank(10,whiteList));                     
			
		}
}

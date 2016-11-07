package ex_1_4_11;
import java.util.Arrays;
import edu.princeton.cs.algs4.*;
import ex_1_4_10.BinarySearchMinIndex;
public class StaticSETofInts {
	/*二分查找面向对象版*/
	private int[] whiteList;
	private int N;
	public StaticSETofInts(int[] list){//输入白名单，并排序
		N=list.length;
		whiteList=new int[N];
		
		for(int i=0;i<N;i++){
			whiteList[i]=list[i];//保护性复制
		}
		Arrays.sort(whiteList);
	}
	
	public int rank(int key){
		int lo=0;
		int lh=whiteList.length-1;
		while(lh>=lo){
			int mid=lo+(lh-lo)/2;
			if(key>whiteList[mid]) lo=mid+1;
			else if(key<whiteList[mid]) lh=mid-1;
			else return mid;
		}
		return -1;
	}
	
	public boolean contains(int key){
		return rank(key)!=-1;//若=-1，返回false,表示不包含；若！=-1，返回true，表示包含
	}
	
	public int howmany(int key){
		int cnt=0;
		int mid=rank(key);
		if(mid>=0) return cnt;
		else cnt++;
		int l=mid;
		int r=mid;
		while(true){
			if(l==0||whiteList[--l]!=key){
				if(whiteList[++r]==key) cnt++;
			}
			else if(r==N||whiteList[++r]!=key){
				if(whiteList[--l]==key) cnt++;
			}
			else{
				cnt+=2;
			}
			if(l==0&&r==N) break;
		}
		return cnt;
	}
		

	public static void main(String[] args){
		/*int a=3;
		if(--a>3) System.out.println(1);
		else System.out.println(a);*/
		int[] whiteList={7,10,10,10,9,20};
		StaticSETofInts s=new StaticSETofInts(whiteList);
		Arrays.sort(whiteList);
		StdOut. println(s.howmany(10));                     
			
		}
	}

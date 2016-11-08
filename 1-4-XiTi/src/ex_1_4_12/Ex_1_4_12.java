package ex_1_4_12;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdRandom;

public class Ex_1_4_12 {

	public static void count(int[] a,int[] b){
		for(int i=0;i<a.length;i++){
			if(i==0){
				if(BinarySearch.rank(a[i], b)>=0) System.out.printf("%d ",a[i]);
			}else if(i<a.length&&a[i]!=a[i-1]){
				if(BinarySearch.rank(a[i], b)>=0) System.out.printf("%d ",a[i]);
			}
	}
}
	public static void main(String[] args){
		//int[] a={1,2,2,3};//null;
		//int[] b={1,2,3,4};//null;
		int[] a=null;
		int[] b=null;
		for(int n=1;true;n+=n){
			a=new int[n];
			for(int i=0;i<n;i++) a[i]=StdRandom.uniform(-10, 10);
			b=new int[n];
			for(int j=0;j<n;j++) b[j]=StdRandom.uniform(-10, 10);
			Ex_1_4_12.count(a, b);
			System.out.println("");
		}
	}
	
}

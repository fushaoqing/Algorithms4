package ex_1_4_12;

import java.util.Arrays;

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
		//int[] a={1,2,2,3,5};//null;
		//int[] b={1,2,3,4,5};//null;
		int[] a=null;
		int[] b=null;
		for(int n=250;true;n+=n){
			a=new int[n];
			for(int i=0;i<n;i++) a[i]=StdRandom.uniform(-100, 100);
			b=new int[n];
			for(int j=0;j<n;j++) b[j]=StdRandom.uniform(-100, 100);
			Arrays.sort(a);
			Arrays.sort(b);
			StopWatch s=new StopWatch();
			Ex_1_4_12.count(a, b);
			System.out.println("");
			System.out.printf("%d %5.2fseconds  %5.2f\n",n,s.elapsedTime(),s.elapsedTime()/n);
		}
	}
}

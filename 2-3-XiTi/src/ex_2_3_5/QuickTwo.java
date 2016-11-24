package ex_2_3_5;
import edu.princeton.cs.algs4.*;
//若数组只有两种主键。只需要将数组partition为等于v和不等于v两部分
public class QuickTwo {
	/*将数组切分成2段，<=v，>=v*/
	public static void exch(double[] a,int i,int j){
		Double temp=a[i];
		a[i]=a[j];
		a[j]=temp;//交换a[i]和a[j]
	}
	
	public static void show(double[] a){
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println("");//打印数组所有元素
	}
	
	public static void sort(double[] a){
		int i=0;
		while(i<a.length){//将小的值作为切分点v
			if(a[i]<a[0]) {
				exch(a,0,i);
				break;
			}else if(a[i]>a[0]) break;
			i++;
		}
		sort(a,0,a.length-1);
	}
	
	public static void sort(double[] a,int lo,int hi){
		if(hi<=lo) return;
 		int r=partition(a,lo,hi);
	}
	
	public static int partition(double[] a,int lo,int hi){
			double v=a[lo];
			//分别从左右两边开始扫描
			int l=lo;
			int r=hi+1;
			while(true){
				while(a[++l]==v) if(l==hi) break;//从右向左找到不等于v的数为止
				while(a[--r]!=v) if(r==lo) break;//从左向右找到等于v的数为止
				if(l>=r) break;
				exch(a,l,r);
			}
			
			return r;//返回切分点
	}
	
	public static void main(String[] args) {
		int n=1000000;
		double[] test=new double[n];
		for(int i=0;i<n;i++){
			if(StdRandom.bernoulli(0.5)) test[i]=1;
		}
		QuickTwo.sort(test);
		QuickTwo.show(test);
	}
}

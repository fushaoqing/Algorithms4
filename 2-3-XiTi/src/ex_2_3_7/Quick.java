package ex_2_3_7;
import edu.princeton.cs.algs4.*;
public class Quick {
	private static int num_0;
	private static int num_1;
	private static int num_2;
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
		StdRandom.shuffle(a);//通过随机打乱数组，规避掉对输入的依赖和不利情况
		sort(a,0,a.length-1);
	}
	
	public static void sort(double[] a,int lo,int hi){
		if((hi-lo+1)==0) 		num_0++;
		else if((hi-lo+1)==1) 	num_1++;
		else if((hi-lo+1)==2)	num_2++;
		if(hi<=lo) return;
 		int r=partition(a,lo,hi);
 		/*对左边子数组和右边子数组分别再进行快速排序*/
 		sort(a,lo,r-1);
 		sort(a,r+1,hi);
	}
	
	public static int partition(double[] a,int lo,int hi){
			double v=a[lo];
			//分别从左右两边开始扫描
			int l=lo;
			int r=hi+1;
			while(true){
				while(a[++l]<v) if(l==hi) break;//从右向左找到大于v的数为止
				while(a[--r]>v) if(r==lo) break;//从左向右找到小于v的数为止
				if(l>=r) break;
				exch(a,l,r);
			}
			exch(a,lo,r);
			return r;//返回切分点
	}
	
	public static void main(String[] args) {
		int n=100;
		double[] test=new double[n];
		for(int i=0;i<n;i++){
			test[i]=StdRandom.uniform();
		}
		Quick.sort(test);
		//Quick.show(s);
		System.out.println(Quick.num_0);
		System.out.println(Quick.num_1);
		System.out.println(Quick.num_2);
	}
}

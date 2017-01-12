import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.*;
public class Quick3Way {
	/*将数组切分成3段，<v，=v，>v*/
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
		//StdRandom.shuffle(a);
		sort(a,0,a.length-1);
	}
	
	public static void sort(double[] a,int lo,int hi){
		if(hi<=lo) return;
		int lt=lo;
		int gt=hi;
		int i=lo+1;
		double v=a[lo];
		while(i<=gt){
			//Quick3Way.show(a);
			if(a[i]<v) 		exch(a,lt++,i++);//将小于v的数移到v的前面
			else if(a[i]>v) exch(a,i,gt--);//将大于v的数移到v的后面
			else      		i++; //等于v的数保留原位
		}
		/*再分别对小于v和大于v的子数组进行快速排序*/
		sort(a,lo,lt-1);
		sort(a,gt+1,hi);
	}
	
	public static void main(String[] args) {
		double[] s={1.0,6.3,4.5,2.1,2.4};
		double[] l={7.0,8.0,6.0,11.0,3.0,3.0,5.0};
		//Quick3Way.sort(s);
		//Quick3Way.show(s);
		Quick3Way.sort(l);
		Quick3Way.show(l);
	}
}


public class Heapsort {
/*堆排序，Comparable表示所有继承Comparable接口的类，都具有compareTo方法*/
	
	public static void show(Comparable[] a){
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println("");//打印数组所有元素
	}
	
	public static boolean less(Comparable[] a,int i,int j){//比较二叉堆上的两个元素
		return a[i-1].compareTo(a[j-1])<0;//堆的索引为1至N，实际索引要减1
	}
	
	public static void exch(Comparable[] a,int i,int j){//交换二叉堆上的两个元素
		Comparable temp=a[i-1];
		a[i-1]=a[j-1];
		a[j-1]=temp;
	}
	
	public static void sink(Comparable[] a,int k,int N){//将大的结点向下比较，下沉
		while(2*k<=N){
			int j=2*k;
			//将上层结点与下层较大的那个结点比较
			if(j<N&&less(a,j,j+1)) j++;//判断j<N是为避免，此时j已经是堆上的最后一个元素
			if(!less(a,k,j)) break;
			exch(a,k,j);
			k=j;
		}
	}
	
	public static void sort(Comparable[] a){
		int N=a.length;
		for(int i=N/2;i>=1;i--){//先将数组构造为堆有序
			sink(a,i,N);
		}
		while(N>1){//不断的将最大元素与最后一个元素交换
			exch(a,1,N--);
			sink(a,1,N);//交换完之后，再将前N-1个元素构造堆有序
		}
	}
	public static void main(String[] args){
		Double[] test={1.2,2.8,2.6,1.3,3.5};
		Heapsort.sort(test);
		Heapsort.show(test);
	}
}

package ex_2_1_12;

public class Example {

	public static boolean less(Double v,Double w){
		return v.compareTo(w)<0;//比较大小，v<w,返回true
	}
	
	public static void exch(Double[] a,int i,int j){
		Double temp=a[i];
		a[i]=a[j];
		a[j]=temp;//交换a[i]和a[j]
	}
	
	public static void show(Double[] a){
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println("");//打印数组所有元素
	}
	public static boolean isSorted(Double[] a){
		for(int i=1;i<a.length;i++){
			if(less(a[i],a[i-1])) return false;
		} 
		return true;
	}
	
	/*public static void main(Double[] args){
		Double[] slist={"a","b","c"};
		Example.exch(slist, 0, 1);
		System.out.println(Example.less(slist[0],slist[1]));
		System.out.println(Example.isSorted(slist));
		Example.show(slist);
	}*/
}

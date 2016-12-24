import edu.princeton.cs.algs4.In;

public class Quick3string {
/*三向切分字符串排序*/
	private static int charAt(String s,int d){
		if(d<s.length())
			return s.charAt(d);
		return -1;
	}
	
	private static void exch(String[] a,int i,int j){
		String temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	
	public static void sort(String[] a){
		sort(a,0,a.length-1,0);
	}
	
	private static void sort(String[] a,int lo,int hi,int d){
		if(hi<lo) return;
		int lt=lo;
		int gt=hi;
		int i=lo+1;
		int v=charAt(a[lo],d);
		while(i<=gt){
			int t=charAt(a[i],d);
			if(t<v) exch(a,i++,lt++);
			else if(t>v) exch(a,i,gt--);
			else i++;
		}
		sort(a,lo,lt-1,d);
		if(v>=0) sort(a,lt,gt,d+1);//当到达字符串末尾时，停止递归
		sort(a,gt+1,hi,d);
	}
	
	public static void main(String[] args) {
		In in=new In(args[0]);
		String[] a=in.readAllStrings();
		Quick3string.sort(a);
		for(String each:a)
			System.out.println(each);
	}
}

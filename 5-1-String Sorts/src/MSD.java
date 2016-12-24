import edu.princeton.cs.algs4.In;

public class MSD {

	private static int R=256;
	private static int M=15;//小数组切换排序方法的阈值
	private static String[] aux;
	private static int[] count;
	
	private static int charAt(String s,int d){
		if(d<s.length())
			return s.charAt(d);
		return -1;
	}
	
	private static void sort(String[] a){
		int N=a.length;
		aux=new String[N];
		sort(a,0,N-1,0);
	}
	
	private static void insertSort(String[] a,int lo,int hi,int d){//插入排序
		for(int i=lo;i<=hi;i++){
			for(int j=i;j>lo&&less(a[j],a[j-1],d);j--)//把每个小的元素插到前面取
				exch(a,j,j-1);
		}
	}
	private static boolean less(String v,String w,int d){
		return v.substring(d).compareTo(w.substring(d))<0;
	}
	private static void exch(String[] a,int i,int j){
		String temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	
	private static void sort(String[] a,int lo,int hi,int d){
		if(hi-lo<=M){
			insertSort(a,lo,hi,d);
			return;}
		count=new int[R+2];
		
		for(int i=lo;i<=hi;i++){
			count[charAt(a[i],d)+2]++;//第一个索引一定为0，作为aux的起始位置，第二个索引1对应键-1
		}
		
		for(int r=0;r<R+1;r++){
			count[r+1]+=count[r];
		}
		
		for(int i=lo;i<=hi;i++){
			aux[count[charAt(a[i],d)+1]++]=a[i];//aux索引一定是从0开始
		}
		
		for(int i=lo;i<=hi;i++){
			a[i]=aux[i-lo];//aux长度始终是 N，在分类的时候是从0开始的，故在回写给子数组时，要-lo
		}
		
		for(int r=0;r<R;r++){//按照前一次排序的键将数组分成若干个子数组，对每个子数组再进行排序
			sort(a,lo+count[r],lo+count[r+1]-1,d+1);
		}
	}
	
	public static void main(String[] args) {
		In in=new In(args[0]);
		String[] a=in.readAllStrings();
		MSD.sort(a);
		for(String each:a)
			System.out.println(each);
	}

}

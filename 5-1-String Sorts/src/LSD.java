import edu.princeton.cs.algs4.StdIn;

public class LSD {

	public static void sort(String[] a,int W){
		int N=a.length;
		int R=256;
		String[] aux=new String[N];
		
		for(int d=W-1;d>=0;d--){//从右往左取每一位为键，进行排序，W为字符串长度
			int[] count=new int[R+1];
			
			for(int i=0;i<N;i++){//计算各个键出现频率，注意索引位置要向右移一格
				count[a[i].charAt(d)+1]++;
			}
			
			for(int r=0;r<R;r++){//将频率转化为索引，表示键对应aux的起始位置，任意键的索引等于所有小于该键的索引之和
				count[r+1]+=count[r];
			}
			
			for(int i=0;i<N;i++){//分类，注意将起始位置赋值后，count值要加1，表示下一个位置
				aux[count[a[i].charAt(d)]++]=a[i];//count[a[i].charAt(d)表示键对应的起始位置
			}
			
			for(int i=0;i<N;i++){//回写回数组a
				a[i]=aux[i];
			}
		}
	}
	
	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		LSD.sort(a, 5);
		for(String each:a)
			System.out.println(each);
	}
}


public class Insertion {
    /*插入排序*/
	public static void sort(Double[] s){
		int N=s.length;
		/*从第二个数开始，将数组每个数插到对应次序的位置*/
		for(int i=1;i<N;i++){//
			for(int j=i;j>0;j--){
				if(Example.less(s[j],s[j-1])) Example.exch(s, j, j-1);//若s[j]更小，将其插到更前的位置上
				else break;//注意若s[j]>=s[j-1]，就跳出循环，因为前面的元素已经比较过，不会出现比s[j]大的了
			}	
		}
	}
	
	public static void main(String[] args){
		Double[] s={7.0,6.3,4.5,2.1};
		Insertion.sort(s);
		Example.show(s);
	}
}

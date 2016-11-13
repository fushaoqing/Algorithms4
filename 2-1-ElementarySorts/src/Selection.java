
public class Selection {
	/*选择排序*/
	public static void sort(Double[] s){
		int N=s.length;
		for(int i=0;i<N;i++){//从第i个数开始，与i+1至N-1进行比较
			int min=i;
			for(int j=i+1;j<N;j++){
				if(Example.less(s[j],s[min])) min=j;//若有更小的数，交换索引
				
			}
			Example.exch(s, i, min);//0到N-1的元素都要进行交换，即使最小的元素是自己也要和自己交换
		}
	}
	
	public static void main(String[] args){
		Double[] s={1.0,6.3,4.5,2.1};
		Selection.sort(s);
		Example.show(s);
	}
}

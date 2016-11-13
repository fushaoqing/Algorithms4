
public class Bubble {
	/*冒泡排序*/
	public static void sort(Double[] s){
		int N=s.length;
		/*将最大的数向右边冒*/
		for(int i=N-1;i>0;i--){//从最后一个数开始
			for(int j=0;j<i;j++){
				if(Example.less(s[j+1],s[j])) Example.exch(s, j+1, j);//若有更大的数，向右替换
			}	
		}
	}
	
	public static void main(String[] args){
		Double[] s={1.0,2.3,4.5,2.1};
		Bubble.sort(s);
		Example.show(s);
	}
}

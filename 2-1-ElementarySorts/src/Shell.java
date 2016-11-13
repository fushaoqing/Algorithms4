
public class Shell {
	/*希尔排序*/
	public static void sort(Double[] s){
		int N=s.length;
		int h=1;
		while(h<N/3) h=h*3+1;//步长取值
		/*对每个子数组交错进行插入排序*/
		while(h>=1){//最外层循环控制步长h,逐步缩短步长
			for(int i=h;i<N;i++){//第二层循环控制每个子数组用于插入排序的开始位置
				for(int j=i;j>=h;j-=h){
					if(Example.less(s[j],s[j-h])) Example.exch(s, j, j-h);//在子数组中以h为步长，进行插入排序
					else break;//注意若s[j]>=s[j-1]，就跳出循环，因为前面的元素已经比较过，不会出现比s[j]大的了
				}
			}
			h=h/3;
		}
	}
	
	public static void main(String[] args){
		Double[] s={3.0,6.3,4.5,2.1};
		Shell.sort(s);
		Example.show(s);
	}
}

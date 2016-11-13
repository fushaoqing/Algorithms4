package ex_2_1_12;
import edu.princeton.cs.algs4.*;
public class Shell {
	/*希尔排序测试，打印出比较次数和数组大小的比值*/
	public static void sort(Double[] s){
		int N=s.length;
		int h=1;
		int compNum=0;
		while(h<N/3) h=h*3+1;//步长取值
		/*对每个子数组交错进行插入排序*/
		while(h>=1){
			//最外层循环控制步长h,逐步缩短步长
			for(int i=h;i<N;i++){//第二层循环控制每个子数组用于插入排序的开始位置
				for(int j=i;j>=h;j-=h){
					compNum++;
					if(Example.less(s[j],s[j-h])) {
						Example.exch(s, j, j-h);//在子数组中以h为步长，进行插入排序
					}
					else {
						break;//注意若s[j]>=s[j-1]，就跳出循环，因为前面的元素已经比较过，不会出现比s[j]大的
				}
			}
		}
		double ratio=compNum/N;
		System.out.println(ratio);
		compNum=0;
		h=h/3;
	}
}
	public static void main(String[] args){
		Double[] test={1.0};
		int m=10;
		
		for(int n=0;n<1000;n++){
			test=new Double[m];
			for(int i=0;i<test.length;i++){
				test[i]=StdRandom.uniform();
			}
			Shell.sort(test);
			m=m*10;
		}
	}
}

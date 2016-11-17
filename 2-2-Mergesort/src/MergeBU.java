
public class MergeBU {
	/*自底向上归并排序*/
	public static void merge(double[] a,double[] aux,int lo,int boundary,int hi){//归并每个小数组
		if(a[boundary]<=a[boundary+1]) return;//两边均为有序数组，若a[mid]<=a[mid+1]，则不用归并
		int i=lo;
		int j= boundary+1;
		for(int n=lo;n<=hi;n++){//注意merge方法中的两个循环都是起始位置lo，终点位置hi，不是0和length-1
			aux[n]=a[n];
		}
		for(int k=lo;k<=hi;k++){
			if(i> boundary) 				a[k]=aux[j++];//左边比较完了直接将右边赋值
			else if(j>hi)   		a[k]=aux[i++];//右边比较完了直接将左边赋值
			else if(aux[j]<aux[i])  a[k]=aux[j++];//右边小于左边，将左边赋值过去
			else					a[k]=aux[i++];
		}
	}
	
	public static void sort(double[] a){
		int N=a.length;
		double[] aux=new double[N];
		for(int sz=1;sz<N;sz=sz+sz){//第一层循环控制子数组半长sz
			for(int lo=0;lo<N-sz;lo+=sz+sz){//第二层循环控制子数组起始位置lo
				//注意这里的merge方法中不再是输入mid,而是两个子数组的分割界boundary，因为长度为偶数的数组不再是等分，如长度为3或5的数组
				merge(a,aux,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));//奇数和偶数长度的数组终点 位置不一样，取最靠右边的位置
			}
		}
	}
	
	public static void show(double[] a){
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println("");//打印数组所有元素
	}
	
	public static void main(String[] args) {
		double[] s={1.0,6.3,4.5,2.1,3.5,2.2};
		MergeBU.sort(s);
		MergeBU.show(s);
	}
}

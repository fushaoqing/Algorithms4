
public class Merge {
	/*自顶向下归并排序*/
	public static void merge(double[] a,double[] aux,int lo,int mid,int hi){//归并每个大数组的两边
		if(a[mid]<=a[mid+1]) return;//两边均为有序数组，若a[mid]<=a[mid+1]，则不用归并
		int i=lo;
		int j=mid+1;
		for(int n=lo;n<=hi;n++){//注意merge方法中的两个循环都是起始位置lo，终点位置hi，不是0和length-1
			aux[n]=a[n];
		}
		for(int k=lo;k<=hi;k++){
			if(i>mid) 				a[k]=aux[j++];//左边比较完了直接将右边赋值
			else if(j>hi)   		a[k]=aux[i++];//右边比较完了直接将左边赋值
			else if(aux[j]<aux[i])  a[k]=aux[j++];//右边小于左边，将左边赋值过去
			else					a[k]=aux[i++];
		}
	}
	
	public static void sort(double[] a){
		double[] aux=new double[a.length];//一次性分配好复制数组的内存，依次通过sort传入merge
		sort(a,aux,0,a.length-1);//调用递归版的sort方法，初始lo=0,hi=length-1
	}
	
	public static void sort(double[] a,double[] aux,int lo,int hi){
		if(lo>=hi) return;
		int mid=lo+(hi-lo)/2;//通过递归不断将数组分成两部分
		sort(a,aux,lo,mid);
		sort(a,aux,mid+1,hi);
		merge(a,aux,lo,mid,hi);
	}
	
	public static void show(double[] a){
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println("");//打印数组所有元素
	}
	
	public static void main(String[] args) {
		double[] s={1.0,6.3,4.5,2.1,3.5};
		Merge.sort(s);
		Merge.show(s);
	}
}


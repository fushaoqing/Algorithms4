import edu.princeton.cs.algs4.StdRandom;

public class Median {
	public static int partition(int[] a,int lo,int hi){
		int l=lo;
		int r=hi+1;
		int v=a[lo];
		while(true){
			while(a[++l]<v) if(l==hi) break;
			while(a[--r]>v) continue;
			if(r<=l) break;
			int temp=a[l];
			a[l]=a[r];
			a[r]=temp;
		}
		int temp=a[lo];
		a[lo]=a[r];
		a[r]=temp;
		return r;
	}
	
	public static int select(int[] a,int k){//返回数组中第k大的数,k从0开始
		StdRandom.shuffle(a);
		int lo=0;
		int hi=a.length-1;
		while(hi>lo){
			int j=partition(a,lo,hi);
			if(j==k) return a[k];
			//确保第k个数即为切分点，此时a[k]前面的数均小于a[k]，a[k]后面的数均大于a[k]
			else if(k<j) hi=j-1;
			else if(k>j) lo=j+1;
		}
		return a[k];
	}
	public static void main(String[] args) {
		int[] a={1,3,9,5,8};
		System.out.println(select(a,1));
		for(int n=0;n<a.length;n++)
			System.out.println(a[n]);
		

	}

}

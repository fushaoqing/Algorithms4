import java.lang.*;
import edu.princeton.cs.algs4.*;
/*基于堆的优先队列*/
public class MaxPQ<Item extends Comparable<Item>> {//继承了Comparable接口的任意类
	private Item[] pq;
	private int N;
	
	public MaxPQ(int maxN){
		pq=(Item[])new Comparable[maxN+1];//创建用于储存二叉堆的数组，索引为0的第一个数组元素不用
	}
	
	public int size(){
		return N;
	}
	
	public boolean isEmpty(){
		return N==0;
	}
	
	public boolean less(int i,int j){//比较二叉堆上的两个元素
		return pq[i].compareTo(pq[j])<0;
	}
	
	public void exch(int i,int j){//交换二叉堆上的两个元素
		Item temp=pq[i];
		pq[i]=pq[j];
		pq[j]=temp;
	}
	public void swim(int k){//将大的结点向上比较，上浮
		while(k/2>=1&&less(k/2,k)){//上层的结点小于小层的结点，即堆无序,则交换两结点
			exch(k/2,k);
			k=k/2;//继续向上比较
		}
	}
	
	public void sink(int k){//将大的结点向下比较，下沉
		while(2*k<=N){
			int j=2*k;
			//将上层结点与下层较大的那个结点比较
			if(j<N&&less(j,j+1)) j++;//判断j<N是为避免，此时j已经是堆上的最后一个元素
			if(!less(k,j)) break;
			exch(k,j);
			k=j;
		}
	}
	
	public void insert(Item v){
		pq[++N]=v;
		swim(N);
	}
	
	public Item delMax(){
		Item max=pq[1];//最上面的索引为1的元素为最大值
		exch(1,N);//将最大元素和最底层元素交换
		pq[N--]=null;//删除最底层元素交换
		sink(1);//将交换后的底层元素沉到合适位置
		return max;
	}
	
	public static void main(String[] args) {
		int M=5;
		MaxPQ<Double> pq=new MaxPQ<Double>(10);
		Double[] test={2.7,3.4,5.6,1.2,3.1,7.8,8.8,2.9};
		for(Double each:test){
			pq.insert(each);
			if(pq.size()>M) pq.delMax();
		}
		Stack<Double> s=new Stack<Double>();
		while(!pq.isEmpty()) s.push(pq.delMax());//将pq中的元素按从大到小输入到stack中
		for(Double each:s) System.out.println(each);//将stack中的元素从小到大输出
	}

}

import java.util.Comparator;
import java.util.Iterator;
import edu.princeton.cs.algs4.*;
public class MinPQ<Item> implements Iterable<Item> {//实现带有泛型的Iterable迭代接口
	 Item[] pq;
	private int N;
	private Comparator<Item> comparator;
	
	public MinPQ(int maxN){
		pq=(Item[]) new Object[maxN+1];
	}
	
	public int size(){
		return N;
	}
	
	public boolean isEmpty(){
		return N==0;
	}
	
	private boolean greater(int i,int j ){
		/*为了比较pq[i]和pq[j]，需要将其装换为Comparable<Item>类型*/
		return ((Comparable<Item>) pq[i]).compareTo(pq[j])>0;
	}
	
	public void exch(int i,int j){//交换二叉堆上的两个元素
		Item temp=pq[i];
		pq[i]=pq[j];
		pq[j]=temp;
	}
	
	public void swim(int k){
		while(k/2>=1&&greater(k/2,k)){
			exch(k/2,k);
			k=k/2;//继续向上比较
		}
	}
	
	public void sink(int k){//将大的结点向下比较，下沉
		while(2*k<=N){
			int j=2*k;
			/*和下一层较小的元素交换*/
			if(j<N&&greater(j,j+1)) j++;//判断j<N是为避免，此时j已经是堆上的最后一个元素
			if(!greater(k,j)) break;
			exch(k,j);
			k=j;
		}
	}
	
	public Item min(){
		return pq[1];
	}
	
	public void insert(Item v){
		pq[++N]=v;
		swim(N);
	}
	
	public Item delMin(){
		Item min=pq[1];//最上面的索引为1的元素为最小值
		exch(1,N);//将最大元素和最底层元素交换
		pq[N--]=null;//删除最底层元素交换
		sink(1);//将交换后的底层元素沉到合适位置
		return min;
	}
	
	public Iterator<Item> iterator(){return new HeapIterator();}
	public class HeapIterator implements Iterator<Item>{//内部类从小到大迭代
		private MinPQ<Item> copy;
		public HeapIterator(){
			copy=new MinPQ<Item>(N);
			for(int i=1;i<=N;i++){
				copy.insert(pq[i]);
			}
		}
		public boolean hasNext(){return !copy.isEmpty();}
		public Item next(){return copy.delMin();}//因为迭代是用delMin()方法，为了不改变原数组pq,故要copy一份
	}
	
	public static void main(String[] args) {
        MinPQ<Double> p = new MinPQ<Double>(3);
        Double[] test={2.4,1.2,3.4};
        for(Double each:test) p.insert(each);
        
        for(Double each:p) StdOut.println(each);
        StdOut.println( p.size() );
    }
}

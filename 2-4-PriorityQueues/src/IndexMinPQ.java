
public class IndexMinPQ<Key extends Comparable<Key>> {

	private int n;
	private int[] pq;
	private int[] qp;
	private Key[] keys;
	
	public IndexMinPQ(int maxN){
		keys=(Key[])new Comparable[maxN+1];//不能直接创建子类的数组，必须先创建父类在转换
		pq=new int[maxN+1];
		qp=new int[maxN+1];
		for(int i=0;i<maxN+1;i++){
			qp[i]=-1;
		}
	}
	
	public boolean isEmpty() {
        return n == 0;
    }
	
	public boolean contain(int i){
		return qp[i]!=-1;
	}
	
	
	public void insert(int i,Key key){
		if(contain(i)) throw new IllegalArgumentException("index is already in the priority queue");
		n++;
		pq[n]=i;
		qp[i]=n;
		keys[i]=key;
		swim(i);
	}
	
	public void exch(int i,int j){
		int temp=pq[i];
		pq[i]=pq[j];
		pq[j]=temp;
		/*pq数组中索引i,j已经交换位置，故qp数组也要换过来*/
		qp[pq[i]]=i;
		qp[pq[j]]=j;
	}
	
	public boolean greater(int i,int j){
		return keys[pq[i]].compareTo(keys[pq[j]])>0;
	}
	
	public void swim(int k){
		while(k/2>=1&&greater(k/2,k)){
			exch(k,k/2);
			k/=2;
		}	
	}
	
	public void sink(int k){
		while(2*k<=n){
			int j=2*k;
			if(j<n&&greater(j,j+1)) j++;
			if(!greater(k,j)) break;
			exch(k,j);
			k=j;
		}
	}
	
	public int delMin(){
		int min=pq[1];
		keys[min]=null;
		exch(1,n--);
		assert min == pq[n+1];
		sink(1);
		qp[min]=-1;
		pq[n+1]=-1;
		return min;	
	}
	
	public void changeKey(int i,Key key){
		keys[i]=key;
		swim(i);
		sink(i);
	}
	
	public void change(int i,Key key){
		 changeKey(i, key);
	}
	
	public void delete(int i){
		int index=qp[i];
		exch(index,n--);
		swim(index);
		sink(index);
		keys[index]=null;
		qp[i] = -1;
	}
	
	public Key min(){
		return keys[pq[1]];
	}
	
	public static void main(String[] args){
		String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };
		IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }
        System.out.println(pq.min());
	}
}

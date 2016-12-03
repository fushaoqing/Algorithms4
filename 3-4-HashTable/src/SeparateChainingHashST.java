import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class SeparateChainingHashST<Key extends Comparable<Key>,Value> {

	private int N;//所有键值对的个数
	private int M;//存储数组的大小
	private SequentSearchST<Key,Value>[] st;
	
	public SeparateChainingHashST(int M){
		this.M =M;
		st=(SequentSearchST<Key,Value>[])new SequentSearchST[M];
		for(int i=0;i<M;i++){
			st[i]=new SequentSearchST();
		}
	}
	
	private int hash(Key key){
		return (key.hashCode()&0x7fffffff)%M;//将对象的hashCode()转换为数组索引
	}
	
	public Value get(Key key){
		return st[hash(key)].get(key);//通过索引定位到数组的位置，再通过字符链表的get方法找到Key
	}
	
	public void put(Key key,Value val){//通过索引定位到数组的位置，再通过字符链表的put方法插入key和val
		st[hash(key)].put(key, val);
		N++;
	}
	
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(int i=0;i<M;i++){
			if(st[i]!=null)
				for(Key key:st[i].keys())
					queue.enqueue(key);
		}
		return queue;
	}
	
	public  static void main(String[] args){
		SeparateChainingHashST<String,Integer> sct=new SeparateChainingHashST<String,Integer>(997);
		
		sct.put("abc", 1);
		sct.put("ad", 3);
		sct.put("qq", 4);
		//sct.delete("ad");
		//StdOut.println(sct.contain("qq"));
		StdOut.println(sct.N);
		for(String word:sct.keys()){
			StdOut.println(word+" "+sct.get(word));
		}
	}
	
}

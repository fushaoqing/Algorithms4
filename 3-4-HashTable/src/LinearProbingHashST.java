import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

//线性哈希表
public class LinearProbingHashST<Key,Value> {

	private int N;
	private int M;
	//两个平行数组分别存储键和值
	private Key[] keys;
	private Value[] vals;
	public LinearProbingHashST(int M){
		this.M=M;
		keys=(Key[]) new Object[M];
		vals=(Value[]) new Object[M];
	}
	
	private int hash(Key key){
		return (key.hashCode()&0x7fffffff)%M;//将对象的hashCode()转换为数组索引
	}
	
	public void resize(int cap){
		LinearProbingHashST<Key,Value> t;
		t=new LinearProbingHashST<Key,Value>(cap);
		for(int i=0;i<M;i++){
			if(keys[i]!=null){
				t.put(keys[i], vals[i]);//将键和值替换到扩容的数组的对应的位置
			}
		}
		//用扩容的数组去替换原有的数组
		keys=t.keys;
		vals=t.vals;
		M=t.M;
	}
	public void put(Key key,Value val){
		if(N>M/2)
			resize(2*M);
		int i;
		for(i=hash(key);keys[i]!=null;i=(i+1)%M){
			if(key.equals(keys[i]))
				vals[i]=val;
		}
		keys[i]=key;
		vals[i]=val;
		N++;
	}
	
	public Value get(Key key){
		for(int i=hash(key);keys[i]!=null;i=(i+1)%M){
			if(key.equals(keys[i]))
				return vals[i];
		}
		return null;
	}
	
	public boolean contain(Key key){
		return get(key)!=null;
	}
	
	public void delete(Key key){
		if(!contain(key)) return;
		int i=hash(key);
		while(!key.equals(keys[i]))//当hash值相同，键值不同时，逐个向后查找
			i=(i+1)%M;
		keys[i]=null;
		vals[i]=null;
		i=(i+1)%M;
			
		while(keys[i]!=null){
			Key keyReDo=keys[i];
			Value valReDo=vals[i];
			keys[i]=null;//先删除后面的键值
			vals[i]=null;
			N--;
			
			put(keyReDo,valReDo);
			i=(i+1)%M;
		}
		N--;
		if(N>0&&N<M/8) resize(M/2);//当键数小于M/8时，动态缩小数组长度
	}
	
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(int i=0;i<M;i++){
			if(keys[i]!=null) 
				queue.enqueue(keys[i]);
		}
		return queue;
	}
	
	public static void main(String[] args){
		LinearProbingHashST<String,Integer> lst=new LinearProbingHashST<String,Integer>(1);
		lst.put("abc", 1);
		lst.put("ad", 3);
		lst.put("qq", 4);
		lst.delete("ad");
		System.out.println(lst.contain("qq"));
		System.out.println(lst.N);
		for(String word:lst.keys()){
			StdOut.println(word+" "+lst.get(word));
		}
	}
}

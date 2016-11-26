import java.util.Scanner;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
public class BinarySearchST<Key extends Comparable<Key>,Value> {

	private static final String Scanner = null;
	private Key[] keys;
	private Value[] vals;
	private int N;
	public BinarySearchST(int cap){
		keys=(Key[])(new Comparable[cap]);
		vals=(Value[])(new Comparable[cap]);
	}
	
	public int size(){
		return N;
	}
	
	public boolean isEmpty(){
		return N==0;
	}
	
	public int rank(Key key){//不管key是否存在数组中，都会返回一个整数
		int lo=0;
		int hi=N-1;
		int N=keys.length;
		while(lo<=hi){
			int mid=lo+(hi-lo)/2;
			int cmp=keys[mid].compareTo(key);
			if(cmp<0) lo=mid+1;
			else if(cmp>0) hi=mid-1;
			else return mid;
		}
		return lo;//若key不存在数组中，返回小于Key的数的个数
	}
	
	public Value get(Key key){
		if(isEmpty()) return null;
		int i=rank(key);//不管key是否存在数组中，rank()都会返回一个整数，故需要再次判断key
		if(i<N&&key.compareTo(keys[i])==0) return vals[i];
		return null;
	}
	
	public void put(Key key,Value val){
		int i=rank(key);
		if(i<N&&key.compareTo(keys[i])==0){
			vals[i]=val;
		}else{
			for(int n=N;n>i;n--){//数组i以后的元素都向右移一位
				keys[n]=keys[n-1];
				vals[n]=vals[n-1];
			}
			keys[i]=key;//插入key和val
			vals[i]=val;
			N++;
		}
	}
	
	public Key max(){
		return keys[N-1];
	}
	
	public Key min(){
		return keys[0];
	}
	
	public Key select(int k){//返回第K小的数
		return keys[k];
	}
	
	public boolean contain(Key key){
		int i=rank(key);
		if(i<N&&key.compareTo(keys[i])==0) return true;
		return false;
	}
	public Key ceiling(Key key){
		int i=rank(key);
		return keys[i];//返回大于等于key的最小key对应的val，即上界
	}
	
	public Key floor(Key key){
		int i=rank(key);
		if(i<N&&key.compareTo(keys[i])==0) return key;
		return keys[i-1];
	}
	
	public void delete(Key key){
		int i=rank(key);
		if(i<N&&key.compareTo(keys[i])==0){
			for(int n=N-1;n>i;n--){
				keys[n-1]=keys[n];//将数组左移一格
				vals[n-1]=vals[n];
			}
			N--;
			return;
		}
		System.out.println("It's not existed!");
	}
	
	public Iterable<Key> keys(Key lo,Key hi){
		Queue<Key> queue=new Queue<Key>();
		for(int i=rank(lo);i<rank(hi);i++){
			queue.enqueue(keys[i]);
		}
		if(contain(hi)) queue.enqueue(keys[rank(hi)]);
		return queue;
	}
	
	public static void main(String[] args){
		BinarySearchST<String,Integer> st=new BinarySearchST<String,Integer>(5);
		st.put("abc", 1);
		st.put("ad", 3);
		st.put("qq", 4);
		//st.delete("ad");
		StdOut.println(st.contain("qq"));
		StdOut.println(st.size());
		StdOut.println(st.max());
		StdOut.println(st.floor("ac"));
		for(String word:st.keys("abc","qq")){
			StdOut.println(word+" "+st.get(word));
		}
	}
}

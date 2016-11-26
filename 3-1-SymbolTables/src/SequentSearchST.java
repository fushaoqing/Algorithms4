import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class SequentSearchST<Key extends Comparable<Key>,Value> {
/*用链表构造符号表*/
	private Node first;
	private int n;
	private class Node{
		Key key;
		Value value;
		Node next;
		public Node(Key key,Value value,Node next){
			this.key=key;
			this.value=value;
			this.next=next;
		}
	}
	
	public Value get(Key key){
		for(Node x=first;x!=null;x=x.next){
			if(x.key.equals(key)) return x.value;
		}
		return null;
	}
	
	public void put(Key key,Value value){
		for(Node x=first;x!=null;x=x.next){
			if(x.key.equals(key)) {x.value=value;return;}
		}
		first=new Node(key,value,first);
		n++;
		//相当于Node node=new Node(key,value,first);first=node;
	}
	
	public int size(){
		return n;
	}
	
	public void delete(Key key){
		Node f=first;
		while(f.next.next!=null){
			f=f.next;//到达最后一个结点
		}
		if(f.next.key.equals(key)) {f.next=null;n--;return;}
	
		for(Node x=first;x.next!=null;x=x.next){//最多遍历至倒数第二个Node上
			if(x.next.key.equals(key)) {x.next=x.next.next;n--;return;}
		}
		
		if(first.key.equals(key))  {
			Node old=first;
			first=first.next;
			old.next=null;
			old=null;
			
			n--;
		}
	}
	
	public Iterable<Key> keys(){
		ArrayList<Key> klist=new ArrayList<Key>();
		int i=0;
		for(Node x=first;x!=null;x=x.next){
			klist.add(x.key);
			i++;
		}
		Collections.sort(klist);
		return klist;
	}
	
	public boolean contain(Key key){
		for(Node x=first;x!=null;x=x.next){
			if(x.key.equals(key)) return true;
		}
		return false;
	}
	
	public  static void main(String[] args){
		SequentSearchST<String,Integer> st=new SequentSearchST<String,Integer>();
		/*int minLen=Integer.parseInt(args[0]);
		while(!StdIn.isEmpty()){
			String word=StdIn.readString();
			if(word.length()<minLen) continue;
			if(!st.contain(word)) st.put(word, 1);//未出现过的字符，将其数目设为1
			else  				  st.put(word, st.get(word)+1);//出现过的字符，原数目加1
		}
		
		String max="";
		st.put(max, 0);
		for(String word:st.keys()){
			if(st.get(word)>st.get(max)) max=word;
		}
		StdOut.println(max+" "+st.get(max));*/
		st.put("abc", 1);
		st.put("ad", 3);
		st.put("qq", 4);
		st.delete("ad");
		StdOut.println(st.contain("qq"));
		StdOut.println(st.size());
		for(String word:st.keys()){
			StdOut.println(word+" "+st.get(word));
		}
	}
}


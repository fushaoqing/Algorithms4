import edu.princeton.cs.algs4.Queue;

public class TrieST<Value> {

	private static int R=256;
	private static Node root;
	private static class Node{
		Object val;
		Node[] next=new Node[R];
	}
	
	public Value get(String key){
		Node x=get(root,key,0);
		if(x==null) return null;//若果查找字符串值为空或未找到完整字符串就到了空链接，返回null
		return (Value)x.val;
	}
	private Node get(Node x,String key,int d){
		if(x==null) return null;
		if(d==key.length()) return x;
		char c=key.charAt(d);
		return get(x.next[d],key,d+1);
	}
	
	public void put(String key,Value val){
		root=put(root,key,val,0);
	}
	private Node put(Node x,String key,Value val,int d){
		if(x==null) x=new Node();
		else if(d==key.length()){
			x.val=val;
			return x;
		}
		char c=key.charAt(d);//返回Key中第d个字符在字符表中的位置
		x.next[c]=put(x.next[c],key,val,d+1);
		return x;//把构造好的节点返回接到原节点上
	}
	
	public Iterable<String> keys(){
		return keysWithPrefix("");//以空字符作前缀，即所有树中的字符
	}
	public Iterable<String> keysWithPrefix(String pre){//返回指定前缀的字符串
		Queue<String> q=new Queue<String>();
		return collect(get(root,pre,0),pre,q);//所有含有公共前缀的字符串都在一颗子树上，固先要找到这课子树
	}
	private Iterable<String> collect(Node x,String pre,Queue<String> q){
		if(x==null) return null;
		if(x.val!=null) q.enqueue(pre);;
		for(char c=0;c<R;c++)
			collect(x.next[c],pre+c,q);//先找到含有pre的子树的根节点,在从根节点开始收集字符串
		return q;
	}
	
	public Iterable<String> keysThatMatch(String pat){
		Queue<String> q=new Queue<String>();
		collect(root,"",pat,q);
		return q;
	}
	private void collect(Node x,String pre,String pat,Queue<String> q){
		if(x==null) return;
		int d=pre.length();
		if(d==pat.length()&&x.val!=null) q.enqueue(pre);
		if(d==pat.length()) return;
		
		char next=pat.charAt(d);
		for(char c=0;c<R;c++)
			if(next=='.'||next==c)//两种情况，若出现'.',则代表任意字符，若不为'.'，则按对应字符查找
				collect(x.next[c],pre+c,q);
	}
	
	public String longestPrefixOf(String s){//在字符查找数中找出给定字符串的最长前缀
		int length=search(root,s,0,0);
		return s.substring(length);
	}
	private int search(Node x,String s,int d,int length){
		if(x==null) return length;
		if(x.val!=null) length=d;
		if(d==s.length()) return length;
		char next=s.charAt(d);
		return search(x.next[next],s,d+1,length);
	}
	
	public static void main(String[] args) {
		

	}
}

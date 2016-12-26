import edu.princeton.cs.algs4.In;
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
		return (Value)x.val;//不支持泛型数组，故存为Object，返回时再转换为Value
	}
	private Node get(Node x,String key,int d){
		if(x==null) return null;
		if(d==key.length()) return x;
		char c=key.charAt(d);
		return get(x.next[c],key,d+1);
	}
	
	public void put(String key,Value val){
		root=put(root,key,val,0);
	}
	private Node put(Node x,String key,Value val,int d){
		if(x==null) x=new Node();
		if(d==key.length()){
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
		collect(get(root,pre,0),pre,q);//所有含有公共前缀的字符串都在一颗子树上，固先要找到这棵子树
		return q;
	}
	private void collect(Node x,String pre,Queue<String> q){
		if(x==null) return ;
		if(x.val!=null) q.enqueue(pre);;
		for(char c=0;c<R;c++)
			collect(x.next[c],pre+c,q);//先找到含有pre的子树的根节点,在从根节点开始收集字符串
	}
	
	public Iterable<String> keysThatMatch(String pat){
		Queue<String> q=new Queue<String>();
		collect(root,"",pat,q);
		return q;
	}
	private void collect(Node x,String pre,String pat,Queue<String> q){
		int d=pre.length();
		if(x==null) return;
		if(d==pat.length()&&x.val!=null) q.enqueue(pre);
		if(d==pat.length()) return;
		char next=pat.charAt(d);
		for(char c=0;c<R;c++)
			if(next=='.'||next==c)//两种情况，若出现'.',则代表任意字符，若不为'.'，则按对应字符查找
				collect(x.next[c],pre+c,pat,q);
	}
	
	public String longestPrefixOf(String s){//在字符查找数中找出给定字符串的最长前缀
		int length=search(root,s,0,0);
		return s.substring(0,length);
	}
	private int search(Node x,String s,int d,int length){
		if(x==null) return length;
		if(x.val!=null) length=d;
		if(d==s.length()) return length;
		char next=s.charAt(d);
		return search(x.next[next],s,d+1,length);
	}
	
	private void delete(String key){
		root=delete(root,key,0);
	}
	private Node delete(Node x,String key,int d){
		if(x==null) return null;
		if(d==key.length()) x.val=null;//达到字符串的末端，将对应字符串表示的值赋为null
		else{
			char next=key.charAt(d);
			delete(x.next[next],key,d+1);
		}
		//回溯删除节点时有三种情况
		if(x.val!=null) return x;//值为非空，链接为空数组
		else 
			for(char c=0;c<R;c++)
				if(x.next[c]!=null)//值为空，链接为非空数组
					return x;
		return null;//值为空，链接为空数组，删除该节点，x.next[c]=null
	}
	public static void main(String[] args) {
		In in=new In(args[0]);
		TrieST<Integer> tree=new TrieST<Integer>();
		int i=1;
		while(i<9){
			String s=in.readString();
			tree.put(s, i++);
		}	
		//System.out.println(tree.get("sells"));
		/*for(String key:tree.keys())
			System.out.println(key);
		tree.delete("sea");
		for(String key:tree.keysWithPrefix("se"))
			System.out.println(key);*/
		//System.out.println(tree.longestPrefixOf("shellss"));
		System.out.println(tree.keysThatMatch("s.."));
	}
}

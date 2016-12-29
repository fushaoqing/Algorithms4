import edu.princeton.cs.algs4.In;
/*三向单词查找数*/
public class TST<Value> {
	private Node root;
	private class Node{
		Object val;
		char c;
		Node left;
		Node right;
		Node mid;
	}
	public Value get(String key){
		Node x=get(key,root,0);
		if(x==null){
			//System.out.println(0);
			return null;
		}
		return (Value)x.val;
	}
	private Node get(String key,Node x,int d){//get方法每次递归return Node
		if(x==null) return null;
		char c=key.charAt(d);
		if(c>x.c) return get(key,x.right,d);
		else if(c<x.c) return get(key,x.left,d);
		else if(d<key.length()-1)
			return get(key,x.mid,d+1);//只有等于x.c，才继续向下查找
		return x;
	}
	
	public void put(String key,Value val){
		root=put(key,val,root,0);
	}
	private Node put(String key,Value val,Node x,int d){//put方法每次递归赋值Node
		char c=key.charAt(d);
		if(x==null){x=new Node();x.c=c;}
		if(c>x.c) x.right=put(key,val,x.right,d);
		else if(c<x.c) x.left=put(key,val,x.left,d);
		else if(d<key.length()-1)
			x.mid=put(key,val,x.mid,d+1);
		else x.val=val;
		return x;
	}
	public static void main(String[] args) {
		In in=new In(args[0]);
		TST<Integer> tree=new TST<Integer>();
		int i=1;
		while(i<9){
			String s=in.readString();
			tree.put(s, i++);
		}	
		
		System.out.println(tree.get("shells"));
	}
}

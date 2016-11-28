
public class BST<Key extends Comparable <Key>,Value>{
	private Node root;
	private class Node{
		private Key key;
		private Value val;
		private Node left;
		private Node right;
		private int N;//以该节点为根的子数组的节点总数
		
		public Node(Key key,Value val,int N){
			this.key=key;
			this.val=val;
			this.N=N;
		}
	}
	
	public int size(){
		return size(root);
	}
	public int size(Node x){
		if(x==null) return 0;
		return x.N;
	}
	
	public Value get(Key key){//查找方法，从根节点开始往下查找
		return get(root,key);
	}
	public Value get(Node x,Key key){
		if(x==null) return null;
		int cmp=key.compareTo(x.key);
		if(cmp>0) return get(x.right,key);//大了，就往右边子树找
		else if(cmp<0) return get(x.left,key);//小了，就往左边子树找
		else return x.val;
	}
	
	public void put(Key key,Value val){//一个BST树是一个大的节点root，每个子树是链接在root上的子节点
		root=put(root,key,val);//返回一个更新了的root节点
	}
	public Node put(Node x,Key key,Value val){
		if(x==null) return new Node(key,val,1);
		int cmp=key.compareTo(x.key);
		if(cmp>0) return put(x.right,key,val);
		else if(cmp<0) return put(x.left,key,val);
		else x.val=val;
		//调用了几次put方法，就有几次回溯
		x.N=size(x.left)+size(x.right)+1;//通过递归的回溯，向上更新之前的节点的N
		return x;
	}
	
	public Node min(){
		return min(root);//从根节点开始一直往左子数组里找
	}
	public Node min(Node x){
		if(x==null) return x;
		return min(x.left);//若左边子数组部位空，就一直在左边子数组里找最小key
	}
	
	public Node floor(Key key){
		return floor(root,key);
	}
	public Node floor(Node x,Key key){
		if(x==null) return null;//数学归纳法中当n=1的情况
		//假设已经知道floor(x.left.key)和floor(x.right,key)，通过判断来求floor(x,key)
		int cmp=key.compareTo(x.key);
		
		if(cmp==0) return x;
		else if(cmp<0) return floor(x.left.key);
		Node t=floor(x.right,key);
		if(t!=null) return t;
		else return x;
	}
	
	public Node select(int k){
		return select(root,k);
	}
	public Node select(Node x,int k){
		if(x==null) return null;
		int t=size(x.left);
		if(t>k) return select(x.left,k);
		else if(t<k) return select(x.right,k-t-1);//若左边子数组数量不够，则将根节点和右子数组也算进去查找
		else return x;
	}
	
	public int rank(Key key){
		return rank(root,key);
	}
	public int rank(Node x,Key key){
		if(x==null) return 0;
		int cmp=key.compareTo(x.key);
		if(cmp<0) return rank(x.left,key);
		else if(cmp>0) return rank(x.right,key)+size(x.left)+1;//假设一直key在右子树中的排名，则只要再加上1个根节点数和左子树个数即是在整个BST中的排名
		else return size(x.left);
	}
}

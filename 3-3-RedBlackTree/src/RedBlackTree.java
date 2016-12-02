import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class RedBlackTree<Key extends Comparable<Key>,Value>{
	private Node root;
	private static final boolean RED=true;
	private static final boolean BLACK=true;
	public class Node{
		Key key;
		Value val;
		Node left,right;
		int N;
		boolean color;
	
		public Node(Key key,Value val,int N,boolean color){
			this.key=key;
			this.val=val;
			this.N=N;
			this.color=color;
		}
	}
	
	private boolean isRed(Node h){
		if(h==null) return false;
		return h.color==RED;
	}
	
	public int size(){
		return size(root);
	}
	public int size(Node x){
		if(x==null) return 0;
		return x.N;
	}
	
	
	public Node rotateLeft(Node h){//将右边的红链接旋转到左边
		Node x=h.right;
		h.right=x.left;
		x.left=h;
		x.N=h.N;
		h.N=size(h.left)+size(h.right)+1;
		return x;
		
	}
	
	public Node rotateRight(Node h){//将左边的红链接旋转到右边
		Node x=h.left;
		h.left=x.right;
		x.right=h;
		x.N=h.N;
		h.N=size(h.left)+size(h.right)+1;
		return x;
	}
	
	public void flipColors(Node h){//当左右两边都为红链接时，将两边链接改为黑色，将根链接改为红色
		h.color=!h.color;
		h.right.color=!h.right.color;
		h.left.color=!h.left.color;
	}
	
	public Key min(){
		return min(root).key;//从根节点开始一直往左子数组里找
	}
	public Node min(Node x){
		if(x==null) return null;
		else if(x.left==null) return x;
		return min(x.left);//若左边子数组部位空，就一直在左边子数组里找最小key
	}
	
	public Key max(){
		return max(root).key;
	}
	public Node max(Node x){
		if(x==null) return null;
		else if(x.right==null) return x;
		return max(x.right);//若左边子数组部位空，就一直在左边子数组里找最小key
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
	
	public boolean contain(Key key){
		return get(key)!=null;
	}
	
	public void put(Key key,Value val){
		root=put(root,key,val);
		root.color=BLACK;//根节点颜色为黑色
	}
	public Node put(Node h,Key key,Value val){
		if(h==null) return new Node(key,val,1,RED);
		int cmp=key.compareTo(h.key);
		if(cmp>0) h.right=put(h.right,key,val);
		else if(cmp<0) h.left=put(h.left,key,val);
		else h.val=val;
		if(isRed(h.right)&&!isRed(h.left)) h=rotateLeft(h);//右边为红链接，左边为黑活空链接时，向左旋转
		if(isRed(h.left)&&isRed(h.left.left)) h=rotateRight(h);//两条红链接连在了一个节点上，向右旋转
		if(isRed(h.left)&&isRed(h.right)) flipColors(h);//左右两边都为红链接时，将两边链接改为黑色，将根链接改为红色
		h.N=size(h.left)+size(h.right)+1;
		return h;
	}
	
	public Node floor(Key key){
		return floor(root,key);
	}
	public Node floor(Node x,Key key){
		if(x==null) return null;//数学归纳法中当n=1的情况
		//假设已经知道floor(x.left.key)和floor(x.right,key)，通过判断来求floor(x,key)
		int cmp=key.compareTo(x.key);
		
		if(cmp==0) return x;
		else if(cmp<0) return floor(x.left,key);
		Node t=floor(x.right,key);
		if(t!=null) return t;
		else return x;
	}
	
	public Iterable<Key> keys(){
		return keys(min(),max());
	}
	public Iterable<Key> keys(Key lo,Key hi){
		Queue<Key> q=new Queue<Key>();
		keys(root,q,lo,hi);
		return q;
	}
	public void keys(Node x,Queue<Key> q,Key lo,Key hi){
		if(x==null)  return ;
		int cmplo=lo.compareTo(x.key);
		int cmphi=hi.compareTo(x.key);
		if(cmplo<0) keys(x.left,q,lo,hi);
		if(cmplo<=0&&cmphi>=0) q.enqueue(x.key);
		if(cmphi>0) keys(x.right,q,lo,hi);
	}
	
	public void moveRedLeft(Node h){
		flipColors(h);//将三个2-节点合成一个4-节点
		if(isRed(h.right.left)){//若右子节点为4-节点或3-节点，就借一个过来
			h.right=rotateRight(h.right);
			h=rotateLeft(h);
			flipColors(h);//将原先的4-节点还原
		}
	}
	
	 private Node balance(Node h) {
        // assert (h != null);
        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
	 
	 public boolean isEmpty() {
	        return root == null;//root为null,说明整个数树为空
	   }
	public void deleteMin(){
		if(!isRed(root.right)&&!isRed(root.left))
			root.color=RED;
		root=deleteMin(root);
		if (!isEmpty()) root.color = BLACK;
	}
	public Node deleteMin(Node h){
		if(h.left==null) return null;
		else if(!isRed(h.left)&&!isRed(h.left.left))
				moveRedLeft(h);
		h.left=deleteMin(h.left);
		return balance(h);
	}
	
	public static void main(String[] args) {
		RedBlackTree<String,Integer> rbt=new RedBlackTree<String,Integer>();
		rbt.put("abc", 1);
		rbt.put("ad", 3);
		rbt.put("qq", 4);
		//StdOut.println(rbt.isEmpty());
		rbt.deleteMin();
		StdOut.println(rbt.contain("qq"));
		StdOut.println(rbt.floor("ad").key);
		StdOut.println(rbt.size());
		for(String word:rbt.keys()){
			StdOut.println(word+" "+rbt.get(word));
		}
	}

}

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Huffman {

	private static int R=256;
	private static class Node implements Comparable<Node>{
		private char ch;//叶子节点中需要编码的字符
		private int fre;//节点中字符在字符串中出现的次数，即频率
		private final Node left,right;
		Node(char ch,int fre,Node left,Node right){
			this.ch=ch;
			this.fre=fre;
			this.left=left;
			this.right=right;
		}
		public boolean isLeaf(){//如果节点的两个节点是null，表是该节点到最底端了，即是叶子节点，不是内部节点
			return left==null&&right==null;
		}
	
		public int compareTo(Node that){//比较频率,频率的树深度小
			return this.fre-that.fre;
		}
	}
	
	public static Node buildTrie(int[] freq){
		MinPQ<Node> pq=new MinPQ<Node>();
		for(char c=0;c<256;c++){
			if(freq[c]!=0)
				pq.insert(new Node(c,freq[c],null,null));//添加内部节点
		}
		while(pq.size()>1){//不断把频率小的节点合并，使其树深度增大，而频率大的树深度小
			Node x=pq.delMin();
			Node y=pq.delMin();
			Node parent=new Node('\0',freq[x.ch]+freq[y.ch],x,y);
			pq.insert(parent);
		}
		return pq.delMin();//将所有节点合并成一个节点，再返回
	}
	
	private static String[] buildCode(Node node){
		String[] st=new String[R];//建立编码表
		buidCode(st,node,"");//遍历整个Trie树，到达叶子节点的路径是唯一的，而路径上经历的0和1构成了到达叶子节点中字符的编码
		return st;
	}
	private static void buidCode(String[] st,Node x,String code){
		if (x.isLeaf()){
			st[x.ch]=code;//字符对应得数组元素赋予编码
			return;
		}
		//从左到右，前序遍历
		buidCode(st,x.left,code+"0");
		buidCode(st,x.right,code+"1");
	}
	
	private static void writeTrie(Node x){
		if(x.isLeaf()){
			//先保存是否是叶子节点，在保存节点中的字符
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch);
			return;
		}
		BinaryStdOut.write(false);
		writeTrie(x.left);
		writeTrie(x.right);
	}
	
	public static void compress(){
		String s=BinaryStdIn.readString();
		char[] input=s.toCharArray();
		int[] freq=new int[R];
		for(int i=0;i<input.length;i++){
			freq[input[i]]++;//计算输入字符串中各个字符的频率
		}
		//把一条字符串映射到一棵Trie树和一个编码表st
		Node root=buildTrie(freq);
		String[] st=buildCode(root);
		
		/*把编码写出到文件中*/
		writeTrie(root);
		BinaryStdOut.write(input.length);
		for(int i=0;i<input.length;i++){
			String code=st[input[i]];//找到相应字符的编码
			for(int j=0;j<code.length();j++)
				if(code.charAt(j)=='1')
					BinaryStdOut.write(true);
				else 
					BinaryStdOut.write(false);
		}
	}
	
	private static Node readTrie(){
		if(BinaryStdIn.readBoolean())//如果是叶子节点，直接读取字符
			return new Node(BinaryStdIn.readChar(),0,null,null);
		return new Node('\0',0,readTrie(),readTrie());//如果是内部节点，继续往下遍历
	}
	
	public static void expand(){
		Node root=readTrie();//先读取Trie树
		int N=BinaryStdIn.readInt();
		for(int i=0;i<N;i++){
			Node x=root;//每次都从根节点开始，往下读取，直到叶子节点，
			while(!x.isLeaf())
				if(BinaryStdIn.readBoolean())
					x=x.right;
				else x=x.left;
			BinaryStdOut.write(x.ch);
		}
		BinaryStdOut.close();
	}
	public static void main(String[] args) {
		 
		
	}
}

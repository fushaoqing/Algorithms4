import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class WeightedQuickUnion {
    /*加权归并*/
	private int[] id;//数组id用于存放代表分量的标识符，id的索引代表触点号
	private int[] sz;//数组sz存储各个根节点所对应的大小
	private int cnt;

	public WeightedQuickUnion(int N){
		cnt=N;
		id=new int[N];
		sz=new int[N];
		for(int i=0;i<id.length;i++){//初始化数组，每个数组索引代表该触点，数组元素代表该触点到根触点的链接
			id[i]=i;
		}
		for(int i=0;i<id.length;i++){//初始化数组，每个数组索引代表根节点，最来说每个触点的根触点就是自身，树的长度为1
			sz[i]=1;
		}
	}
	
	public int find(int p){
		while(p!=id[p]) p=id[p];
		return p;//返回根触点的索引
	}
	
	public void union(int p,int q){
		int i=find(p);
		int j=find(q);
		if(i==j) return;//根触点相同表示在同一分量上，跳过
		if(sz[i]<sz[j]){id[i]=j;sz[j]+=sz[i];}//讲触点少的i分量链接到触点多的j分量上,触点j所在分量长度增加
		else{id[j]=i;sz[i]+=sz[j];}
		cnt--;//两个分量归并为一个分量，分量数减1；
	}
	
	public boolean connected(int p,int q){
		return find(p)==find(q);
	}
	
	
	public static void main(String[] args) {
		int N=StdIn.readInt();
		WeightedQuickUnion uf=new WeightedQuickUnion(N);
		while(!StdIn.isEmpty()){
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(uf.connected(p, q)) continue;
			uf.union(p, q);
			StdOut.println(p+"  "+q);//打印连接
		}

		StdOut.println(uf.cnt+" components");
	}
}

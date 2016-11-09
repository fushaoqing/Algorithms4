import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnion {

	/*快速归并*/
	private int[] id;//数组id用于存放代表分量的标识符，id的索引代表触点号
	private int cnt;

	public QuickUnion(int N){
		cnt=N;
		id=new int[N];
		for(int i=0;i<id.length;i++){//初始化数组，每个数组索引代表该触点，数组元素代表该触点到根触点的链接
			id[i]=i;
		}
	}
	
	public int find(int p){
		while(p!=id[p]) p=id[p];
		return p;//返回根触点的索引
	}
	
	public void union(int p,int q){
		int proot=find(p);
		int qroot=find(q);
		if(proot==qroot) return;//根触点相同表示在同一分量上，跳过
		id[proot]=qroot;//将触点p链接到q的触点
		cnt--;//两个分量归并为一个分量，分量数减1；
	}
	
	public boolean connected(int p,int q){
		return find(p)==find(q);
	}
	
	
	public static void main(String[] args) {
		int N=StdIn.readInt();
		QuickUnion uf=new QuickUnion(N);
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

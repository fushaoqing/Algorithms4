import edu.princeton.cs.algs4.*;
public class QuickFind {
	/*快速查找*/
	private int[] id;//数组id用于存放代表分量的标识符，id的索引代表触点号
	private int cnt;
	public QuickFind(int N){
		cnt=N;
		id=new int[N];
		for(int i=0;i<id.length;i++){//初始化数组，最开始,一个索引代表一个触点
			id[i]=i;
		}
	}
	
	public int find(int p){//查找只需访问数组一次
		return id[p];//返回触点p所在的分量
	}
	
	public void union(int p,int q){//归并需要遍历数组，
		int a=find(p);
		int b=find(q);
		if(a==b) return;
		for (int i=0;i<id.length;i++){
			if(id[i]==a) id[i]=b;//讲触点p所在分量归并到触点q所在分量上
		}
		cnt--;//两个分量归并为一个分量，分量数减1；
	}
	
	public boolean connected(int p,int q){
		return find(p)==find(q);
	}
	
	
	public static void main(String[] args) {
		int N=StdIn.readInt();
		QuickFind uf=new QuickFind(N);
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

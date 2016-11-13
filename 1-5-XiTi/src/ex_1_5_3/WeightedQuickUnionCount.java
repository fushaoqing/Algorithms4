package ex_1_5_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import ex_1_5_2.QuickUnionCount;

public class WeightedQuickUnionCount {

	private int[] id;//数组id用于存放代表分量的标识符，id的索引代表触点号
	private int[] sz;//数组sz存储各个根节点所对应的大小
	private int cnt;
	private int num;
	public WeightedQuickUnionCount(int N){
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
		while(p!=id[p]) {num+=2;p=id[p];}
		num++;
		return p;//返回根触点的索引
	}
	
	public void union(int p,int q){
		int i=find(p);
		int j=find(q);
		if(i==j) return;//根触点相同表示在同一分量上，跳过
		if(sz[i]<sz[j]){id[i]=j;sz[j]+=sz[i];num+=5;}
		else{id[j]=i;sz[i]+=sz[j];}
		cnt--;//
	}
	
	public boolean connected(int p,int q){
		return find(p)==find(q);
	}
	
	
	public static void main(String[] args) {
		int N=StdIn.readInt();
		WeightedQuickUnionCount uf=new WeightedQuickUnionCount(N);
		while(!StdIn.isEmpty()){
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(uf.connected(p, q)) continue;//每次connected，调用两次find,访问数组两次
			uf.union(p, q);
			for (int i=0;i<uf.id.length;i++) StdOut.print(uf.id[i]+" ");
			System.out.println("");
			StdOut.println(p+"  "+q+" "+uf.num);//打印连接
		}
		StdOut.println(uf.cnt+" components");
	}
}

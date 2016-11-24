import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
public class Multiway {

	public static void merge(In[] streams){
		int N=streams.length;//streams为存放了三个输入流的数组
		IndexMinPQ<String> pq=new IndexMinPQ<String>(N);
		
		for(int i=0;i<N;i++){
			if(!streams[i].isEmpty()){
				pq.insert(i, streams[i].readString());//streams里的每个输入流都可以读取字符串
			}
		}
		
		while(!pq.isEmpty()){
			StdOut.println(pq.min());
			int i=pq.delMin();//删除队列中的最小值，返回被删除值在keys的位置
			if(!streams[i].isEmpty()){
				pq.insert(i, streams[i].readString());
			}
		}
	}
	
	public static void main(String[] args){
		int N=args.length;
		In[] streams=new In[N];//streams为存放了三个输入流的数组
		for(int i=0;i<N;i++){
			streams[i]=new In(args[i]);
		}
		merge(streams);
	}
}

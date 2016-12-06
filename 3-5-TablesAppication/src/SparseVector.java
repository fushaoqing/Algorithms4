import edu.princeton.cs.algs4.*;

public class SparseVector {
	/*稀疏向量的计算*/
	 private int d;
	private LinearProbingHashST<Integer,Double> st;//这里的HashST指线性哈希表
	private int size(){
		return st.size();
	}
	
	 public SparseVector(int d) {
        this.d  = d;
        this.st = new LinearProbingHashST<Integer, Double>();
    }
	 
	public void put(int i,double x){
		st.put(i, x);
	}
	
	public double get(int i){
		if(!st.contains(i)){
			return 0.0;
		}
		return st.get(i);
	}
	
	public double dot(double[] that){
		double sum=0.0;
		for(int i:this.st.keys()){
			sum=sum+that[i]*this.get(i);//只需put非零项，值为零的键不参与计算
		}
		return sum;
	}
	
	public double dot(SparseVector that){//只对两个向量中都为非零的元素进行计算
		double sum=0.0;
		//取两个向量中非零元素个数较小值
		if(this.size()<=that.size())
			for(int i:this.st.keys())
				sum=sum+that.get(i)*this.get(i);
		else
			for(int i:that.st.keys())
				sum=sum+that.get(i)*this.get(i);
		return sum;	
	}
	
	public static void main(String[] args){
		SparseVector a=new SparseVector(4);
		SparseVector b=new SparseVector(4);
		a.put(0, 0.50);
        a.put(2, 0.75);
        a.put(3, 0.11);
        a.put(3, 0.00);
        b.put(2, 0.60);
        b.put(3, 0.90);
        double[] c={1.2,3.4,5.5,2.3};
        System.out.println(a.dot(b));
        System.out.println(a.dot(c));  
	}
}

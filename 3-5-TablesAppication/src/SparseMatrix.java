
public class SparseMatrix {

	private int n;//
	private int m;
	private SparseVector[] rows;
	public SparseMatrix(int n,int m){//构造n*m矩阵
		this.n=n;
		this.m=m;
		rows=new SparseVector[n];
		for(int i=0;i<n;i++)
			rows[i]=new SparseVector(m);
	}
	
	public void put(int i,int j,double value){
		rows[i].put(j, value);//输入第i行j列的值
	}
	
	public double get(int i,int j){
		return rows[i].get(j);//第i行SparseVector中的第j个值
	}
	
	public SparseMatrix dot(SparseMatrix that){
		SparseMatrix r=new SparseMatrix(this.n,that.n);
		for(int i=0;i<this.n;i++){
			for(int j=0;j<that.n;j++){
				//System.out.println(that.rows[j]);
				double val=this.rows[i].dot(that.rows[j]);
				//System.out.println(val);
				r.put(i, j, val);
			}
		}
		return r;
	}
	public String toString(){
		String s="";
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				s+=" "+rows[i].get(j);	
			}	
			s+="\n";
		}
		return s;
	}
	
	public static void main(String[] args) {
		 SparseMatrix A = new SparseMatrix(2,3);
		 SparseMatrix B = new SparseMatrix(2,3);
		 A.put(0, 0, 1);
		 A.put(0, 1, 2);
		 A.put(0, 2, 3);
		 B.put(0, 0, 1);
		 B.put(0, 1, 3);
		 B.put(0, 2, 5);
		 B.put(1, 0, 2);
		 //A.dot(B);
		 //A.dot(B).toString();
		 System.out.println(A.dot(B));

	}

}

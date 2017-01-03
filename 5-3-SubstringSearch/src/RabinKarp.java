
public class RabinKarp {

	private String pat;
	private long patHash;
	private int M;
	private long Q;//散列表的大小
	private long RM;
	private int R=256;
	
	public RabinKarp(String pat){
		this.pat=pat;
		this.M=pat.length();
		Q=997;
		RM=1;
		for(int i=1;i<M-1;i++)
			RM=(RM*R)%Q;
		patHash=hash(pat,M);
	}
	
	private long hash(String key,int M){//返回字符串key0到M-1段的hashcode
		long h=0;
		for(int i=0;i<M;i++)
			h=(h*R+key.charAt(i))%Q;
		return h;
	}
	
	public int search(String txt){
		int N=txt.length();
		long txtHash=hash(txt,M);
		if(patHash==txtHash) return 0;//一开始就匹配
		for(int i=M;i<N;i++){
			txtHash=((txtHash+txt.charAt(i-M)*(Q-RM))*R+txt.charAt(i))%Q;//利用了余数的性质
		if(patHash==txtHash) 
			return i-M+1;
		}
		return N;
	}
	
	public static void main(String[] args){
		BoyerMoore b=new BoyerMoore("dabr");
		System.out.println(b.search("abacadabrac"));
	}
}

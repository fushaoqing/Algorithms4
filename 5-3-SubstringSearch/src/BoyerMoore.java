
public class BoyerMoore {

	private int[] right;
	private String pat;
	public BoyerMoore(String pat){
		this.pat=pat;
		int M=pat.length();
		int R=256;
		right=new int[R];
		for(char c=0;c<R;c++)
			right[c]=-1;//未出现的均为-1,表示向右移一格
		for(int j=0;j<M;j++)
			right[pat.charAt(j)]=j;//pat中字符在pat出现的位置
	}
	
	public int search(String txt){
		int N=txt.length();
		int M=pat.length();
		int skip;
		for(int i=0;i<=N-M;i+=skip){
			skip=0;
			for(int j=M-1;j>=0;j--){//当出现不匹配字符时，有三种情况
				if(txt.charAt(i+j)!=pat.charAt(j)){
					skip=j-right[txt.charAt(i+j)];
					if(skip<1) skip=1;
					break;
				}
			}
			if(skip==0) return i;//当skip==0，说明skip没有被替换，表示从j到0位置的字符都匹配
		}
		return N;
	}
	
	public static void main(String[] args){
		BoyerMoore b=new BoyerMoore("c");
		System.out.println(b.search("abacadabrac"));
	}
}

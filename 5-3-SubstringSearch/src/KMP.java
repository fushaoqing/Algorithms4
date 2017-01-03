
public class KMP {

	private String pat;
	private int[][] dfa;
	
	public KMP(String pat){
		this.pat=pat;
		int R=256;
		int M=pat.length();
		dfa=new int[R][M];
		dfa[pat.charAt(0)][0]=1;
		
		for(int X=0,j=1;j<M;j++){
			for(char c=0;c<R;c++){
				dfa[c][j]=dfa[c][X];
			}
			dfa[pat.charAt(j)][j]=j+1;//j状态遇到当前匹配字符，转向下一个状态
		    X=dfa[pat.charAt(j)][X];//更新重启位置
		}
	}
	
	private int search(String txt){
		int i,j;
		int N=txt.length();
		int M=pat.length();
		for(i=0,j=0;i<N&&j<M;i++){
			j=dfa[txt.charAt(i)][j];//表示txt.charAt(i)与pat.charAt(j)进行了匹配
		}
		if(j==M) return i-M;
		return N;
	}
	
	public static void main(String[] args){
		KMP k=new KMP("abra");
		System.out.println(k.search("abacadabrac"));
	}
}

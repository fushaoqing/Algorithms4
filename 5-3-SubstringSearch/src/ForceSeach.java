
public class ForceSeach {

	public static int search1(String pat,String txt){
		int M=pat.length();
		int N=txt.length();
		for(int i=0;i<N-M;i++){//沿着i进行下去，i不会退
			int j;
			for(j=0;j<M;j++){
				if(txt.charAt(i+j)!=pat.charAt(j))//一旦出现不匹配，跳出内循环，从下一个i开始
					break;
			}
			if(j==M) return i;
		}
		return N;
	}
	
	public static int search2(String pat,String txt){
		int M=pat.length();
		int N=txt.length();
		int j;
		int i;
		for(i=0,j=0;i<N&&j<M;i++){
			if(txt.charAt(i)==pat.charAt(j)) 
				j++;
			else {i-=j;j=0;}//不匹配时，i会回退到起始匹配位置
		}
		if(j==M) return i-M;//最终会一直匹配到j=M;此时i=匹配起点+M
		return N;
	}
	
	
	public static void main(String args[]){
		System.out.println(search1("abac","abacadabrac"));
		System.out.println(search2("abac","abacadabrac"));
		System.out.println(1712%997);
	}
}

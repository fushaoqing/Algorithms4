
public class ASCII {

	private char[] ascii;
	private int[] inverse;
	private int R;
	
	public ASCII(){
		R=128;
		ascii=new char[128];
		inverse=new int[128];
		
		for(int i=0;i<R;i++)
			ascii[i]=(char)i;
		for(int i=0;i<R;i++)
			inverse[i]=i;
	}
	
	public int R(){
		return R;
	}
	
	public int lgR(){//表示一个索引所需的比特数,即inverse的最大索引的二进制位数
		int lgR=0;
		for(int t=R-1;t>0;t/=2)//除去0000000,还有127个数，所以要R-1
			lgR++;
		return lgR;
	}
	
	public char toChar(int index){//数字索引转字符
		return ascii[index];
	}
	
	public int toIndex(char c){
		return inverse[c];
	}
	
	public boolean contains(char c){
		return inverse[c]!=-1;
	}
	
	public int[] toIndeices(String s){
		char[] source = s.toCharArray();//把字符串转为字符数组
        int[] target  = new int[s.length()];
        for(int i=0;i<s.length();i++)
        	target[i]=toIndex(source[i]);
        return target;
	}
	
	public String toChars(int[] indices){
		StringBuilder s = new StringBuilder(indices.length);
		for(int i=0;i<indices.length;i++)
			s.append(toChar(indices[i]));
		return s.toString();//StringBuilder对应用toString()方法返回一个String
	}
	
	public static void main(String[] args){
		ASCII a=new ASCII();
		System.out.println(a.lgR());
		System.out.println(a.contains('/'));
		int[] indice={97,98,99};
		System.out.println(a.toChars(indice));
	}
	
}

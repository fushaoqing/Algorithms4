import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class WeiTuCompress {

	public static void expand(){//按照游程解压打出位图
		boolean b=false;//位图从右边先从0开始
		while(!BinaryStdIn.isEmpty()){
			char cnt=BinaryStdIn.readChar();//每8位一读取
			for(int i=0;i<cnt;i++){
				BinaryStdOut.write(b);
			}
			b=!b;
		}
		BinaryStdOut.close();
	}
	
	public static void compress(){//将位图编码为游程
		char cnt=0;
		boolean b,old=false;
		while(!BinaryStdIn.isEmpty()){
			b=BinaryStdIn.readBoolean();
			if(b!=old){//b为1
				BinaryStdOut.write(cnt);
				cnt=0;
				old=!old;//0和1交替出现，0先出现
			}else{
				if(cnt==255){
					BinaryStdOut.write(cnt);//游程cnt值不能超过255
					cnt=0;
					BinaryStdOut.write(cnt);
				}
					
			}
			cnt++;
		}
		BinaryStdOut.close();
	}
	public static void main(String[] args) {
		

	}

}

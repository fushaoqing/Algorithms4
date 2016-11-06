public class StopWatch {
	private final long start;
	public StopWatch(){
		start=System.currentTimeMillis();//java系统方法，返回当前以毫秒为单位的时间
	}
	public double elapsedTime(){
		long now=System.currentTimeMillis();
		return (now-start)/1000.0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N=Integer.parseInt(args[0]);
		int[] a=new int[N];
		for(int n=0;n<N;N++) a[n]=StdRandom.uniform(-1000000,1000000);
		StopWatch s=new StopWatch();
		System.out.println(ThreeSum.count(a));
		//System.out.println(ThreeSumFast.count(a));
		System.out.println(s.elapsedTime()+" seconds");
	}
}

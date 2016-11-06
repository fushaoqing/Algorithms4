package ex_1_4_3;
public class StopWatch {
	private final long start;
	public StopWatch(){
		start=System.currentTimeMillis();//java系统方法，返回当前以毫秒为单位的时间
	}
	public double elapsedTime(){
		long now=System.currentTimeMillis();
		return (now-start)/1000.0;
	}
}

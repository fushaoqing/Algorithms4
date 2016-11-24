import java.util.Comparator;
import edu.princeton.cs.algs4.*;
public class Transaction {
/*通过Comparator<>接口自定义比较方法compare()*/
	private final String who;
	private final Date when;
	private final double amount;
	
	public Transaction(String who,Date when,double amount){
		this.who=who;
		this.when=when;
		this.amount=amount;
	}
	
	public static class WhoOrder implements Comparator<Transaction>{
		public int compare(Transaction a,Transaction b){
			return a.who.compareTo(b.who);//调用String类的compareTo方法
		}
	}
	
	public static class WhenOrder implements Comparator<Transaction>{
		public int compare(Transaction a,Transaction b){
			return a.when.compareTo(b.when);
		}
	}
	
	public static class AmountOrder implements Comparator<Transaction>{
		public int compare(Transaction a,Transaction b){
			if(a.amount>b.amount) return 1;
			else if(a.amount<b.amount) return -1;
			else return 0;
		}
	}
	
	public boolean less(Transaction a,Comparator<Transaction> comparator){
		return comparator.compare(this, a)<0;
	}
	public static void main(String[] args) {
		Date date=new Date(11,23,2016);
		Transaction a=new Transaction("fu",date,300.0);
		Transaction b=new Transaction("shao",date,200.0);
		/*通过输入不同的Comparator实例参数，实现不同的排序目的*/
		System.out.println(a.less(b, new WhoOrder()));
		System.out.println(a.less(b, new WhenOrder()));
		System.out.println(a.less(b, new AmountOrder()));
	}

}

package duoXianCheng;

/*
 * 同步函数，把synchronized放到方法的修饰符上就可以了
 * 函数需要被对象调用，那么函数都有一个所属对象引用。就是this
 * 所以同步函数使用的锁是this.
 * 使用两个线程来买票。
 * 一个线程在同步代码块中.
 * 一个线程在同步函数中。
 * 都在执行卖票动作
 * 
 * 如果同步函数被静态修饰后，使用的锁是什么呢?
 * 通过验证，发现不在是this ，因为静态方法中页不可以定义this
 * 静态进内存时，内存中没有本类对象，但是一定有该类对应的字节码文件对象。类名.class 该对象类型是.class
 * 静态的同步方法，使用的锁是该方法所在类的字节码文件对象。类名.class
 * 
 * */
class Bank{
	private int sum;
	public synchronized void add(int n){
		sum = sum + n;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sum="+sum);
	}
}

class Cus implements Runnable{
	private Bank b = new Bank();
	public void run(){
		for(int x=0;x<3;x++){
			b.add(100);
		}
	}
}
public class Demo3 {
	public static void main(String[] args){
		Cus c = new Cus();
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		t1.start();
		t2.start();
	}
}

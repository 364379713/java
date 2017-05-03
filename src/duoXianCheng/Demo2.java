package duoXianCheng;

/*
 * 创建线程的第二种方式:实现Runable接口
 * 1，定义类实现Runnable接口
 * 2，覆盖Runnable接口中的run方法(将线程要运行的代码存放在该run方法中)
 * 3，通过Thread类建立线程对象
 * 4，将Runnable接口的子类对象作为实际参数传递给Thread类的构造函数
 * 5，调用Thread类的start方法开启线程并调用Runnable接口子类的run方法
 * 
 * 
 * 
 * 这种方法好处，可以继承别的类，java单继承的如果，要不然只能继承Thread类
 * 避免单继承的局限性，在定义线程时，建议使用Runable
 * 
 * 两种方式的区别
 * 继承Thread:线程代码存放Thread子类的run方法中
 * 实现Runnable,线程代码存放在接口子类的run方法中
 * 
 * 通过分心，发现，打印出 0， -1  ，-2等错票
 * 多程序的运行出现了安全问题(写多线程的安全问题);
 * 问题的原因:当多条语句在操作同一个线程共享数据时,一个线程对多条语句只执行了一部分，还没有
 * 执行完，另一个线程参与进来执行。导致了共享数据的错误
 * 
 * 解决办法对多条操作共享数据的语句，只能让一个线程都执行完。在执行过程中，其他线程不可以参与运行
 * 
 * java对多线程的安全问题提供了专业的解决方式就是同步代码块
 * synchronized(对象){需要被同步的代码} （操作共享的属性才使用同步）
 * 对象如同锁，持有锁的线程可以在同步中执行
 * 没有持有锁的线程即时获取cpu的执行权，也进不去，因为没有获取锁;
 * 
 * 同步的前提:1，必须要有两个或者两个以上的线程.
 * 2，必须是多个线程使用同一个锁。
 * 
 * 必须保证同步中只能有一个线程在运行
 * synchronized()同步好处:解决了安全问题，弊端，需要判断锁状态，消耗资源
 * 
 * */
class Ticket1 implements Runnable{
	Object obj = new Object();
	private int tick = 100;
	public void run(){
		while(true){
			//同步代码块
			synchronized (obj) {
				if(tick>0){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread()+"....sale"+tick--);
				}
			}	
		}
	}
}

public class Demo2{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ticket1 t = new Ticket1();
		
		Thread t1= new Thread(t);
		Thread t2= new Thread(t);
		Thread t3= new Thread(t);
		Thread t4= new Thread(t);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}

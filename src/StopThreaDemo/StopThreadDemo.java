package StopThreaDemo;
/*
 * stop方法已经过时
 * 
 * 如何停止线程?
 * 只有一种，run方法结束
 * 开启多线程运行，运行代码通常是循环结构。
 * 
 * 只要控制住循环，就可以让run方法结束，也就是线程结束
 * 
 * 特殊情况:当线程出于冻结状态就不会读取到标记。那么线程就不会停止
 * 
 * */

class StopThread implements Runnable{
	private boolean flag = true;
	public synchronized void run(){
		while(flag){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(Thread.currentThread().getName()+"....Exception");
				flag = false;
			}
			System.out.println(Thread.currentThread().getName()+"...run");
		}
	}
	public void changeFlag(){
		this.flag = false;
	}
}
public class StopThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StopThread st = new StopThread();
		Thread t1 = new Thread(st);
		Thread t2 = new Thread(st);
		t1.start();
		t2.start();
		
		int num = 0;
		while(true){
			if(num++ ==60){
				st.changeFlag();
				t1.interrupt();
				t2.interrupt();
				break;
			}
			System.out.println(Thread.currentThread().getName()+"...."+num);
		}
	}

}

package duoXianCheng;

/*死锁
 * 同步中嵌套同步而锁确不同
 * */
class MyLock{
	static Object locka = new Object();
	static Object lockb = new Object();
}
class Testone implements Runnable{
	private boolean flag;
	Testone(boolean flag){
		this.flag = flag;
	}
	public void run(){
		if(flag){
			synchronized(MyLock.locka){
				System.out.println("if locka");
				synchronized(MyLock.lockb){
					System.out.println("if lockb");
				}
			}
		}else{
			
			synchronized(MyLock.lockb){
				System.out.println("else lockb");
				synchronized(MyLock.locka){
					System.out.println("else locka");
				}
			}
		}
	}
}
public class Demo4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new Testone(true));
		Thread t2 = new Thread(new Testone(false));
		t1.start();
		t2.start();
	}

}

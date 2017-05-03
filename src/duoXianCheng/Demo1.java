package duoXianCheng;

/*多线程方法
 * 1，继承Thread类
 * 2，子类覆盖父类中的run方法,将县城运行的代码存放在run中
 * 3，建立子类对象的同时县城页被创建
 * 4，通过调用start方法开启线程
 * 
 * 
 * */
class Test extends Thread{
	public void run(){
		for(int x=0;x<60;x++){
			System.out.println("test run.."+x);
		}
	}
}

public class Demo1 {

	public static void main(String[] args) {
		Test t1 = new Test();
		Test t2 = new Test();
		t1.start();
		t2.start();
		for(int x=0;x<60;x++){
			System.out.println("main....."+x);
		}
	}

}

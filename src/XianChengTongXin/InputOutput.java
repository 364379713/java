package XianChengTongXin;
/*线程间通讯：其实就是多个线程在操作同一个资源
 * 但操作的动作不同
 * 等待唤醒机制
 * */
class Res{
	String name;
	String sex;
	boolean flag = false;
}
class Input implements Runnable{
	private Res r;
	Input(Res r){
		this.r = r;
	}
	public void run(){
		int x = 0;
		while(true){
			synchronized(r){
				if(r.flag)
					try {
						r.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				if(x==0){
					r.name = "mike";
					r.sex = "man";
				}else{
					r.name="丽丽";
					r.sex="女女";
				}
				x=(x+1)%2;
				r.flag = true;
				r.notify();
			}
		}
	}
}

class Output implements Runnable{
	private Res r;
	Output(Res r){
		this.r = r;
	}
	public void run(){
		while(true){
			synchronized(r){
				if(!r.flag)
					try {
						r.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				System.out.println(r.name+"....."+r.sex);
				r.flag = false;
				r.notify();
			}
		}
	}
}


public class InputOutput {
	public static void main(String[] args){
		Res r = new Res();
		Input in = new Input(r);
		Output out = new Output(r);
		
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		t1.start();
		t2.start();
	}
}

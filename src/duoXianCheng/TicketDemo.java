package duoXianCheng;



class Ticket extends Thread{
	private static int tick = 100;
	public void run(){
		while(true){
			if(tick>0){
				System.out.println(currentThread().getName()+"sale:"+ tick--);
			}
		}
	}
}

public class TicketDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ticket t1 = new Ticket();
		Ticket t2 = new Ticket();
		Ticket t3 = new Ticket();
		Ticket t4 = new Ticket();
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}

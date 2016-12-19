import javax.jms.JMSException;

public class JMSOrderClient {
	public static void main(String[] args) throws JMSException {
		Thread t1 = new Thread(new OrderProducer());
		Thread t2 =new Thread(new OrderConsumer());
		Thread t3 =new Thread(new ExecutedOrderConsumer());
		
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

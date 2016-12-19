import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;


public class OrderConsumer implements Runnable{

	private static void validateOrderAvailablity(Order order) throws ValidationException{
		if(order.getQuantity() > Inventory.MAX_QUNATITY){
			throw new ValidationException("Order Exceedes Max Threshold");
		} 
	}
	
	public void consume(String queue) throws JMSException{
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://NOILAB66150:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createQueue("OrderQueue");
		MessageConsumer consumer = session.createConsumer(dest);
		Message message = consumer.receive(1000);
		Object object = ((ObjectMessage)message).getObject();
		if(object instanceof Order){
			Order order = (Order)object;
			try {
				validateOrderAvailablity(order);
				order.setStatus(Status.PROCESSED);
				System.out.println(order+" is processed. Sending to Executed Order Queue.");
				sendExecutedOrder(order, "ExecutedOrderQueue");
			} catch (ValidationException e) {
				order.setStatus(Status.REJECTED);
				e.printStackTrace();
			} catch (JMSException e){
				order.setStatus(Status.CANCELLED);
				e.printStackTrace();
			}
		}
		session.close();
		connection.close();
	}
	
	public static void sendExecutedOrder(Order order, String queue) throws JMSException {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://NOILAB66150:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createQueue(queue);
		MessageProducer producer = session.createProducer(dest);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		ObjectMessage createObjectMessage = session.createObjectMessage(order);
		producer.send(createObjectMessage);
		session.close();
		connection.close();
	}

	@Override
	public void run() {
		try {
			consume(null);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class OrderProducer implements Runnable{

	public static void sendOrder(Order order) throws JMSException {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://NOILAB66150:61616");
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createQueue("OrderQueue");
		MessageProducer producer = session.createProducer(dest);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		ObjectMessage createObjectMessage = session.createObjectMessage(order);
		producer.send(createObjectMessage);
		session.close();
		connection.close();
	}

	public void produce() {
		Order order1 = new Order(10.00, 12);
		try {
			sendOrder(order1);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		produce();
	}
}

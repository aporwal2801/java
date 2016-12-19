import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;


public class ExecutedOrderConsumer implements Runnable{
	
	public void consumeExecutedOrder(String queue) throws JMSException{
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://NOILAB66150:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createQueue("ExecutedOrderQueue");
		MessageConsumer consumer = session.createConsumer(dest);
		Message message = consumer.receive();
		Object object = ((ObjectMessage)message).getObject();
		if(object instanceof Order){
			Order order = (Order)object;
			System.out.println(order+" is processed successfully.");
		}
		session.close();
		connection.close();
	}

	@Override
	public void run() {
		try {
			consumeExecutedOrder(null);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


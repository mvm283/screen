package taskmanagment;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import configuration.GlobalConfigs;
import utiles.urlChecker;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    private String queueName;

    public Producer(String queueName) {
        this.queueName = queueName;
    }

    public void producer(String message) throws TimeoutException, IOException {

        ConnectionFactory factory = new ConnectionFactory();
        //factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);

        channel.basicPublish("", queueName, false, null, message.getBytes());

        System.out.println("url sent!! > " + message);


    }
}

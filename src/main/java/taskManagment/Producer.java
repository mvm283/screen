package taskManagment;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    private String queueName="downloadQueue";

    public   void producer(String message) throws TimeoutException, IOException {
        ConnectionFactory  factory=new ConnectionFactory();
        //factory.setHost("localhost");
         Connection connection=factory.newConnection();
            Channel channel=connection.createChannel();
            channel.queueDeclare(queueName,false,false,false,null);

            channel.basicPublish("",queueName,false,null,message.getBytes());

            System.out.println("message sent!!");


    }
}

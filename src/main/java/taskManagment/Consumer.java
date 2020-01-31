package taskManagment;

import com.rabbitmq.client.*;
import scraper.ChromeWebDriver;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {


    private String queueName="downloadQueue";

    private ChromeWebDriver chromeWebDriver=new ChromeWebDriver();
    private long counter=0;
    public   void consumer( ) throws TimeoutException, IOException {
        ConnectionFactory factory=new ConnectionFactory();
        //factory.setHost("localhost");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(queueName,false,false,false,null);

        channel.basicConsume(queueName, true, new DeliverCallback() {
                    public void handle(String s, Delivery delivery) throws IOException {
                            String m=new String(delivery.getBody(),"UTF-8");
                            System.out.println( counter++ +" : received String : "+ m);
                        chromeWebDriver.captureUrl(m);
                    }
                }, new CancelCallback() {
                    public void handle(String s) throws IOException {
                    }
                }
                );


    }
}


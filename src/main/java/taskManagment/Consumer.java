package taskManagment;

import com.rabbitmq.client.*;
import scraper.ChromeWebDriver;
import thread.WorkerThread;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class Consumer {


    private String queueName="downloadQueue";

    private ChromeWebDriver chromeWebDriver=new ChromeWebDriver();
    private long counter=0;
    ExecutorService executor = Executors.newFixedThreadPool(5);
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
                        Runnable worker = new WorkerThread(m);
                        executor.execute(worker);
                    }
                }, new CancelCallback() {
                    public void handle(String s) throws IOException {
                    }
                }
                );


    }
}


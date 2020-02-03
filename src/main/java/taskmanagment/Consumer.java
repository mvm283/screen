package taskmanagment;

import com.rabbitmq.client.*;
import com.rabbitmq.client.CancelCallback;
import configuration.GlobalConfigs;
import scraper.ChromeWebDriver;
import thread.DatabaseWorker;
import thread.WorkerThread;
import utiles.RabitMqConnectionFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;


public class Consumer {
    private long counter=0;

    public Consumer(String queueName) throws TimeoutException, IOException {
        if(queueName.equals(GlobalConfigs.DATABASE_QUEUE))
            dbConsumer(queueName);
        else if(queueName.equals(GlobalConfigs.DOWNLOAD_QUEUE))
            downloadConsumer(queueName);


    }
    public Consumer(String queueName,String stop) throws TimeoutException, IOException {
        if(queueName.equals(GlobalConfigs.DATABASE_QUEUE))
            dbConsumer(queueName);
        else if(queueName.equals(GlobalConfigs.DOWNLOAD_QUEUE))
            downloadConsumerStop(queueName);


    }

    public Consumer() {
    }


    public void downloadConsumer(String queueName ) throws TimeoutException, IOException {
        final ExecutorService executor = Executors.newFixedThreadPool(5);
        ConnectionFactory factory= RabitMqConnectionFactory.rabitMqConnectionFactory();

        //factory.setHost("localhost");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(queueName,false,false,false,null);
        System.out.println("Started Download consumer!!");
                channel.basicConsume(queueName, true, new DeliverCallback() {
                    public void handle(String s, Delivery delivery) throws IOException {
                        String m=new String(delivery.getBody(),"UTF-8");
                        //System.out.println( counter++ +" : DON received String : "+ m);
                        Runnable worker = new WorkerThread(m);
                        executor.execute(worker);
                    }
                }, new CancelCallback() {
                    public void handle(String s) throws IOException {
                    }
                }
        );
    }

    public void downloadConsumerStop(String queueName ) throws TimeoutException, IOException {

    }
    public void dbConsumer(String queueName ) throws TimeoutException, IOException {
       final ExecutorService executor = Executors.newFixedThreadPool(1);
        ConnectionFactory factory=RabitMqConnectionFactory.rabitMqConnectionFactory();
        //factory.setHost("localhost");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(queueName,false,false,false,null);
        System.out.println("Started Database consumer!!");
        channel.basicConsume(queueName, true, new DeliverCallback() {
                    public void handle(String s, Delivery delivery) throws IOException {
                        String m=new String(delivery.getBody(),"UTF-8");
                        //System.out.println( counter++ +" : DB received String : "+ m);
                        Runnable worker = new DatabaseWorker(m);
                        executor.execute(worker);
                    }
                }, new CancelCallback() {
                    public void handle(String s) throws IOException {
                    }
                }
        );
    }
}


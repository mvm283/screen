package thread;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import scraper.ChromeWebDriver;
import taskManagment.Consumer;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public      class WorkerThread implements Runnable {
    public String message;
    public WorkerThread(String s){
        this.message=s;
    }
    public void run() {
        System.out.println(Thread.currentThread().getName()+" (Start) message = "+message);
        try {
            processmessage();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" (End)");//prints thread name
    }

    private ChromeWebDriver chromeWebDriver=new ChromeWebDriver();
    public   void processmessage() throws TimeoutException, IOException {

        chromeWebDriver.captureUrl( message);
        //chromeWebDriver.captureUrl( this.message);
      //  try {  Thread.sleep(2000);  } catch (InterruptedException e) { e.printStackTrace(); }
    }
}


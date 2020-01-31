package thread;
import taskManagment.Consumer;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class WorkerThread implements Runnable {
    private String message;
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
    private void processmessage() throws TimeoutException, IOException {
        Consumer consumer=new Consumer();
        consumer.consumer();

      //  try {  Thread.sleep(2000);  } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
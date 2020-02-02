package thread;
import scraper.ChromeWebDriver;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public  class WorkerThread implements Runnable {

    public String message;
    private ChromeWebDriver chromeWebDriver=new ChromeWebDriver();

    public WorkerThread(String s){
        this.message=s;
    }
    public void run() {
        //System.out.println(Thread.currentThread().getName()+" (Start) message = "+message);
        try {
            processmessage();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(Thread.currentThread().getName()+" (End)");//prints thread name
    }

    public   void processmessage() throws TimeoutException, IOException {
        chromeWebDriver.captureUrl( message);
    }
}


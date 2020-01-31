
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import scraper.ChromeWebDriver;
import taskManagment.Consumer;
import taskManagment.Producer;
import thread.WorkerThread;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class Launcher {


    public static void main( String[] args ) throws IOException, TimeoutException {





        Producer producer=new Producer();
        for(int i=0;i<10 ;i++){
            producer.producer("http://www.google.com");
        }


        ExecutorService executor = Executors.newFixedThreadPool(5);//creating a pool of 5 threads
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);//calling execute method of ExecutorService
        }
        executor.shutdown();
        while (!executor.isTerminated()) {   }

        System.out.println("Finished all threads");


/*
    Consumer consumer=new Consumer();
        consumer.consumer();
*/


    }
}

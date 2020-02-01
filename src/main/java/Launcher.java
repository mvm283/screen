
import configuration.GlobalConfigs;
import h.manager.ManagerFactory;
import h.model.WebUrlModel;
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

import java.io.*;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class Launcher {


    public static void main( String[] args ) throws IOException, TimeoutException {



        Producer producer=new Producer(GlobalConfigs.DOWNLOAD_QUEUE);

        producer.producer("http://www.google.com");
        producer.producer("http://www.yahoo.com");
        producer.producer("http://www.msn.com");
        producer.producer("http://www.varzesh3.com");

        Consumer consumer=new Consumer();
        consumer.dounloadConsumer(GlobalConfigs.DOWNLOAD_QUEUE);
        consumer.dbConsumer(GlobalConfigs.DATABASE_QUEUE);



    }
}

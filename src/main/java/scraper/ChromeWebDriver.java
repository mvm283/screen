package scraper;

import com.google.gson.Gson;
import configuration.GlobalConfigs;
import fileUtilitis.FileRepository;
import database.model.WebUrlModel;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.ResourceUtils;
import taskmanagment.Producer;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class ChromeWebDriver {

    private long counter=0;
    //private WebDriver driver = new ChromeDriver();
    public void captureUrl(String url) throws IOException, TimeoutException {

        DesiredCapabilities des=new DesiredCapabilities();
        des.setJavascriptEnabled(true);
        //System.setProperty("webdriver.chrome.driver", "D:\\uni\\screenshot\\tools\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", ResourceUtils.getFile(GlobalConfigs.CHROOME_DRIVER).getAbsolutePath()
               );

        WebDriver driver = new ChromeDriver();
        driver.get(url);

        String dbFileName= String.valueOf(url.concat(String.valueOf(Math.random())).hashCode());

        if("aws".equals(GlobalConfigs.DOWNLOAD_FROM_STORAGE))
            FileRepository.saveFile( ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64),dbFileName);
        else  if("local".equals(GlobalConfigs.DOWNLOAD_FROM_STORAGE))
             FileRepository.saveFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),dbFileName);

        WebUrlModel webUrlModel=new WebUrlModel();
        webUrlModel.setFileName(dbFileName);
        webUrlModel.setUrl(url);
        DateTime now = new DateTime(DateTimeZone.UTC);
        webUrlModel.setTimeStamp(new Date(now.getMillis()));

        Gson gson = new Gson();
        String toDbMessage=gson.toJson(webUrlModel);

        Producer producer=new Producer(GlobalConfigs.DATABASE_QUEUE);
        producer.producer(toDbMessage);

        driver.close();

    }
}

package scraper;

import com.google.common.primitives.Bytes;
import com.google.gson.Gson;
import configuration.GlobalConfigs;
import fileUtilitis.FileRepository;
import h.manager.ManagerFactory;
import h.model.WebUrlModel;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import taskManagment.Producer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import static sun.plugin2.util.PojoUtil.toJson;

public class ChromeWebDriver {

    private long counter=0;
    //private WebDriver driver = new ChromeDriver();
    public void captureUrl(String url) throws IOException, TimeoutException {

        DesiredCapabilities des=new DesiredCapabilities();
        des.setJavascriptEnabled(true);
        System.setProperty("webdriver.chrome.driver", "D:\\uni\\screenshot\\tools\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(url);

        String dbFileName= String.valueOf(url.concat(String.valueOf(Math.random())).hashCode());
        //File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //to local storage
        FileRepository.saveFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),dbFileName);
        //to aws s3 storage
        //FileRepository.saveFile( ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64),dbFileName);

        WebUrlModel webUrlModel=new WebUrlModel();
        webUrlModel.setFileName(dbFileName);
        webUrlModel.setUrl(url);
        DateTime now = new DateTime(DateTimeZone.UTC);
        webUrlModel.setTimeStamp(new Date(now.getMillis()));
        //String toDbMessage= toJson(webUrlModel);

        Gson gson = new Gson();
        String toDbMessage=gson.toJson(webUrlModel);

        Producer producer=new Producer(GlobalConfigs.DATABASE_QUEUE);
        producer.producer(toDbMessage);

        driver.close();

    }
}

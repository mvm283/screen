package scraper;

import configuration.GlobalConfigs;
import h.manager.ManagerFactory;
import h.model.WebUrlModel;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import taskManagment.Producer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

public class ChromeWebDriver {

    private long counter=0;
    //private WebDriver driver = new ChromeDriver();
    public void captureUrl(String url) throws IOException, TimeoutException {

        DesiredCapabilities des=new DesiredCapabilities();
        des.setJavascriptEnabled(true);
        System.setProperty("webdriver.chrome.driver", "D:\\uni\\screenshot\\tools\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(url);

        String fileName=GlobalConfigs.FILE_PATH+(url.concat(String.valueOf(Math.random()))).hashCode() +".jpg";

        File srcFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(srcFile1, new File((GlobalConfigs.FILE_PATH+(url.concat(String.valueOf(counter++))).hashCode() +".jpg")), true);
        FileUtils.copyFile(srcFile1, new File(fileName), true);

        WebUrlModel webUrlModel=new WebUrlModel();
        webUrlModel.setFileName(fileName);
        webUrlModel.setUrl(url);
        webUrlModel.setTimeStamp(LocalDateTime.now());
        ManagerFactory.getInstance().getIWebUrlManager().addWebUrl(webUrlModel);

        Producer producer=new Producer(GlobalConfigs.DATABASE_QUEUE);
        producer.producer(url);

        driver.close();

    }
}

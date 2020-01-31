package scraper;

import configuration.GlobalConfigs;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class ChromeWebDriver {

    private long counter=0;
    //private WebDriver driver = new ChromeDriver();
    public void captureUrl(String url) throws IOException {

        DesiredCapabilities des=new DesiredCapabilities();
        des.setJavascriptEnabled(true);
        System.setProperty("webdriver.chrome.driver", "D:\\uni\\screenshot\\tools\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(url);

        File srcFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile1, new File((GlobalConfigs.FILE_PATH+(url.concat(String.valueOf(counter++))).hashCode() +".jpg")), true);

        driver.close();

    }
}

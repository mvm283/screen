
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;

import java.io.*;

public class launcher {


    public static void main( String[] args ) throws InterruptedException, IOException {

        DesiredCapabilities des=new DesiredCapabilities();
        des.setJavascriptEnabled(true);
        //des.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"D:\\uni\\screenshot\\phantomjs\\bin\\phantomjs.exe");
        //System.setProperty("phantomjs.binary.path", "D:\\uni\\screenshot\\phantomjs\\bin\\phantomjs.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\uni\\screenshot\\tools\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        for(int i=0;i<1000;i++) {
            driver.get("http://www.google.com");

            File srcFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile1, new File(("D:\\uni\\tempShot\\dd"+i+".jpg")), true);

        }
/*
        WebDriver wd=new PhantomJSDriver();
        wd.get("https://www.polito.it/");

        File srcFile=((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,new File("d:\\dd.jpg"),true);
*/
    }
}

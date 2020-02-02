package scraper;

import configuration.GlobalConfigs;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

public class PhantomJsDriver {

    private long counter=0;
    public void captureUrl(String url) throws IOException {

        DesiredCapabilities des=new DesiredCapabilities();
        des.setJavascriptEnabled(true);
        System.setProperty("phantomjs.binary.path", ResourceUtils.getFile(GlobalConfigs.PHANTOMJS_DRIVER).getAbsolutePath());

        WebDriver wd=new PhantomJSDriver();
        wd.get(url);

        File srcFile=((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,new File((GlobalConfigs.FILE_PATH+(url.concat(String.valueOf(counter++))).hashCode() +".jpg")),true);

    }
}

import configuration.GlobalConfigs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;
import taskmanagment.Consumer;
import utiles.UserInterface;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class UserInterfaceTest {

    public static   String urlListFileLocation="";

    @BeforeAll
    public static void init () throws TimeoutException, IOException {
        Consumer consumer=new Consumer();
        consumer.downloadConsumer(GlobalConfigs.DOWNLOAD_QUEUE);
        consumer.dbConsumer(GlobalConfigs.DATABASE_QUEUE);
    }
    @Test
    public void produceFromFileTest() throws Exception {
        urlListFileLocation= ResourceUtils.getFile(GlobalConfigs.TEST_RECOURCES+"urlList.txt").getAbsolutePath();

        String command1="ctl -p -f "+urlListFileLocation;
        UserInterface.run(command1);

    }
    @Test
    public void produceFromStringTest() throws Exception {
        Consumer consumer=new Consumer();
        consumer.downloadConsumer(GlobalConfigs.DOWNLOAD_QUEUE);
        consumer.dbConsumer(GlobalConfigs.DATABASE_QUEUE);

        String command1="ctl -p -s http://www.msn.com";
        UserInterface.run(command1);

    }

    @Test
    public void requestWithFileTest() throws Exception {
        urlListFileLocation= ResourceUtils.getFile(GlobalConfigs.TEST_RECOURCES+"urlList.txt").getAbsolutePath();

        String command1="ctl -p -s http://www.msn.com";
        UserInterface.run(command1);

    }
}

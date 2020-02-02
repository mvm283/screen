import configuration.GlobalConfigs;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
import utiles.UserInterface;

public class UserInterfaceTest {

    public static   String urlListFileLocation="";
    @Test
    public void fullRunTest() throws Exception {
        urlListFileLocation= ResourceUtils.getFile("urlList.txt").getAbsolutePath();


        String command1="ctl -p -f "+urlListFileLocation;
        UserInterface.run(command1);

        String command2="ctl -q -f "+urlListFileLocation + " d:/";
        UserInterface.run(command1);
    }
}

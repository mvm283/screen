import org.junit.Test;
import utiles.UserInterface;

public class UserInterfaceTest {

    public static final String urlListFileLocation="resources/urlList.txt";
    @Test
    public void fullRunTest() throws Exception {

        String command1="ctl -p -f "+urlListFileLocation;
        UserInterface.run(command1);

        String command2="ctl -q -f "+urlListFileLocation + " d:/";
        UserInterface.run(command1);
    }
}

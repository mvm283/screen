
import configuration.GlobalConfigs;
import taskmanagment.Consumer;
import taskmanagment.Producer;
import utiles.UserInterface;

import java.io.*;
import java.util.concurrent.TimeoutException;

public class Launcher {


    public static void main( String[] args ) throws Exception {

        UserInterface.entryPoint(args);

        //produceFromStringTest();

    }

    public static void produceFromStringTest() throws Exception {
        Consumer consumer=new Consumer();
        consumer.downloadConsumer(GlobalConfigs.DOWNLOAD_QUEUE);
        consumer.dbConsumer(GlobalConfigs.DATABASE_QUEUE);

        String command1="ctl -p -s http://www.msn.com";
        UserInterface.run(command1);

    }
}

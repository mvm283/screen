
import configuration.GlobalConfigs;
import taskmanagment.Consumer;
import taskmanagment.Producer;

import java.io.*;
import java.util.concurrent.TimeoutException;

public class Launcher {


    public static void main( String[] args ) throws IOException, TimeoutException {



        Producer producer=new Producer(GlobalConfigs.DOWNLOAD_QUEUE);

        producer.producer("http://www.google.com");
        producer.producer("http://www.yahoo.com");
        producer.producer("http://www.msn.com");
        producer.producer("http://www.varzesh3.com");

        Consumer consumer=new Consumer(GlobalConfigs.DOWNLOAD_QUEUE);
        //consumer.downloadConsumer(GlobalConfigs.DOWNLOAD_QUEUE);
        consumer.dbConsumer(GlobalConfigs.DATABASE_QUEUE);

        Consumer consumer1=new Consumer(GlobalConfigs.DOWNLOAD_QUEUE,"stop");

    }
}

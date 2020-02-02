import configuration.GlobalConfigs;
import org.junit.jupiter.api.Test;
import taskmanagment.Consumer;
import taskmanagment.Producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerTest {

    @Test
    public void producerTest() throws TimeoutException, IOException {

        Producer producer=new Producer(GlobalConfigs.DOWNLOAD_QUEUE);
        producer.producer("http://www.google.com");
        producer.producer("http://www.yahoo.com");
        producer.producer("http://www.msn.com");
        producer.producer("http://www.varzesh3.com");

    }
}

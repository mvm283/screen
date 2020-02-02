import configuration.GlobalConfigs;
import org.junit.jupiter.api.Test;
import taskmanagment.Consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerTest {

    @Test
    public void consumerTest() throws TimeoutException, IOException {
        Consumer consumer=new Consumer();
        consumer.downloadConsumer(GlobalConfigs.DOWNLOAD_QUEUE);
        consumer.dbConsumer(GlobalConfigs.DATABASE_QUEUE);

    }

}

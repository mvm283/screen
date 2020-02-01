package thread;

import com.google.gson.Gson;
import h.manager.ManagerFactory;
import h.model.WebUrlModel;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeoutException;
import static sun.plugin2.util.PojoUtil.*;

public class DatabaseWorker extends  WorkerThread {

    public DatabaseWorker(String s) {
        super(s);

    }

    @Override
    public void processmessage() throws TimeoutException, IOException {

        Gson gson = new Gson();
        WebUrlModel webUrlModel =  gson.fromJson(message,   WebUrlModel.class);

        ManagerFactory.getInstance().getIWebUrlManager().addWebUrl(webUrlModel);
    }
}

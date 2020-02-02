package thread;

import com.google.gson.Gson;
import database.manager.ManagerFactory;
import database.model.WebUrlModel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DatabaseWorker implements Runnable {
    public String message;
    public DatabaseWorker(String s){
        this.message=s;
    }
    public void run() {
        //System.out.println(Thread.currentThread().getName()+" (Start) message = "+message);
        try {
            processmessage();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println(Thread.currentThread().getName()+" (End)");//prints thread name
    }

    public void processmessage() throws TimeoutException, IOException {

        Gson gson = new Gson();
        WebUrlModel webUrlModel =  gson.fromJson(message,   WebUrlModel.class);

        ManagerFactory.getInstance().getIWebUrlManager().addWebUrl(webUrlModel);
    }
}

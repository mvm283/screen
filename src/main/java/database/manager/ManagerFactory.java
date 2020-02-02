package database.manager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ManagerFactory {

    private static ManagerFactory instance = null;

    public static ApplicationContext appContext = new ClassPathXmlApplicationContext(
            "applicationContext.xml");

    private IWebUrlManager iWebUrlManager;

    public static ManagerFactory getInstance() {
        if (instance == null)
            instance = new ManagerFactory();
        return instance;
    }

    private ManagerFactory() {
    }
//ManagerFactory.getInstance().getIWebUrlManager().addWebUrl(weburl);
    public IWebUrlManager getIWebUrlManager() {
        if (iWebUrlManager == null) {
            iWebUrlManager = (IWebUrlManager) appContext
                    .getBean("webUrlManagerService");
        }
        return iWebUrlManager;
    }

}

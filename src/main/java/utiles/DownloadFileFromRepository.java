package utiles;

import configuration.GlobalConfigs;
import fileutilitis.FileRepository;
import database.manager.ManagerFactory;
import database.model.WebUrlModel;

import java.io.IOException;

public class DownloadFileFromRepository {

    public static  void getFile(String url,String saveingPath) throws IOException {
        if (!urlChecker.IsMatch(url, GlobalConfigs.URL_REGEX)) {
            System.out.println("url is not correct.");
            return;
        }
        WebUrlModel webUrlModel = ManagerFactory.getInstance().getIWebUrlManager().getbUrl(url);

        if("aws".equals(GlobalConfigs.DOWNLOAD_FROM_STORAGE))
            FileRepository.downloadFileFromAwsStorage(webUrlModel.getFileName(),saveingPath);
        else
            if("local".equals(GlobalConfigs.DOWNLOAD_FROM_STORAGE))
            FileRepository.downloadFileFromLocalStorage(webUrlModel.getFileName(),saveingPath);

    }

}

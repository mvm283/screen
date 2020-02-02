package utiles;

import configuration.GlobalConfigs;
import fileUtilitis.FileRepository;
import database.manager.ManagerFactory;
import database.model.WebUrlModel;

import java.io.IOException;

public class DownloadFileFromRepository {

    public static  void getFile(String url,String saveingPath) throws IOException {
        WebUrlModel webUrlModel = ManagerFactory.getInstance().getIWebUrlManager().getbUrl(url);

        if("aws".equals(GlobalConfigs.DOWNLOAD_FROM_STORAGE))
            FileRepository.downloadFileFromAwsStorage(webUrlModel.getFileName(),saveingPath);
        else
            if("local".equals(GlobalConfigs.DOWNLOAD_FROM_STORAGE))
            FileRepository.downloadFileFromLocalStorage(webUrlModel.getFileName(),saveingPath);

    }

}

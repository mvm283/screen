package utiles;

import configuration.GlobalConfigs;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class UrlFileReader {

    public static List<String> readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        List<String> urlList= Arrays.asList(data.split(GlobalConfigs.URL_SEPARATOR));
        return urlList;
    }
}

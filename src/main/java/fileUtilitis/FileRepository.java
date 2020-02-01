package fileUtilitis;

import com.amazonaws.AmazonServiceException;
import com.google.common.primitives.Bytes;
import configuration.GlobalConfigs;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import utiles.AWSUtil;

import java.io.*;

public class FileRepository {


    private static final AWSUtil aws = new AWSUtil();
    private static final String FILE_EXTENTION=".jpg";


    public static void saveFile(File srcfile,String fileName) throws IOException {
          fileName= GlobalConfigs.FILE_PATH+fileName +FILE_EXTENTION;
        saveFileToLocalStorage(srcfile,fileName);

    }
    public static void saveFile(String srcfile, String fileName) throws IOException {
        saveFileToAwsStorage(srcfile,fileName+FILE_EXTENTION);

    }
    public static void saveFileToLocalStorage(File srcfile,String fileName) throws IOException {
        FileUtils.copyFile(srcfile, new File(fileName), true);
    }
    public static void saveFileToAwsStorage(String srcFile,String fileName) throws IOException {

        byte[] byteArray = Base64.decodeBase64(srcFile.getBytes());
        ByteArrayInputStream bytesIn = null;

        try {
            System.out.println(fileName + " - start to write11!");
            bytesIn = new ByteArrayInputStream(byteArray);
            aws.putFileIntoS3(bytesIn, fileName);
            System.out.println(fileName + " - saved to aws!");
        } catch (AmazonServiceException e1) {
            e1.printStackTrace();
        }    finally {
            try {
                if (bytesIn != null) {
                    bytesIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

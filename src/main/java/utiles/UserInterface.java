package utiles;

import configuration.GlobalConfigs;
import org.apache.commons.lang3.StringUtils;
import taskmanagment.Consumer;
import taskmanagment.Producer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class UserInterface {




    public static List<String> fileHandler(String path) throws Exception {
        return  UrlFileReader.readFileAsString(path );

    }


    private  static boolean fileExists(String fileName){
        if(!(new File(fileName).exists()))
        {
            System.out.println( "File "+fileName+" is not exists");
            return  false;
        }
        else
            return  true;
    }

    private static boolean checkArguments(String[] args1) {
        if (args1.length>=4)
        if ("-p".equals(args1[1]) || "-q".equals(args1[1]))
            if (("-s".equals(args1[2]) && args1[3].length()>0)
                  ||  ("-f".equals(args1[2])  &&  args1[3].length()>0 && fileExists(args1[3])))
                    return true;
    return false;



    }

    public static void commandControl(String[] args) throws Exception {
        Producer producer=new Producer(GlobalConfigs.DOWNLOAD_QUEUE);
        //produce
        if ("-p".equals(args[1])) {
            if ("-f".equals(args[2])) {//read file
                for (String url : fileHandler(args[3])) {
                    //check it is a valid url
                    producer.producer(url.replaceAll("(\\r|\\n)", ""));
                }
            } else if ("-s".equals(args[2])) {//read from string
                List<String> urls = Arrays.asList(args[3].split(";"));
                for (String url : urls) {
                    producer.producer(url.replaceAll("\"",""));
                }
            }
        }else
        //query
        if ("-q".equals(args[1])) {
            if ("-f".equals(args[2])) {//read file
                for (String url : fileHandler(args[3])) {

                    DownloadFileFromRepository.getFile(url.replaceAll("(\\r|\\n)", ""),args[4]);
                }
            } else if ("-s".equals(args[2])) {//read from string
                List<String> urls = Arrays.asList(args[3].split(";"));
                for (String url : urls) {
                    DownloadFileFromRepository.getFile(url.replaceAll("\"",""),args[4]);
                }
            }
        }

    }

    public static void run(String line) throws Exception {
        // split line into arguments
        String[] args1 = line.split(" ");

        // process arguments
        if (args1.length > 0) {
            if (args1[0].equalsIgnoreCase("q")) {
                System.out.println("exiting");
                System.exit(0);
            } else if (args1[0].equalsIgnoreCase("ctl")) {
                if(checkArguments(args1))
                    commandControl(args1);
                else
                    System.out.println("Bad command (e.i ctl -p/-q -f/-s filename)");
                System.out.print("-->");
            }else
            {
                System.out.println("Bad command (e.i ctl -p/-q -f/-s filename)");
                System.out.print("-->");
            }
        }
    }
    public static void main(String[] args) throws Exception {

        Consumer consumer=new Consumer(GlobalConfigs.DOWNLOAD_QUEUE);
        //consumer.downloadConsumer(GlobalConfigs.DOWNLOAD_QUEUE);
        consumer.dbConsumer(GlobalConfigs.DATABASE_QUEUE);


        System.out.print("-->");
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {

            String line = sc.nextLine().replaceAll("(\\r|\\n)", "");
            // return pressed
            if (line.length() == 0) {
                continue;
            }
            run(line);
        }
    }
}

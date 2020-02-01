package utiles;

import configuration.GlobalConfigs;
import taskmanagment.Consumer;
import taskmanagment.Producer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class UserInterface {

    public static void entryPoint(String[] args) throws TimeoutException, IOException {


/*
screenshot servicectl -c  download start/stop
screenshot servicectl -c  db start/stop

screenshot loadctl -f fileLocation
screenshot loadctl -s "url1;ur2;.."

screenshot readctl -f fileLocation
screenshot readctl -s "url1;ur2;.."
*/

        Producer producer=new Producer(GlobalConfigs.DOWNLOAD_QUEUE);

        if (args.length == 4) {
            if ("servicectl".equals(args[0])) {
                if ("-c".equals(args[1])) {
                    if (GlobalConfigs.DOWNLOAD_QUEUE.equals(args[2])
                            || GlobalConfigs.DOWNLOAD_QUEUE.equals(args[2])) {
                        if ("start".equals(args[2])) {
                            Consumer consumer = new Consumer(args[2].toString());
                        } else if ("stop".equals(args[2])) {
                            Consumer consumer = new Consumer(args[2].toString(), "stop");
                        }
                    }

                } else if ("-p".equals(args[1])) {
//producer
                }

            } else if ("loadctl".equals(args[0])) {
                if ("-f".equals(args[1])) {
//read file
                    if (args[2]==null && args[2].isEmpty())
                    {
                        System.out.println("Please enter a valid file");
                    }
                    File urlFile=new File(args[2]);
                    List<String> urls= Arrays.asList(args[2].split(";"));
                    for (String url:urls  ) {
                        producer.producer(url);
                    }
                } else if ("-s".equals(args[1])) {
//read from string
                    List<String> urls= Arrays.asList(args[2].split(";"));
                    for (String url:urls  ) {
                        producer.producer(url);
                    }


                }
            } else if ("readctl".equals(args[0])) {
                if ("-f".equals(args[1])) {
//download from aws s3 basec file
                } else if ("-s".equals(args[1])) {
//download from aws s3 basec string
                }
            } else
            {
                //check command please
            }
        }


    }
}

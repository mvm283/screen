ScreenshotService

This repository contains ScreenshotService application, which the user guid is as follows:

1- nstall Docker

2- install mysql

3- run rabbitmq with this command:
docker run -it --rm --hostname somerabbit --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
 
4- To store files there are two ways: local or Aws s3. I put both capability in the code, but i did not have free aws s3 repository to put credential in the code to use aws s3. to store in the local storage there is a hardcoded path in the code: d:\screenshots


5- To run consumer service run this command in the target folder of the project:
java -jar screenshot.jar screenshotservice c

6- To run the client run this command in the target folder of the project:
java -jar screenshot.jar screenshotservice p

7- After the step 4 you will enter into a command line application. Commands in the command line are as follow:

ctl -p/-q -f/-s file.txt/url 
 
-p : when the client send request to make screenshot from url/urls (url will sent to downloadQueue)
-q : when the client send request to make a query for url/urls (if for the url there is a captured screenshot, the jpg file will save into the path which client mentioned in the command line)

-f : url/urls from file
-s : url/urls from string

scenarios:
1- make screenshot from urls inside file d:\urlList.txt :
ctl -p -f d:\urlList.txt

2- make screenshot from url/urls inside string :
ctl -p -f http://www.msn.com

3- make query from url/urls inside file and save them into d:\ :
ctl -p -f d:\urlList.txt d:\

4- make query from url/urls inside string and save them into d:\ :
ctl -p -f http://www.msn.com d:\

----------





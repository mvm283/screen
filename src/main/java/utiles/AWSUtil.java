package utiles;

import java.io.*;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;


public class AWSUtil {

	private static final String accessKey = "accessKey";
	private static final String secretKey = "secretKey";
	private static final String bucketName = "screenShotServiceRepository";
	private static final String rootPerfixName = "aws";


	public S3Object getObjFromS3(String fileName) {

		try {
			// check data existence
			if (fileName != null && !fileName.isEmpty()) {
				fileName = rootPerfixName + fileName;
				// retrieve file
				AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
				AmazonS3 s3client = new AmazonS3Client(credentials);

				S3Object obj = s3client.getObject(new GetObjectRequest(bucketName, fileName));

				return obj;
			}
		} catch (Exception e) {
			// File not found
			e.printStackTrace();
			;
		}

		return null;
	}

	public void downloadFileFromS3(String fileName,String savingPath){
		try {
			S3Object o=	getObjFromS3( fileName);
			S3ObjectInputStream s3is = o.getObjectContent();
			FileOutputStream fos = new FileOutputStream(new File(savingPath.concat("/").concat(fileName)));
			byte[] read_buf = new byte[1024];
			int read_len = 0;
			while ((read_len = s3is.read(read_buf)) > 0) {
				fos.write(read_buf, 0, read_len);
			}
			s3is.close();
			fos.close();
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}


	}

	public void putFileIntoS3(ByteArrayInputStream bytesIn, String fileName) {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey,
				secretKey);
		AmazonS3 s3client = new AmazonS3Client(credentials);
		ObjectMetadata metaData = new ObjectMetadata();
		s3client.putObject(bucketName, fileName, bytesIn, metaData);
		return;
	}

}

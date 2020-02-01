package utiles;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
 

public class AWSUtil {

	private static final String accessKey = "a";
	private static final String secretKey = "b";
	private static final String bucketName = "screenShotServiceRepository";
	private static final String rootPerfixName = "aws";
	public S3Object getFileFromS3(String fileName) {

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

	public void putFileIntoS3(ByteArrayInputStream bytesIn, String fileName) {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey,
				secretKey);
		AmazonS3 s3client = new AmazonS3Client(credentials);
		ObjectMetadata metaData = new ObjectMetadata();
		s3client.putObject(bucketName, fileName, bytesIn, metaData);
		return;
	}

}

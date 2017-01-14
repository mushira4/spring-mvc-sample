package br.com.spring.mvc.basics.infrastructure;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component("amazonS3FileSaver")
public class AmazonS3FileSaver implements FileSaver {

	//	@Autowired
//	private AmazonS3Client s3Client;
//
	@Override
	public String write(String baseFolder, MultipartFile file) {
//		try {
//			s3Client.putObject("books", file.getOriginalFilename(), file.getInputStream(), new ObjectMetadata());
//		} catch (AmazonClientException | IOException e) {
//			e.printStackTrace();
//		}
//
//		return "http://s3.amazonaws.com/books/" + file.getOriginalFilename();
		return "";
	}
}
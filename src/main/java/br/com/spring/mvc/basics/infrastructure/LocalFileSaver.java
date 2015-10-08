package br.com.spring.mvc.basics.infrastructure;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("localFileSaver")
public class LocalFileSaver implements FileSaver {

	@Autowired
	private HttpServletRequest request;
	
	
	// FIXME: It need to check if the directory exists, if not has to create it. 
	public String write(String baseFolder, MultipartFile file){
		String realPath = request.getServletContext().getRealPath("/" + baseFolder);
		System.out.println("Base Folder: " + baseFolder);
		
		try{
			String path = realPath + "/" + file.getOriginalFilename();

			System.out.println("Upload File Path:" + path);
			file.transferTo(new File(path));
			
			return baseFolder + "/" + file.getOriginalFilename();
		}catch (IOException e){
			throw new RuntimeException(e);
		}
		
//		return baseFolder +"/" + file.getOriginalFilename();
	}
	
}

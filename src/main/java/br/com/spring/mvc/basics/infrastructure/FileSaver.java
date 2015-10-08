package br.com.spring.mvc.basics.infrastructure;

import org.springframework.web.multipart.MultipartFile;

/**
 * Class responsible for file saving.
 * @author mushira4
 */
public interface FileSaver {

	/**
	 * Write the received file into a file
	 * @param baseFolder
	 * @param file
	 * @return
	 */
	public String write(String baseFolder, MultipartFile file);
	
}

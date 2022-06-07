package com.poly.utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileUtils {

	@Autowired
	private ServletContext context;

	public String uploadFile(MultipartFile uploadedFile) {
		String path = context.getRealPath("/upload");
		File file = new File(path);
		String fileName = "";

		if (uploadedFile.isEmpty()) {
			fileName = "no-image.jpg";
		}

		if (!file.exists()) {
			file.mkdirs();
		}

		try {
			fileName = uploadedFile.getOriginalFilename();
			File finalFile = new File(file.getAbsoluteFile() + File.separator + fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalFile));

			stream.write(uploadedFile.getBytes());
			stream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

}

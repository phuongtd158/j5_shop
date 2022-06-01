package com.poly.utilities;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileUtils {
    public File handleUploadFile(MultipartFile uploadedFile) {
        String folderPath = "C:\\Users\\ADMIN\\eclipse-workspace\\Assignment_Java5\\src\\main\\webapp\\upload";
        File uploadFolder = new File(folderPath);

        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        File savedFile = null;
        try { 
            String filename = uploadedFile.getOriginalFilename();

            savedFile = new File(uploadFolder, filename);
            uploadedFile.transferTo(savedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedFile;
    }
}

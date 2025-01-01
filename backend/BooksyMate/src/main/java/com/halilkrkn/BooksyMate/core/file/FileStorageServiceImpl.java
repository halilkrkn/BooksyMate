package com.halilkrkn.BooksyMate.core.file;

import com.halilkrkn.BooksyMate.entities.book.Book;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${application.file.upload.photos-output-path}")
    private String fileUploadPath;

    @Override
    public String saveFile(
            @NonNull MultipartFile sourceFile,
            @NonNull Integer userId) {

        final String fileUploadSubPath = "users" + File.separator + userId;


        return uploadFile(sourceFile, fileUploadSubPath);
    }

    private String uploadFile(
            @NonNull MultipartFile sourceFile,
            @NonNull String fileUploadSubPath) {

        final String finalUploadPath = this.fileUploadPath + File.separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);

        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                log.warn("Failed to crated the target folder");
                return null;
            }
        }

        final String FileExtension = getFileExtension(sourceFile.getOriginalFilename());
        // ./uploads/users/1/123456789.jpg
        String targetFilePath = finalUploadPath + File.separator + System.currentTimeMillis() + "." + FileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, sourceFile.getBytes());
            log.info("File saved to {}", targetFilePath);
            return targetFilePath;
        } catch (Exception e) {
            log.error("Failed to save the file", e);
        }
        return null;
    }

    private String getFileExtension(String fileName) {
        if (fileName != null || fileName.isEmpty()) {
            return "";
        }
        //something.jpg
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }

        // .JPG -> .jpg
        return fileName.substring(lastDotIndex + 1).toLowerCase();

    }
}

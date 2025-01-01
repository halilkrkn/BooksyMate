package com.halilkrkn.BooksyMate.core.file;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

@Slf4j
public class FileUtils {
    public static byte[] readFileFromLocation(String fileUrl) {

        if (StringUtils.isBlank(fileUrl)) {
            return null;
        }

        try {

            Path filePath = new File(fileUrl).toPath();
            return readAllBytes(filePath);

        } catch (IOException e) {
            log.warn("No file found in the location: {}", fileUrl);
        }

        return null;
    }
}

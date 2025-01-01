package com.halilkrkn.BooksyMate.core.file;

import com.halilkrkn.BooksyMate.entities.book.Book;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String saveFile(MultipartFile sourceFile, Integer userId);
}

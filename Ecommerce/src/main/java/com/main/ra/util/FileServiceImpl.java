package com.main.ra.util;

import com.main.ra.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileServiceImpl {
    private final Path root = Paths.get("public/uploads");

    public FileServiceImpl() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new BaseException("", HttpStatus.FORBIDDEN);
        }
    }

    public String save(String fileLocation,MultipartFile file) {
        try {
            Path user = Paths.get(root+fileLocation);
            Files.createDirectories(user);
            Files.copy(file.getInputStream(), user.resolve(Objects.requireNonNull(file.getOriginalFilename())), StandardCopyOption.REPLACE_EXISTING);
            return file.getOriginalFilename();
        } catch (Exception ex) {
            throw new BaseException("", HttpStatus.FORBIDDEN);
        }
    }

    public Resource load(String fileName) {
        try {
            Path filePath = root.resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new Exception("File not found!");
            }
        } catch (Exception ex) {
            throw new BaseException("", HttpStatus.FORBIDDEN);
        }
    }
}

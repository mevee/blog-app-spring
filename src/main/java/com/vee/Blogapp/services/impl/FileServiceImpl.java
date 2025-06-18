package com.vee.Blogapp.services.impl;

import com.vee.Blogapp.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        //unique file name path
        String randomId = UUID.randomUUID().toString();

        assert fileName != null;
        String fileName2 = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
        String filepath = path + File.separator +fileName2;
        //Creating new field if not exist
        File f =new File(path);
        if (!f.exists()){
            f.mkdir();
        }
        //saving file in fileName2 path
        Files.copy(file.getInputStream(), Paths.get(filepath));
        return fileName2;
    }

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        return "";
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        return new FileInputStream(fullPath);
    }
}

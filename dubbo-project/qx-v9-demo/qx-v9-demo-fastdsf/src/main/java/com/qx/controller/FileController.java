package com.qx.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Author: QX_He
 * DATA: 2020/7/31-22:52
 * Description:
 **/
@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @RequestMapping("/upload")
    public String uploadFile() throws FileNotFoundException {
        File file = new File("F:\\JAVA\\Dubbo\\FW\\Project\\GitbubVersion\\Images\\Nova7.jpg");
        String fileName = file.getName();
        String extendName = fileName.substring(fileName.indexOf(".") + 1);
        FileInputStream inputStream = new FileInputStream(file);
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.length(), extendName, null);
        System.out.println(fileName);
        System.out.println(extendName);
        System.out.println(storePath.getGroup());
        System.out.println(storePath.getFullPath());
        System.out.println(storePath.getPath());
        return "add file success";
    }

}

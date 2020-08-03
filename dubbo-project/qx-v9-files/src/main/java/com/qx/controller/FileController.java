package com.qx.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public String uploadFile(MultipartFile file) {

//        StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.length(), extendName, null);
//        System.out.println(fileName);
//        System.out.println(extendName);
//        System.out.println(storePath.getGroup());
//        System.out.println(storePath.getFullPath());
//        System.out.println(storePath.getPath());

        System.out.println("The file`s content is :" + file);
        return "add file success";
    }

}

package com.qx.tempFilecontroller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qingxiu.utills.webresult.MultiUploadFileResult;
import com.qingxiu.utills.webresult.Result;
import com.qingxiu.utills.webresult.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Author: QX_He
 * DATA: 2020/8/1-17:47
 * Description:
 **/
@Controller
@CrossOrigin
@RequestMapping("/file")
public class FileController {


    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Value("${image.server.ip}")
    private String image_server_ip;

    /**
     * Single file upload function
     *
     * @param file
     * @return
     */
    @ResponseBody  // 而不是 @RequestBody
    @RequestMapping("upload")
    public Result<String> uploadFile(MultipartFile file) {
        //1. Get the object of file and pull it to service.
        String fileName = file.getName();
        String extendName = fileName.substring(fileName.indexOf(".") + 1);
        //2. Obtain the return full-path ,then make a result return to front-end
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extendName, null);
            if (true) {
                String fullPath = image_server_ip + storePath.getFullPath();
                System.out.println("Is a right path ?: " + fullPath);
                return new Result<String>(true, StatusCode.OK, "File was stored successfully", fullPath);
            } else {
                String a = new StringBuilder(image_server_ip).append(storePath.getFullPath()).toString();
                return new Result<String>(true, StatusCode.OK, "File was stored successfully", a);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(false, StatusCode.ERROR, "File was stored abortively");
        }
    }

    @ResponseBody  // 而不是 @RequestBody
    @RequestMapping("/multiFileUpload")
    public MultiUploadFileResult uploadFile(MultipartFile[] files) throws Exception {
        if (files == null) {
            throw new Exception("No album ,please upload again !");
        }
        MultiUploadFileResult multiUploadFileResult = new MultiUploadFileResult();
        // gather the file`s fullPath respectively
        String[] paths = new String[files.length];
        // iterate
        for (int i = 0; i < files.length; i++) {
            //1. Get the object of file and pull it to service.
            String fileName = files[i].getName();
            String extendName = fileName.substring(fileName.indexOf(".") + 1);
            //2. Obtain the return full-path ,then make a result return to front-end
            try {
                StorePath storePath = fastFileStorageClient.uploadFile(files[i].getInputStream(), files[i].getSize(), extendName, null);
                String fullPath = new StringBuilder(image_server_ip).append(storePath.getFullPath()).toString();
                paths[i] = fullPath;
            } catch (IOException e) {
                e.printStackTrace();
                multiUploadFileResult.setErrno("1");
                return multiUploadFileResult;
            }
        }
        multiUploadFileResult.setData(paths);
        multiUploadFileResult.setErrno("0");
        return multiUploadFileResult;
    }
}


package com.example.webcustomer.controller;

import com.example.webcustomer.util.IOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/file/")
public class FileController {
    /**
     * 处理文件上传
     * @param file
     */
    @PostMapping("upload")
    public  String  upload(MultipartFile file){
        File localfile = new File("/file/name");
        try {
            //将文件上传到本地路径
            file.transferTo(localfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileInfo = "";
        fileInfo += file.getName()+file.getOriginalFilename()+file.getContentType();
        return fileInfo;
    }

    /**
     * 文件的下载
     * @param fileName
     * @param request
     * @param response
     */
    @GetMapping("download/{fileName}")
    public void download(@PathVariable String fileName , HttpServletRequest request, HttpServletResponse response){
        /*String filePath = "D:/temp/upload/" + fileName;
        File file = new File(filePath);
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();
        ){
            response.setContentType("application/x-download");
            //指定文件的名称
            response.addHeader("Content-Disposition","attachment;filename=" + fileName);
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        IOUtil.downloadFile(fileName,response);
    }
}

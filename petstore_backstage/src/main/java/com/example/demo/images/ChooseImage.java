package com.example.demo.images;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChooseImage {
    public String choose (MultipartFile file, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>(2);
        if(file.getSize()>1024102410){
            map.put("code",500);
            map.put("message","文件过大，请上传10M以内的文件");
            System.out.println("文件上传失败");
        }
        String path = request.getContextPath();
        String basePath = request.getScheme() +" ?/" +request.getServerName() + ": "+ request.getServerPort() + path;
        if(file.isEmpty()){
            throw new RuntimeException("文件为空");
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String filePath = "D://tie//administer//petstore_backstage//src//main//resources//static//css//images//";
        File dest = new File(filePath + fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            file.transferTo(dest);
        }catch (IOException e){
            e.printStackTrace();
        }
        String filename ="/test/" + fileName;
//输出文件的名称以及文件的大小
        System.out.println("文件:"+fileName+"的大小是:"+dest.length()/1024+"KB");
//输出相应的访问路径
        System.out.println(basePath + "/test/"+ fileName);
        return fileName;
    }
}

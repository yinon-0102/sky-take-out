package com.sky.controller.admin;


import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequestMapping("/admin/common")
@RestController
public class CommonController {


    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传:{}", file.getOriginalFilename());
        //将文件交给OSS管理
        try {
            String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
            log.info("文件上传OSS,url:{}", url);
            return Result.success(url);
        } catch (IOException e) {
            log.error("文件上传失败: {}", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}

package com.liy.service;

import com.liy.domain.ResponseResult;
import com.liy.domain.entity.SystemFileConfig;
import org.springframework.web.multipart.MultipartFile;

public interface CloudOssService {

    /**
     * 更新文件存储策略
     */
    void updateFileUploadWay(SystemFileConfig systemFileConfig);

    /**
     * 上传
     * @param file 文件
     * @return
     */
    ResponseResult upload(MultipartFile file);

    /**
     * 批量删除文件
     * @param key 文件名
     * @return
     */
    ResponseResult delBatchFile(String ...key);
}

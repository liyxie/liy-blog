package com.liy.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.liy.common.FileConstants;
import com.liy.domain.ResponseResult;

import com.liy.domain.entity.SystemFileConfig;
import com.liy.service.CloudOssService;
import com.liy.service.SystemConfigService;
import com.liy.strategy.context.FileUploadStrategyContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CloudOssServiceImpl implements CloudOssService {

    private final FileUploadStrategyContext fileUploadStrategyContext;

    private SystemFileConfig strategy;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @Override
    public ResponseResult upload(MultipartFile file) {
        if (file.getSize() > 1024 * 1024 * 10) {
            return ResponseResult.error("文件大小不能大于10M");
        }
        //获取文件后缀
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return ResponseResult.error("请选择jpg,jpeg,gif,png格式的图片");
        }
        String key = fileUploadStrategyContext.executeFileUploadStrategy(strategy, FileConstants.System_Photo, file);
        return ResponseResult.success(key);
    }


    /**
     * 删除文件
     *
     * @param key
     * @return
     */
    @Override
    public ResponseResult delBatchFile(String... key) {
        Boolean isSuccess = fileUploadStrategyContext.executeDeleteFileStrategy(strategy, key);
        if (!isSuccess) {
            return ResponseResult.error("删除文件失败");
        }
        return ResponseResult.success();
    }

    @Override
    public void updateFileUploadWay(SystemFileConfig systemFileConfig) {
        strategy = systemFileConfig;
    }

}

package com.liy.strategy;

import com.liy.config.file.FileConfig;
import com.liy.domain.entity.SystemFileConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传策略
 */
public interface FileUploadStrategy {

    FileUploadStrategy updateConfig(SystemFileConfig systemFileConfig);

    String fileUpload(String path, MultipartFile file);

    Boolean deleteFile(String ...fileName);
}

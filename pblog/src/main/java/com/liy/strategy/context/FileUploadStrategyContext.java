package com.liy.strategy.context;

import com.liy.common.FileConstants;
import com.liy.domain.entity.SystemFileConfig;
import com.liy.strategy.FileUploadStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @apiNote 文件存储策略上下文
 */
@Service
@RequiredArgsConstructor
public class FileUploadStrategyContext {

    private final Map<String, FileUploadStrategy> fileUploadStrategyMap;


    /**
     * 执行文件上传策略
     *
     * @param file 文件对象
     * @return {@link String} 文件名
     */
    public String executeFileUploadStrategy(SystemFileConfig systemFileConfig, String path, MultipartFile file) {
        return fileUploadStrategyMap.get(systemFileConfig.getStrategy()).updateConfig(systemFileConfig).fileUpload(path, file);
    }

    public String executeFileUploadStrategy(SystemFileConfig systemFileConfig,MultipartFile file) {
        return executeFileUploadStrategy(systemFileConfig, FileConstants.Common, file);
    }

    /**
     * 删除文件策略
     * @param SystemFileConfig
     * @param key
     * @return
     */
    public Boolean executeDeleteFileStrategy(SystemFileConfig systemFileConfig, String ...key) {
         return fileUploadStrategyMap.get(systemFileConfig.getStrategy()).deleteFile(key);
    }
}

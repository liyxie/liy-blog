package com.liy.strategy.imp.file;

import com.liy.config.file.FileConfig;
import com.liy.config.file.LocalConfig;
import com.liy.domain.entity.SystemFileConfig;
import com.liy.strategy.FileUploadStrategy;
import com.liy.util.FileUtils;
import com.liy.util.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service("localUploadStrategyImpl")
@RequiredArgsConstructor
public class LocalUploadStrategyImpl implements FileUploadStrategy {

    private final LocalConfig localConfig;

    @Override
    public FileUploadStrategy updateConfig(SystemFileConfig systemFileConfig) {
        localConfig.initOrUpdate(systemFileConfig);
        return this;
    }

    @Override
    public String fileUpload(String path, MultipartFile file) {
        String suffix = null;

        String savePath = localConfig.getLocalFileUrl();
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        //通过UUID生成唯一文件名
        String filename = UUIDUtils.getUuid() + "." + suffix;
        try {
            suffix = FileUtils.getExtension(file.getInputStream());
            //将文件保存指定目录
            file.transferTo(new File(savePath + path + filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回文件名称
        return localConfig.getLocalFileUrl() + filename;
    }

    @Override
    public Boolean deleteFile(String... fileName) {
        return false;
    }
}

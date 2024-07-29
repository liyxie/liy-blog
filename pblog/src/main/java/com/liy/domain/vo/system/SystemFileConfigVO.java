package com.liy.domain.vo.system;



import com.baomidou.mybatisplus.annotation.TableName;
import com.liy.domain.entity.SystemFileConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SystemFileConfigVO对象", description = "系统文件存储配置")
public class SystemFileConfigVO implements Serializable {

  @ApiModelProperty(value = "id")
  private long id;
  @ApiModelProperty(value = "存储类型名称")
  private String typeName;
  @ApiModelProperty(value = "存储链接")
  private String url;
  @ApiModelProperty(value = "存储区域")
  private String area;
  @ApiModelProperty(value = "存储空间/桶")
  private String bucket;
  @ApiModelProperty(value = "公钥/腾讯secretId")
  private String accessKey;
  @ApiModelProperty(value = "私钥")
  private String secretKey;
  @ApiModelProperty(value = "存储主路径")
  private String path;

  public static SystemFileConfigVO poToVo(SystemFileConfig systemFileConfig) {
    if (systemFileConfig == null) {
      return null;
    }
    SystemFileConfigVO systemFileConfigVO = new SystemFileConfigVO();
    systemFileConfigVO.setId(systemFileConfig.getId());
    systemFileConfigVO.setTypeName(systemFileConfig.getTypeName());
    systemFileConfigVO.setUrl(systemFileConfig.getUrl());
    systemFileConfigVO.setArea(systemFileConfig.getArea());
    systemFileConfigVO.setBucket(systemFileConfig.getBucket());
    systemFileConfigVO.setAccessKey(systemFileConfig.getAccessKey());
    systemFileConfigVO.setPath(systemFileConfig.getPath());
    return systemFileConfigVO;
  }


  public static SystemFileConfig voToPo(SystemFileConfigVO systemFileConfigVO) {
    if (systemFileConfigVO == null) {
      return null;
    }
    SystemFileConfig systemFileConfig = new SystemFileConfig();
    systemFileConfig.setId(systemFileConfigVO.getId());
    systemFileConfig.setTypeName(systemFileConfigVO.getTypeName());
    systemFileConfig.setUrl(systemFileConfigVO.getUrl());
    systemFileConfig.setArea(systemFileConfigVO.getArea());
    systemFileConfig.setBucket(systemFileConfigVO.getBucket());
    systemFileConfig.setAccessKey(systemFileConfigVO.getAccessKey());
    systemFileConfig.setSecretKey(systemFileConfigVO.getSecretKey());
    systemFileConfig.setPath(systemFileConfigVO.getPath());
    return systemFileConfig;
  }
}

package com.liy.domain.entity;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("b_system_file_config")
public class SystemFileConfig {

  @TableField(value = "id")
  private long id;
  @TableField(value = "type_name")
  private String typeName;
  @TableField(value = "url")
  private String url;
  @TableField(value = "area")
  private String area;
  @TableField(value = "bucket")
  private String bucket;
  @TableField(value = "access_key")
  private String accessKey;
  @TableField(value = "secret_key")
  private String secretKey;
  @TableField(value = "path")
  private String path;
  @TableField(value = "strategy")
  private String strategy;


}

package com.liy.domain.entity;


public class BSystemConfig {

  @ApiModelProperty(value = "主键")
  private long id;
  @ApiModelProperty(value = "七牛云公钥")
  private String qiNiuAccessKey;
  @ApiModelProperty(value = "七牛云私钥")
  private String qiNiuSecretKey;
  @ApiModelProperty(value = "七牛云存储区域 华东（z0），华北(z1)，华南(z2)，北美(na0)，东南亚(as0)")
  private String qiNiuArea;
  @ApiModelProperty(value = "七牛云上传空间")
  private String qiNiuBucket;
  @ApiModelProperty(value = "七牛云域名前缀：http://images.moguit.cn")
  private String qiNiuPictureBaseUrl;
  @ApiModelProperty(value = "文件上传七牛云(0:否， 1:是)")
  private String uploadQiNiu;
  @ApiModelProperty(value = "是否开启注册用户邮件激活(0:否， 1:是)")
  private String openEmailActivate;
  @ApiModelProperty(value = "是否开启邮件通知(0:否， 1:是)")
  private String startEmailNotification;
  @ApiModelProperty(value = "创建时间")
  private java.sql.Timestamp createTime;
  @ApiModelProperty(value = "更新时间")
  private java.sql.Timestamp updateTime;
  @ApiModelProperty(value = "是否开启仪表盘通知(0:否， 1:是)")
  private String openDashboardNotification;
  @ApiModelProperty(value = "仪表盘通知【用于首次登录弹框】MD")
  private String dashboardNotificationMd;
  @ApiModelProperty(value = "仪表盘通知【用于首次登录弹框】")
  private String dashboardNotification;
  @ApiModelProperty(value = "搜索模式【0:SQL搜索 、1：全文检索】")
  private long searchModel;
  @ApiModelProperty(value = "邮箱地址")
  private String emailHost;
  @ApiModelProperty(value = "邮箱发件人")
  private String emailUsername;
  @ApiModelProperty(value = "邮箱授权码")
  private String emailPassword;
  @ApiModelProperty(value = "邮箱发送端口")
  private long emailPort;
  @ApiModelProperty(value = "启用邮箱发送")
  private long openEmail;
  @ApiModelProperty(value = "本地文件地址")
  private String localFileUrl;
  @ApiModelProperty(value = "文件上传方式 1:本地 2：七牛云；3：阿里云；4：腾讯云")
  private long fileUploadWay;
  @ApiModelProperty(value = "阿里云ak")
  private String aliYunAccessKey;
  @ApiModelProperty(value = "阿里云sk")
  private String aliYunSecretKey;
  @ApiModelProperty(value = "阿里云存储桶名")
  private String aliYunBucket;
  @ApiModelProperty(value = "阿里云Endpoint")
  private String aliYunEndpoint;
  @ApiModelProperty(value = "腾讯云链接")
  private String txYunUrl;
  @ApiModelProperty(value = "腾讯云存储桶名")
  private String txYunBucket;
  @ApiModelProperty(value = "腾讯云存储所属地域")
  private String txYunAddr;
  @ApiModelProperty(value = "腾讯云secretKey")
  private String txYunSecretKey;
  @ApiModelProperty(value = "腾讯云accessKey")
  private String txYunAccessKey;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getQiNiuAccessKey() {
    return qiNiuAccessKey;
  }

  public void setQiNiuAccessKey(String qiNiuAccessKey) {
    this.qiNiuAccessKey = qiNiuAccessKey;
  }


  public String getQiNiuSecretKey() {
    return qiNiuSecretKey;
  }

  public void setQiNiuSecretKey(String qiNiuSecretKey) {
    this.qiNiuSecretKey = qiNiuSecretKey;
  }


  public String getQiNiuArea() {
    return qiNiuArea;
  }

  public void setQiNiuArea(String qiNiuArea) {
    this.qiNiuArea = qiNiuArea;
  }


  public String getQiNiuBucket() {
    return qiNiuBucket;
  }

  public void setQiNiuBucket(String qiNiuBucket) {
    this.qiNiuBucket = qiNiuBucket;
  }


  public String getQiNiuPictureBaseUrl() {
    return qiNiuPictureBaseUrl;
  }

  public void setQiNiuPictureBaseUrl(String qiNiuPictureBaseUrl) {
    this.qiNiuPictureBaseUrl = qiNiuPictureBaseUrl;
  }


  public String getUploadQiNiu() {
    return uploadQiNiu;
  }

  public void setUploadQiNiu(String uploadQiNiu) {
    this.uploadQiNiu = uploadQiNiu;
  }


  public String getOpenEmailActivate() {
    return openEmailActivate;
  }

  public void setOpenEmailActivate(String openEmailActivate) {
    this.openEmailActivate = openEmailActivate;
  }


  public String getStartEmailNotification() {
    return startEmailNotification;
  }

  public void setStartEmailNotification(String startEmailNotification) {
    this.startEmailNotification = startEmailNotification;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public String getOpenDashboardNotification() {
    return openDashboardNotification;
  }

  public void setOpenDashboardNotification(String openDashboardNotification) {
    this.openDashboardNotification = openDashboardNotification;
  }


  public String getDashboardNotificationMd() {
    return dashboardNotificationMd;
  }

  public void setDashboardNotificationMd(String dashboardNotificationMd) {
    this.dashboardNotificationMd = dashboardNotificationMd;
  }


  public String getDashboardNotification() {
    return dashboardNotification;
  }

  public void setDashboardNotification(String dashboardNotification) {
    this.dashboardNotification = dashboardNotification;
  }


  public long getSearchModel() {
    return searchModel;
  }

  public void setSearchModel(long searchModel) {
    this.searchModel = searchModel;
  }


  public String getEmailHost() {
    return emailHost;
  }

  public void setEmailHost(String emailHost) {
    this.emailHost = emailHost;
  }


  public String getEmailUsername() {
    return emailUsername;
  }

  public void setEmailUsername(String emailUsername) {
    this.emailUsername = emailUsername;
  }


  public String getEmailPassword() {
    return emailPassword;
  }

  public void setEmailPassword(String emailPassword) {
    this.emailPassword = emailPassword;
  }


  public long getEmailPort() {
    return emailPort;
  }

  public void setEmailPort(long emailPort) {
    this.emailPort = emailPort;
  }


  public long getOpenEmail() {
    return openEmail;
  }

  public void setOpenEmail(long openEmail) {
    this.openEmail = openEmail;
  }


  public String getLocalFileUrl() {
    return localFileUrl;
  }

  public void setLocalFileUrl(String localFileUrl) {
    this.localFileUrl = localFileUrl;
  }


  public long getFileUploadWay() {
    return fileUploadWay;
  }

  public void setFileUploadWay(long fileUploadWay) {
    this.fileUploadWay = fileUploadWay;
  }


  public String getAliYunAccessKey() {
    return aliYunAccessKey;
  }

  public void setAliYunAccessKey(String aliYunAccessKey) {
    this.aliYunAccessKey = aliYunAccessKey;
  }


  public String getAliYunSecretKey() {
    return aliYunSecretKey;
  }

  public void setAliYunSecretKey(String aliYunSecretKey) {
    this.aliYunSecretKey = aliYunSecretKey;
  }


  public String getAliYunBucket() {
    return aliYunBucket;
  }

  public void setAliYunBucket(String aliYunBucket) {
    this.aliYunBucket = aliYunBucket;
  }


  public String getAliYunEndpoint() {
    return aliYunEndpoint;
  }

  public void setAliYunEndpoint(String aliYunEndpoint) {
    this.aliYunEndpoint = aliYunEndpoint;
  }


  public String getTxYunUrl() {
    return txYunUrl;
  }

  public void setTxYunUrl(String txYunUrl) {
    this.txYunUrl = txYunUrl;
  }


  public String getTxYunBucket() {
    return txYunBucket;
  }

  public void setTxYunBucket(String txYunBucket) {
    this.txYunBucket = txYunBucket;
  }


  public String getTxYunAddr() {
    return txYunAddr;
  }

  public void setTxYunAddr(String txYunAddr) {
    this.txYunAddr = txYunAddr;
  }


  public String getTxYunSecretKey() {
    return txYunSecretKey;
  }

  public void setTxYunSecretKey(String txYunSecretKey) {
    this.txYunSecretKey = txYunSecretKey;
  }


  public String getTxYunAccessKey() {
    return txYunAccessKey;
  }

  public void setTxYunAccessKey(String txYunAccessKey) {
    this.txYunAccessKey = txYunAccessKey;
  }

}

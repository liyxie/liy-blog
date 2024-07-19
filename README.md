## 本地部署

### 数据库

创建MySql数据库,执行sql创表

### 后端

idea打开，

修改数据库链接、ES链接、Redis链接

```yaml
spring:
  datasource:
    url: jdbc:mysql://124.71.***.***:3306/数据库名?characterEncoding=UTF-8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&tinyInt1isBit=false&allowPublicKeyRetrieval=true
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: 用户名
    password: 密码
```

```yaml
elasticsearch:
  rest:
    uris: 124.71.***.***:9200

redis:
  host: 43.139.94.154
  port: 6379
  timeout: 3000
  password: 密码
```

第三方登录配置可改可不改，看是否要用

```yaml
qq:
  app-id: 123456789
  check-token-url: https://graph.qq.com/oauth2.0/me?access_token={access_token}
  user-info-url: https://graph.qq.com/user/get_user_info?openid={openid}&access_token={access_token}&oauth_consumer_key={oauth_consumer_key}
weibo:
  app-id: 132456
  app-secret: a380fef0743e38cad12125902b204fab
  grant-type: authorization_code
  redirect-url: http://www.codescholar.cn/rulin/callback/weibo
  access-token-url: https://api.weibo.com/oauth2/access_token
  user-info-url: https://api.weibo.com/2/users/show.json?uid={uid}&access_token={access_token}
gitee:
  app-id: d029
  app-secret: e94dfa5
  grant-type: authorization_code
  redirect-url: http://www.codescholar.cn/rulin/callback/gitee
  access-token-url: https://gitee.com/oauth/token
  user-info-url: https://gitee.com/api/v5/user?access_token={access_token}
baidu:
  url: http://data.zz.baidu.com/urls?site=www.codescholar.cn&token=XKeIRVpUvz5KuKBG
file:
  upload-folder: /usr/local/pblog/file/
```

### 前端

基本不用改，要是后端接口改了就改下请求接口

VScode打开，安装依赖（我用cnpm，看个人）

web和admin都是相同过程

```shell
cnpm install

cnpm run serve
```

### 注意问题

- node-sass依赖问题

```shell
WARN node unsupported "node@v20.14.0" is incompatible with @vue/cli-plugin-unit-jest@4.4.4 › @vue/cli-shared-utils@4.5.1/ [43/47] Installing tslib@2.3.0
[npminstall:runscript:error] node-sass@^6.0.1 run postinstall node scripts/build.js error: Error: Command failed with exit code 1: node scripts/build.js
    at makeError (C:\Users\贤\AppData\Roaming\npm\node_modules\cnpm\node_modules\execa\lib\error.js:60:11)
    at handlePromise (C:\Users\贤\AppData\Roaming\npm\node_modules\cnpm\node_modules\execa\index.js:118:26)
    at process.processTicksAndRejections (node:internal/process/task_queues:95:5)
    at async exports.runScript (C:\Users\贤\AppData\Roaming\npm\node_modules\cnpm\node_modules\npminstall\lib\utils.js:261:12)
    at async runLifecycleScripts (C:\Users\贤\AppData\Roaming\npm\node_modules\cnpm\node_modules\npminstall\lib\lifecycle_scripts.js:66:7)
    at async _install (C:\Users\贤\AppData\Roaming\npm\node_modules\cnpm\node_modules\npminstall\lib\install_package.js:321:5)
    at async install (C:\Users\贤\AppData\Roaming\npm\node_modules\cnpm\node_modules\npminstall\lib\install_package.js:23:12)
    at async _installOne (C:\Users\贤\AppData\Roaming\npm\node_modules\cnpm\node_modules\npminstall\lib\local_install.js:215:15)
```

通常是node与node-sass版本不匹配问题

node -v查看本地node版本，package.json查看node-sass版本

官网查看版本对应关系 [node-sass - npm (npmjs.com)](https://www.npmjs.com/package/node-sass)

可单独下载对应的sass，或直接更换node版本解决



-  Cannot find module '@vue/babel-preset-app'

 Cannot find module '@vue/babel-preset-app'错误

多几次npm install，不行删除node-modules再来，注意要是之前有node-sass问题，先单独安装sass再install



- These dependencies were not found:* core-js/modules/es.array.push.js

缺core-js模块

尝试单独安装core-js，不行再删除modules文件，先单独安装，再install



Node与npm版本对应关系 [Node.js — Node.js 版本 (nodejs.org)](https://nodejs.org/zh-cn/about/previous-releases)



# nodejs版本管理nvm

win用nvm，Linux可以用n

## nvm

删除原有nodejs

下载 setup.zip或者exe [Releases · coreybutler/nvm-windows (github.com)](https://github.com/coreybutler/nvm-windows/releases)

解压安装，第一个选nvm安装位置，第二个nodejs安装位置（最好选原位置，不用再配环境）

cmd用nvm -v测试

设置淘宝镜像（可不用，原速度感觉还行）

nvm安装目录下打开setting.txt，直接加

```json
node_mirror: https://npm.taobao.org/mirrors/node/
node_mirror: https://npm.taobao.org/mirrors/npm/
```

使用方式

```shell
nvm install v16.20.2 //安装16.20.2版
nvm uninstall v16.20.0 //卸载
nvm ls // 查看本地全部node版本
nvm list zvailable // 显示可安装所有node版本
nvm use 版本 // 切换版本
```


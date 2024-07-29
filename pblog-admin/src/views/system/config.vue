<template>
  <div class="app-container" v-if="ifShow">
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick">

      <el-tab-pane label="系统配置" name = '0'>
        <span slot="label"><i class="el-icon-edit" /> 系统配置</span>
        <el-form style="margin-left: 20px;" label-position="left" label-width="140px">

          <aside>
            通过开关选择博客编辑时的文本编辑器，以及文件显示方式<br>
          </aside>

          <!--当有新的反馈，友链申请时进行通知，首先需要在系统管理处设置接收通知的邮箱 -->
          <el-form-item label="网站消息邮件通知">
            <el-radio v-for="item in openDictList" :key="item.value" v-model="form.startEmailNotification"
              :label="item.value" border size="medium">{{ item.label }}
            </el-radio>
          </el-form-item>

          <el-form-item label="启用邮箱发送功能">
            <el-radio v-for="item in yesNoDictList" :key="parseInt(item.value)" v-model="form.openEmail"
              :label="parseInt(item.value)" border size="medium">{{ item.label }}
            </el-radio>
          </el-form-item>

          <!-- 用于控制用户是否需要通过邮箱验证，完成认证-->
          <el-form-item label="注册用户邮件激活">
            <el-radio v-for="item in openDictList" :key="item.value" v-model="form.openEmailActivate" :label="item.value"
              border size="medium">{{ item.label }}
            </el-radio>
          </el-form-item>

          <!-- 用于控制文件的上传方式-->
          <el-form-item label="图片上传方式">
            <el-radio v-for="(item, index) in fileUploadList" :key="index" v-model="form.fileUploadWay" :label="index"
              border size="medium">{{ item }}
            </el-radio>
          </el-form-item>

          <!-- 仪表盘弹框通知，在用户登录后台的时候会出现，可以手动关闭 -->
          <el-form-item label="仪表盘弹框通知">
            <el-radio v-for="item in openDictList" :key="item.value" v-model="form.openDashboardNotification"
              :label="item.value" border size="medium">{{ item.label }}
            </el-radio>
          </el-form-item>

          <!-- 搜索模式-->
          <el-form-item>
            <template slot="label">
              文章搜索模式
              <el-popover placement="top-start" width="200" trigger="hover" content="用于控制门户搜索功能使用SQL方式，还是全文检索。">
                <i slot="reference" style="cursor: pointer;margin-left: 2px" class="el-icon-question" />
              </el-popover>
            </template>
            <el-radio v-for="item in searchModelDictList" :key="parseInt(item.value)" v-model="form.searchModel"
              :label="parseInt(item.value)" border size="medium">{{ item.label }}
            </el-radio>
          </el-form-item>

          <el-form-item>
            <el-button v-if="canUpdate" type="primary" @click="submitForm()">保 存</el-button>
          </el-form-item>

        </el-form>
      </el-tab-pane>

      
      <!-- <div><el-tab-pane label="本地文件存储" name="1">
        <span slot="label">
          <i class="el-icon-date" /> 本地文件存储
        </span>

        <el-form ref="form" style="margin-left: 20px;" label-position="left" :model="form" label-width="120px"
          :rules="rules">
          <aside>
            使用IO流将文件存储本地磁盘中
          </aside>

          <el-form-item label="本地文件路径" prop="localFileUrl">
            <el-input v-model="form.systemFileConfigVOList[0].path" auto-complete="new-password" style="width: 400px" />
          </el-form-item>
          <el-form-item>
            <el-button v-if="canUpdate" type="primary" @click="submitForm()">保 存</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane></div> -->

        <el-tab-pane v-for="(item, index) in form.systemFileConfigVOList" :key="item.id" :label="item.typeName" :name= "(item.id+1).toString()">
          <span slot="label">
            <i class="el-icon-date" /> {{ item.typeName }}
          </span>

          <el-form ref="form" style="margin-left: 20px;" label-position="left" :model="form" label-width="120px"
            :rules="rules">
            <aside>
              使用 <a href="#">{{ item.typeName }}</a> 构建对象存储服务<br>
            </aside>

            <el-form-item label="文件存储路径">
              <el-input v-model="item.path" auto-complete="new-password" style="width: 400px" />
            </el-form-item>

            <!-- 本地存储不显示 -->
            <div v-if="item.id > 0">

              <el-form-item :label="item.typeName + '域名'" >
                <el-input v-model="item.url" auto-complete="new-password"
                  style="width: 400px" />
              </el-form-item>

              <el-form-item :label="item.typeName + '公钥/secretId'">
                <el-input v-model="item.accessKey" auto-complete="new-password"
                  style="width: 400px" />
              </el-form-item>

              <el-form-item :label=" item.typeName + '私钥/secretKey'">
                <el-input v-model="item.secretKey" type="password" auto-complete="new-password"
                  style="width: 400px"/>
              </el-form-item>

              <el-form-item :label="item.typeName + '上传空间/桶'">
                <el-input v-model="item.bucket" style="width: 400px" />
              </el-form-item>

              <el-form-item :label="item.typeName + '存储区域/地区'">

                <el-select v-if="item.id === 1" v-model="item.area" placeholder="请选择存储区域" clearable>
                  <el-option v-for="item in areaDictList" :key="item.value" :label="item.name" :value="item.value" />
                </el-select>

                <el-select v-if="item.id === 3" v-model="item.area" placeholder="请选择存储区域" clearable>
                  <el-option v-for="item in txareaDictList" :key="item.name" :label="item.name" :value="item.name" />
                </el-select>

              </el-form-item>

            </div>

            <el-form-item>
              <el-button v-if="canUpdate" type="primary" @click="submitForm()">保 存</el-button>
            </el-form-item>

          </el-form>
        </el-tab-pane>




      <!-- 阿里云 -->
      <!-- <el-tab-pane label="阿里云对象存储" name="three">
        <span slot="label">
          <i class="el-icon-date" /> 阿里云对象存储
        </span>

        <el-form
          ref="form"
          style="margin-left: 20px;"
          label-position="left"
          :model="form"
          label-width="120px"
          :rules="rules"
        >
          <aside>
            使用 <a href="#">阿里云</a> 构建对象存储服务<br>
          </aside>

          <el-form-item label="阿里云文件域名" prop="qiNiuPictureBaseUrl">
            <el-input v-model="form.qiNiuPictureBaseUrl" auto-complete="new-password" style="width: 400px" />
          </el-form-item>

          <el-form-item label="阿里云公钥">
            <el-input v-model="form.aliYunAccessKey" auto-complete="new-password" style="width: 400px" />
          </el-form-item>

          <el-form-item label="阿里云私钥">
            <el-input
              v-model="form.aliYunSecretKey"
              type="password"
              auto-complete="new-password"
              style="width: 400px"
            />
          </el-form-item> 

          <el-form-item label="上传空间">
            <el-input v-model="form.aliYunBucket" style="width: 400px" />
          </el-form-item>

          <el-form-item label="存储区域">
            <el-select v-model="form.qiNiuArea" placeholder="请选择存储区域" clearable>
              <el-option
                v-for="item in areaDictList"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button v-if="canUpdate" type="primary" @click="submitForm()">保 存</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>-->

      <!-- <el-tab-pane label="腾讯云对象存储" name="four">
        <span slot="label">
          <i class="el-icon-date" /> 腾讯云对象存储
        </span>

        <el-form ref="form" style="margin-left: 20px;" label-position="left" :model="form" label-width="120px"
          :rules="rules">
          <aside>
            使用 <a href="#">腾讯云</a> 构建对象存储服务<br>
          </aside>

          <el-form-item label="腾讯云文件域名" prop="qiNiuPictureBaseUrl">
            <el-input v-model="form.systemFileConfigVOList[3].url" auto-complete="new-password" style="width: 400px" />
          </el-form-item>

          <el-form-item label="腾讯云SecretId">
            <el-input v-model="form.systemFileConfigVOList[3].accessKey" auto-complete="new-password"
              style="width: 400px" />
          </el-form-item>

          <el-form-item label="腾讯云SecretKey">
            <el-input v-model="form.systemFileConfigVOList[3].secretKey" type="password" auto-complete="new-password"
              style="width: 400px" />
          </el-form-item>

          <el-form-item label="存储桶名称">
            <el-input v-model="form.systemFileConfigVOList[3].bucket" style="width: 400px" />
          </el-form-item>

          <el-form-item label="存储区域">
            <el-select v-model="form.systemFileConfigVOList[3].area" placeholder="请选择存储区域" clearable>
              <el-option v-for="item in txareaDictList" :key="item.name" :label="item.name" :value="item.name" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button v-if="canUpdate" type="primary" @click="submitForm()">保 存</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane> -->

      <el-tab-pane label="邮箱发送" name='5'>
        <span slot="label">
          <i class="el-icon-date" /> 邮箱发送
        </span>

        <el-form ref="form" style="margin-left: 20px;" label-position="left" :model="form" label-width="120px"
          :rules="rules">
          <aside>
            使用 <a href="#">邮箱</a> 发送通知<br>
          </aside>

          <el-form-item label="邮箱地址" prop="emailHost">
            <el-input v-model="form.emailHost" auto-complete="new-password" style="width: 400px" />
          </el-form-item>

          <el-form-item label="邮箱发送者">
            <el-input v-model="form.emailUsername" auto-complete="new-password" style="width: 400px" />
          </el-form-item>

          <el-form-item label="邮箱授权码">
            <el-input v-model="form.emailPassword" type="emailUsername" auto-complete="new-password"
              style="width: 400px" />
          </el-form-item>

          <el-form-item label="邮箱端口">
            <el-input v-model="form.emailPort" style="width: 400px" />
          </el-form-item>

          <el-form-item>
            <el-button v-if="canUpdate" type="primary" @click="submitForm()">保 存</el-button>
          </el-form-item>

        </el-form>
      </el-tab-pane>

      <el-tab-pane label="仪表盘通知" name='6'>
        <span slot="label"><i class="el-icon-edit" /> 仪表盘通知</span>
        <div class="editor-container">
          <mavon-editor ref="md" v-model="form.dashboardNotificationMd" placeholder="开始编辑...." :subfield="false"
            style="height: 500px" @imgDel="" @change="" @imgAdd="" />
        </div>
        <div style="margin-top: 5px; margin-left: 10px;text-align: center">
          <el-button v-if="canUpdate" type="primary" @click="submitForm()">保 存</el-button>
        </div>
      </el-tab-pane>

    </el-tabs>
  </div>
</template>

<script>
import { getSystemConfig, updateSystemConfig } from '@/api/systemConfig'
import { getDataByDictType } from '@/api/dictData'
import { mapGetters } from 'vuex'
import { hasAuth } from '@/utils/auth'

export default {
  data() {
    return {


      form: {
        systemFileConfigVOList: [],
      },
      ifShow: false,

      index: '0', // 当前激活页
      activeName: '0',
      areaDictList: [
        { name: '华东', value: 'z0' }, { name: '华北', value: 'z1' }, { name: '华南', value: 'z2' },
        { name: '北美', value: 'na0' }, { name: '东南亚', value: 'as0' }
      ], // 七牛云存储区域字典
      txareaDictList: [
        { name: "北京一区" }, { name: "北京" }, { name: "南京" }, { name: "上海" }, { name: "广州" }, { name: "成都" }, { name: "重庆" }
      ], // 腾讯云存储区域字典
      yesNoDictList: [], // 是否字典
      openDictList: [], // 开启关闭字典
      fileUploadList: ['本地', '七牛云', '阿里云', '腾讯云'], // 开启关闭字典
      searchModelDictList: [], // 搜索模式字典列表
      openDictDefaultValue: null,
      rules: {
        qiNiuPictureBaseUrl: [
          { pattern: /^((https|http|ftp|rtsp|mms)?:\/\/)[^\s]+/, message: '请输入正确的域名' }
        ],
        email: [
          { pattern: /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/, message: '请输入正确的邮箱' }
        ]
      }
    }
  },
  watch: {},
  computed: {
    ...mapGetters([
      'pres'
    ]),

    canUpdate: function () {
      return hasAuth(this.pres, '/system/config/update')
    }
  },

  // 
  created() {
    // 获取字典
    this.getDictList()
    // 获取系统配置
    this.getSystemConfigList()
  },

  methods: {

    /**
     * 字典查询
     */
    getDictList: function () {
      const dictTypeList = ['sys_search_model', 'sys_yes_no', 'sys_normal_disable']
      getDataByDictType(dictTypeList).then(response => {
        const dictMap = response.data
        this.yesNoDictList = dictMap.sys_yes_no.list
        this.openDictList = dictMap.sys_normal_disable.list
        this.openDictDefaultValue = dictMap.sys_normal_disable.defaultValue
        this.searchModelDictList = dictMap.sys_search_model.list
      })
    },

    // 换标签
    handleClick: function (tab, event) {
      this.index = tab.index
    },

    // 获得系统配置信息
    getSystemConfigList: function () {
      const that = this
      getSystemConfig().then(response => {
        console.log(response.data.systemFileConfigVOList[1])
        if (response.data) {
          this.form = response.data
        }
        that.ifShow = true
      }).catch(err => {
        console.error(err)
      })
    },

    // 提交修改
    submitForm: function () {
      console.log('开始提交表单')
      this.$refs.form.validate((valid) => {
        if (!valid) {
          console.log('校验出错')
        } else {
          // 获取文本编辑器中的内容【只有在切换到仪表盘通知的时候，才需要获取】
          if (this.index === '4') {
            this.form.dashboardNotification = this.$refs.md.d_render
          }
          console.log(this.form)
          updateSystemConfig(this.form).then(res => {
            this.$message.success(res.message)
          }).catch(err => {
            console.error(err)
          })
        }
      })
    }

  }
}
</script>

<style lang="scss">
aside {
  background: #eef1f6;
  padding: 8px 24px;
  margin-bottom: 20px;
  border-radius: 2px;
  display: block;
  line-height: 32px;
  font-size: 16px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
  color: #2c3e50;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  a {
    color: #337ab7;
    cursor: pointer;

    &:hover {
      color: rgb(32, 160, 255);
    }
  }
}
</style>

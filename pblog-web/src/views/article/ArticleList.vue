<template>
  <div>
    <Loading></Loading>
    <!-- 标签或分类名 -->
    <div class="banner" :style="cover">
      <div class="bgShade">
        <h1 class="banner-title animated fadeInDown">{{ title }} - {{ name }}</h1>
      </div>
    </div>
    <div class="article-list-wrapper">
      <v-row>
        <v-col md="4" cols="12" v-for="item of articleList" :key="item.id">
          <!-- 文章 -->
          <v-card class="animated zoomIn article-item-card">
            <div class="article-item-cover">
              <router-link :to="'/articles/' + item.id">
                <!-- 缩略图 -->
                <v-img
                  class="on-hover"
                  width="100%"
                  height="100%"
                  :src="item.avatar"
                />
              </router-link>
            </div>
            <div class="article-item-info">
              <!-- 文章标题 -->
              <div>
                <router-link :to="'/articles/' + item.id">
                  {{ item.title }}
                </router-link>
              </div>
              <div style="margin-top:0.375rem">
                <!-- 发表时间 -->
                <v-icon size="20">mdi-clock-outline</v-icon>
                {{ item.createTime | date }}
                <!-- 文章分类 -->
                <router-link
                  :to="'/categories/' + item.categoryId"
                  class="float-right"
                >
                  <v-icon>mdi-bookmark</v-icon>
                  {{ item.categoryName }}
                </router-link>
              </div>
            </div>
            <!-- 分割线 -->
            <v-divider></v-divider>
            <!-- 文章标签 -->
            <div class="tag-wrapper">
              <router-link
                v-for="tag of item.tagDTOList"
                :key="tag.id"
                :to="'/tags/' + tag.id"
                class="tag-btn"
              >
                {{ tag.name }}
              </router-link>
            </div>
          </v-card>
        </v-col>
      </v-row>
      <!-- 无限加载 -->
      <infinite-loading @infinite="infiniteHandler">
        <div slot="no-results" />
        <div slot="no-more" />
      </infinite-loading>
    </div>
  </div>
</template>

<script>
  import { queryIdToArticles } from "../../api";
  import Loading from "@/components/loading/loading";

  export default {
    components: {
      Loading
    },
    metaInfo: {
      meta: [{
        name: "keyWords",
        content: "liy-blog,开源博客,  "  //变量或字符串
      }, {
        name: "description",
        content: "一个专注于技术分享的博客平台,大家以共同学习,乐于分享,拥抱开源的价值观进行学习交流"
      }]
    },
    created() {
      const path = this.$route.path;
      if (path.indexOf("/categories") !== -1) {
        this.title = "分类";
      } else {
        this.title = "标签";
      }
    },
    data: function() {
      return {
        current: 1,
        img: process.env.VUE_APP_IMG_API,
        size: 6,
        articleList: [],
        name: "",
        title: ""
      };
    },
    methods: {
      infiniteHandler($state) {
        queryIdToArticles({
          categoryId: this.$route.params.categoryId,
          tagId: this.$route.params.tagId,
          pageNo: this.current,
          pageSize: this.size
        }).then(res => {
          if (res.data.name) {
            this.name = res.data.name;
            document.title = this.title + " - " + this.name;
          }
          if (res.data.records.length) {
            this.current++;
            this.articleList.push(...res.data.records);
            $state.loaded();
          } else {
            $state.complete();
          }

        });
      }
    },
    computed: {
      cover() {
        var cover = "";
        this.$store.state.blogInfo.pageList.forEach(item => {
          if (item.pageLabel == "articleList") {
            cover = item.pageCover;
          }
        });
        return "background: url(" + cover + ") center center / cover no-repeat";
      }
    }
  };
</script>

<style scoped>
    @media (min-width: 760px) {
        .article-list-wrapper {
            max-width: 1106px;
            margin: 370px auto 1rem auto;
        }

        .article-item-card:hover {
            transition: all 0.3s;
            box-shadow: 0 4px 12px 12px rgba(7, 17, 27, 0.15);
        }

        .article-item-card:not(:hover) {
            transition: all 0.3s;
        }

        .article-item-card:hover .on-hover {
            transition: all 0.6s;
            transform: scale(1.1);
        }

        .article-item-card:not(:hover) .on-hover {
            transition: all 0.6s;
        }

        .article-item-info {
            line-height: 1.7;
            padding: 15px 15px 12px 18px;
            font-size: 15px;
        }
    }

    @media (max-width: 759px) {
        .article-list-wrapper {
            margin-top: 230px;
            padding: 0 12px;
        }

        .article-item-info {
            line-height: 1.7;
            padding: 15px 15px 12px 18px;
        }
    }

    .article-item-card {
        border-radius: 8px !important;
        box-shadow: 0 4px 8px 6px rgba(7, 17, 27, 0.06);
    }

    .article-item-card a {
        transition: all 0.3s;
    }

    .article-item-cover {
        height: 220px;
        overflow: hidden;
    }

    .article-item-card a:hover {
        color: #8e8cd8;
    }

    .tag-wrapper {
        padding: 10px 15px 10px 18px;
    }

    .tag-wrapper a {
        color: #fff !important;
    }

    .tag-btn {
        display: inline-block;
        font-size: 0.725rem;
        line-height: 22px;
        height: 22px;
        border-radius: 10px;
        padding: 0 12px !important;
        background: linear-gradient(to right, #bf4643 0%, #6c9d8f 100%);
        opacity: 0.6;
        margin-right: 0.5rem;
    }
</style>

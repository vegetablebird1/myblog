<template>
  <div>

    <div id="footer_body">
      <ul>
        <li>
          <div class="info_logo info1">
            <span>QQ</span>
            <img src="../images/QQLink.png">
          </div>
        </li>
        <li>
          <div class="info_logo info2">
            <span>WeChat</span>
            <img src="../images/WeChatLink.jpg">
          </div>
        </li>
        <li>
          <div class="info_logo info3">
            <span style="text-align: center">联系我:</span>
            <p>邮箱:1446935479@qq.com</p>
            <span><a href="https://github.com/vegetablebird1" title="访问github"><img src="../images/githubLogo.jpg"></a>
              <a href="https://gitee.com/jmZhao00" title="访问gitee"><img id="giteeLogo" src="../images/giteeLogo.jpg"></a>
            </span>
            <p><span>网站累计访问数为:{{views}}</span></p>
          </div>
        </li>
      </ul>
      <div id="bottom_nav"><a href="https://beian.miit.gov.cn">网站备案号:
        粤ICP备2021094595号-1</a></div>
    </div>


  </div>
</template>

<script>
import {getViews} from "@/api/history";

export default {
  name: "Footer",
  data() {
    return {
      views: ''
    }
  },

  methods: {

  },

  created() {
    getViews().then(res => {
      this.views = res.data.data
    })
  }
}
</script>

<style scoped>

#footer_body {
  height: 180px;
  width: 100%;
  margin: 50px 0 auto;
  /*background: black;*/
  opacity: 0.8;
  background-color: #00aae9;
  background-image: linear-gradient(62deg, #00aae9 0%, #80D0C7 50%);

}

ul {
  margin: 0;
  padding: 0;
}

.info_logo {
  width: 100px;
  height: 123px;
}

.info1 {
  position: relative;
  left: 300px;
  top: 30px;
  text-align: center;
}

.info2 {
  position: relative;
  left: 600px;
  top: 30px;
  text-align: center;
}

.info3 {
  width: 210px;
  position: relative;
  left: 900px;
  top: 30px;
}

li {
  list-style: none;
  display: inline-block;
}

#giteeLogo {
  position: relative;
  left: 60px;
}

#bottom_nav {
  position: relative;
  color: black;
}

</style>
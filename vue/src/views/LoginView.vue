<template>
  <div class="all">
    <div class="boxs">
      <div
        style=" width: 100%;height: 100px;font-size: 30px;line-height: 100px;text-align: center;color: black;"
      >
        欢迎登录
      </div>
      <div style="margin-top: 25px; text-align: center; height: 320px">
        <el-form :model="admin">
          <el-form-item>
            <el-input
              v-model="admin.name"
              prefix-icon="el-icon-user"
              style="width: 80%"
              placeholder="请输入用户名"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-input
              v-model="admin.password"
              prefix-icon="el-icon-lock"
              style="width: 80%"
              placeholder="请输入密码"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              style="width: 80%; margin-top: 10px"
              type="primary"
              @click="login()"
              >登录</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return {
      admin: {},
    };
  },
  created() {},
  methods: {
    login() {
      request.post("/admin/", this.admin).then((res) => {
        if (res.code === "0") {
          this.$message({
            message: "登录成功",
            type: "success",
          });
          this.$router.push("/index");
        } else {
          this.$message({
            message: res.msg,
            type: "error",
          });
        }
      });
    },
  },
};
</script>

<style>
.el-menu {
  border-right: none !important;
}

.boxs {
  width: 400px;
  height: 350px;
  margin: 150px auto;
  background-color: white;
  border-radius: 10px;
  /* background-image:url('/src/images/login_background.png'); */
}

.all {
  background-image: url("../images/login_background.png");
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: -1;
}
</style>
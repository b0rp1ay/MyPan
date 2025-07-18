# MyPan01

## 项目简介

MyPan01 是基于 Java Servlet + Thymeleaf + Bootstrap 的简单网盘应用，实现文件上传、文件列表展示、文件下载等功能。

## 功能

* 用户登录/会话管理（基于 HttpSession）
* 文件上传（MultipartConfig）
* 文件列表展示（Thymeleaf 模板渲染）
* 文件下载（Content-Disposition 附件下载）
* 支持断点续传（Accept-Ranges、Range）

## 技术栈

* Servlet 4.0（jakarta.servlet-api）
* Thymeleaf 3.x
* Bootstrap 5.x
* Tomcat 9+

## 环境要求

* JDK 8 及以上
* Apache Tomcat 9 及以上
* IDE 推荐 IntelliJ IDEA
* 手动管理依赖：将 `jakarta.servlet-api.jar`、`thymeleaf.jar`、`thymeleaf-spring.jar` 等拷贝到 `WEB-INF/lib/`

## 项目结构

```
MyPan01/
├─ src/
│  ├─ com/MyPan01/
│      ├─ servlet/
│      │   ├─ BaseServlet.java
│      │   ├─ UploadServlet.java
│      │   └─ DownloadServlet.java
│      └─ model/
│          └─ FileInfo.java
├─ WebContent/
│  ├─ upload/                上传文件存储目录
│  ├─ static/
│  │   ├─ css/
│  │   └─ js/
│  └─ WEB-INF/
│      ├─ templates/         Thymeleaf 模板
│      │   ├─ index.html
│      │   └─ file_list.html
│      └─ lib/               第三方依赖 Jar
└─ README.md
```

## 配置说明

1. 在 `WebContent/upload/` 下创建目录，用于存储上传文件。
2. 修改 `UploadServlet` 和 `DownloadServlet` 中的 `uploadPath` 路径，指向实际存储目录。
3. 确保 `@WebServlet` 注解的映射与前端调用 URL 保持一致。

## 快速启动

1. 使用 IDE 或命令行将项目打包为 `MyPan01.war`。
2. 将 `war` 包部署到 Tomcat 的 `webapps/` 目录，解压后会创建 `MyPan01` 应用。
3. 启动 Tomcat，访问 `http://localhost:8080/MyPan01/`。

## 使用说明

### 1. 登录

* 访问首页 `index.html`，输入用户信息登录（可自行扩展用户校验）。

### 2. 上传文件

* 在“文件上传”表单中选择文件，点击“上传”按钮，文件会保存到 `upload` 目录。

### 3. 查看文件列表

* 上传成功后，跳转至文件列表页面，展示当前所有已上传文件名。

### 4. 下载文件

* 点击文件名链接，即可下载对应文件，浏览器弹出另存为对话框。





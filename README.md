# csti-ns项目相关
- 连接池：
    ```
    HiKariCP
    ```
  

- 支付参考

   ```
   https://github.com/Leibnizhu/AlipayWechatPlatform
   ```

- 后端应用框架

   ```
   vertx-web + springboot + springcloud + jpa
   ```

- 前端应用框架

    ```
    vue
    ```

- 版本管理工具

   ```
   git
   ```

- 项目管理工具

   ```
   gradle 5.2.0
   ```

- 消息队列

   ```
   Kafka
   ```

- 数据库

   ```
   mysql
   ```

- 开发工具

   ```
   idea2018
   ```

- 部署工具

   ```
   docker
   ```




- 接口规范

  - 登录相关(login.csti-ns.com)

    - 登录

      ```
      方式：POST
          URL:/login
          send :
      {
          stu_id:xxx,
          password:xxx
      }
      receive :
      {
          status:200,
          msg:"message"
      }
      ```

    - 注册

      ```
      方式：POST
      URL:/register
      send :
      {
          username:xxx,
          password:xxx,
          tele:xxx,
          grade:xxx,
          stu_id:20150000
      }
      receive :
      {
          status:200,
          msg:"message"
      }
      ```

    - 注销

      ```
      方式：POST
      URL:/logout?stu_id=xxx
      send :
      {
          stu_id:20150000
      }
      receive :
      {
          status:200,
          msg:"message"
      }
      ```

  - 文章相关(mp.csti-ns.com)

    - 发布文章

      ```java
      方式：POST
      URL:/article/add/:stu_id
      send:
      {
          title:"",
          data:"xxx",
          date:xxx,
          tag:xxx
      }
      receive:
      {
          status:200,
          msg:""
      }
      ```

    - 修改文章

      ```java
      方式：PUT
      URL:/article/update/:stu_id/:article_id
      send:
      {
          title:"",
          data:"xxx",
          date:xxx,
          tag:xxx
      }
      receive:
      {
          status:200,
          msg:""
      }
      ```

    - 删除文章

      ```java
      方式：DELETE
      URL:/article/del/:stu_id/:article_id
      send:
      {
          
      }
      receive:
      {
          status:200,
          msg:""
      }
      ```

    - 搜索文章(ES)

      ```java
      方式：GET
      URL:/article/search/:xxx
      send:
      {
          
      }
      receive:
      {
          status:200,
          msg:"",
          data:JSON
      }
      ```

  - 评论相关

    - 增加评论

      ```java
      方式：POST
      URL：/article/comment/:article_id
      send:
      {
          stu_id:xxx,
          comment:"",
          comment_date:xxx
      }
      receive:
      {
          status:200,
          msg:""
      }
      ```

    - 删除评论

      ```java
      方式：DELETE
      URL：/article/comment/:article_id
      send:
      {
      }
      receive:
      {
          status:200,
          msg:""
      }
      ```

  - 账号相关(i.csti-ns.com)

    - 我的关注

      ```java
      方式：POST
      URL：/follow-list
      send:
      {
          stu_id:xxx
      }
      receive:
      {
          status:200,
          msg:"",
          data:JSON
      }
      ```

    - 我的收藏

      ```java
      方式：POST
      URL：/favorite-list
      send:
      {
          stu_id:xxx
      }
      receive:
      {
          status:200,
          msg:"",
          data:JSON
      }
      ```

    - 个人中心

      ```java
      方式：POST
      URL：/profile
      send:
      {
          stu_id:xxx
      }
      receive:
      {
          status:200,
          msg:"",
          data:JSON
      }
      ```

    - 修改资料

      ```java
      方式：POST
      URL：/profile/update
      send:
      {
          stu_id:xxx,
          data:JSON
      }
      receive:
      {
          status:200,
          msg:"",
      }
      ```

    - 头像上传

      ```java
      方式：POST
      URL：/profile/upload-img
      send:
      {
          stu_id:xxx,
          data:img
      }
      receive:
      {
          status:200,
          msg:"",
      }
      ```

  - 喵币相关(my.csti-ns.com)

    - 喵币充值

      ```java
      方式：POST
      URL：/my/score/recharge
      send:
      {
          stu_id:xxx,
          score:xxx
      }
      receive:
      {
          status:200,
          msg:"",
      }
      ```

    - 消费记录

      ```java
      方式：Get
      URL：/my/:stu_id/score/consume-record
      send:
      {
      }
      receive:
      {
          status:200,
          data:JSON
      }
      ```

    - 充值记录

      ```java
      方式：Get
      URL：/my/:stu_id/score/recharge-record
      send:
      {
      }
      receive:
      {
          status:200,
          data:JSON
      }
      ```

  - 资源相关(download.csti-ns.com)

    - 下载资源

      ```java
      方式：Get
      URL：/res/download/:resource_id
      send:
      {
          stu_id:xxx
      }
      receive:
      {
          status:200,
          msg:""
      }
      ```

    - 上传资源

      ```java
      方式：Get
      URL：/res/upload/:resource_id
      send:
      {
          stu_id:xxx,
          score:xxx
      }
      receive:
      {
          status:200,
          msg:""
      }
      ```

    - 资源搜索

      ```java
      方式：Get
      URL：/res/search/:xxx
      send:
      {
      
      }
      receive:
      {
          status:200,
          msg:"",
          res-url:xxx
      }
      ```

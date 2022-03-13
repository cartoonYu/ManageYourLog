### ManageYourLog
这个项目如其名，用于业务日志的管理。这个项目建立的立意是对业务日志有解析，存储，查询的统一入口。

#### 支持上传方式
1. http
2. rpc (dubbo + nacos)
3. kafka

#### 支持查询方式
1. http

#### 支持存储方式
1. mysql

#### 优点
1. 多方式上传/查询日志，并支持上传层，接收层，查询层，存储层通过配置动态切换
2. 日志模型统一，适应不同业务的接入

#### 缺点（现阶段）
1. 业务需要指定上传方式以及配置相关配置，日志上传对业务代码有一定的侵入
2. 存储日志因事务问题只支持单一方式的存储，不支持多方式的存储

#### 规划但未实现功能
1. 降低日志上传对业务代码的侵入，实现仅通过注解 + 模版就能上传
2. 支持多存储方式的同时写入
3. 存储层支持同步写入以及异步写入，且可通过配置动态切换
4. 存储层增加对不同中间件的支持
5. 查询方式与市面上的日志查询系统打通，减少接入成本

#### 如何使用
1. 部署 ManageYourLogServer 项目
   1. 启动参数
```
--add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.math=ALL-UNNAMED
```

2. 引入 facade 包
```xml
<dependency>
    <groupId>org</groupId>
    <artifactId>ManageYourLogFacade</artifactId>
    <version>1.0.0</version>
</dependency>
```

2. 配置上传方式以及对应配置

3. 组装日志上传 pojo
```
org.manage.log.facade.model.req.UploadLogRecordReq
```

3. 调用对应方法进行上传
```
org.manage.log.facade.UploadLog.upload(org.manage.log.facade.model.req.UploadLogRecordReq)
org.manage.log.facade.UploadLog.upload(java.util.List<org.manage.log.facade.model.req.UploadLogRecordReq>)
```











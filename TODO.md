#### facade
1. kafka 异步写入消息
2. rpc 调用同步写入

#### server
1. 实际储存方式(mysql/es/hbase...)
2. 多方式上传日志（http/rpc/kafka）
3. CommonController/CommonService 按照职责重命名
4. p6spy + 动态日志级别添加
5. mybatis 事务添加

#### test

#### common
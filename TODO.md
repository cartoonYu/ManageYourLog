#### facade
1. kafka 异步写入消息
2. rpc 调用同步写入
3. http 同步调用

#### server
1. 实际储存方式
2. repo 选择具体存储方式 factory/策略
3. 多方式上传日志（http/rpc/kafka）
5. CommonController/CommonService 按照职责重命名

#### common
1. pom 文件强制依赖阿里云
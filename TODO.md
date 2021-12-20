#### facade
1. kafka 异步写入消息
2. rpc 调用同步写入

#### server
1. 实际储存方式(mysql/es/hbase...)
2. 多方式上传日志（rpc/kafka）
3. CommonController/CommonService 按照职责重命名
4. p6spy + 动态日志级别添加
5. mybatis 事务添加
6. 数据库存储枚举改为存储真实描述

#### test
1. test 包分拆到各业务包中？

#### common
1. fastjosn 转为 gson
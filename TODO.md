#### facade
1. kafka 异步写入消息
2. rpc 调用同步写入

#### server
1. 实际储存方式(es/hbase...)
2. 多方式上传日志（rpc/kafka）
3. web 容器切换到 undertow

#### test
1. test 包分拆到各业务包中？
2. 完全使用 junit5 api

#### common
1. README.md 编写
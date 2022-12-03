### ManageYourLog
<p>
As project name, this project is used to mange business log. <br/>
The conception about this project is that provide a unified entrance for parse, store, query business log
</p>

#### supported upload modes
1. http
2. rpc (dubbo + nacos)
3. kafka

#### supported query modes
1. http

#### supported store modes
1. mysql

#### advantages
1. Multipart way to upload/select log, support dynamic switch upload layer, receive layer, query layer, store layer
2. Unified logging model，adapt to the access of different services

#### current disadvantages
1. client need to define upload mode and its configuration, log uploading has certain intrusion on service code 
2. logs can be stored in a single mode but not in multiple modes due to transaction problems

#### on the road
1. Reduce the intrusion of log upload to business code, and only through annotations + templates can be uploaded
2. Support multipart store mode for store in same time
3. Async store and sync store will be supported and dynamic switch in store layer
4. Store layer support different middleware
5. Query layer access query system on the market, reduce access cost

#### how to use
1. Deploy ManageYourLogServer
   1. start parameter
```
--add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.math=ALL-UNNAMED
```

2. import facade
```xml
<dependency>
    <groupId>org</groupId>
    <artifactId>ManageLogUpload</artifactId>
    <version>1.0.0</version>
</dependency>
```

2. define upload log and its configuration

3. package upload log pojo
```
org.manage.log.upload.model.req.UploadLogRecordReq
```

3. call method to upload
```
org.manage.log.upload.UploadLog.upload(org.manage.log.upload.model.req.UploadLogRecordReq)
org.manage.log.upload.UploadLog.upload(java.util.List<org.manage.log.upload.model.req.UploadLogRecordReq>)
```











use ManageYourLog;
insert into LogRecord(
    recordId, content, operatorSort, operator, logRecordSort,
    indexIds, version, createTime, modifyTime)
    value (
      '111', '订单 1111 被快递员 222 送出分拨中心', 'USER', '222', 'operate',
      '111', 1, '2021-10-26 00:00:00', '2021-10-26 00:00:00'
    );

insert into LogRecordIndex(
    indexId, logRecordId, sort, indexValue, version,
    createTime, modifyTime)
    value(
      '111', '111', 'id', '111', 1,
      '2021-10-26 00:00:00', '2021-10-26 00:00:00'
    );

insert into LogConfig (
    ruleId, ruleName, logRecordSort, operatorSort, contentTemplate,
    description, version, createTime, modifyTime)
    value(
        '1', 'orderOperate', 'operate', 'USER', 'order operate, operator: %s',
        'order operate', 1, '2021-10-26 00:00:00', '2021-10-26 00:00:00'
    );

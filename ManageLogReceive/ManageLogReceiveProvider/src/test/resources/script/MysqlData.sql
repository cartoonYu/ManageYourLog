use ManageYourLog;
insert into LogRecord(
    recordId, content, operatorSort, operator, logRecordSort,
    version, createTime, modifyTime)
    value (
      '111', '订单 1111 被快递员 222 送出分拨中心', 'USER', '222', 'operate',
       1, '2021-10-26 00:00:00', '2021-10-26 00:00:00'
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
        '1', 'orderOperate', 'operate', 'USER', 'order operate, operator: #{userId.}',
        'order operate', 1, '2021-10-26 00:00:00', '2021-10-26 00:00:00'
    );

insert into LogIndexConfig (
    ruleId, ruleName, logConfigId, sort, valueIndexKey,
    description, version, createTime, modifyTime)
    value(
        '1', 'orderOperateIndex', 1, 'date', 'userId',
        'order operate index', 1, '2021-10-26 00:00:00', '2021-10-26 00:00:00'
    );

insert into LogContentFormatConfig (
    ruleId, ruleName, logConfigId, type, `value`,
    executeSequence, version, createTime, modifyTime)
    value(
          '1', 'orderOperateFormat1', 1, 'REGULAR_EXPRESSION_MATCH', '#\\{([A-Za-z]+\\.+)+\\}',
          0, 1, '2021-10-26 00:00:00', '2021-10-26 00:00:00'
    );

insert into LogContentFormatConfig (
    ruleId, ruleName, logConfigId, type, `value`,
    executeSequence, version, createTime, modifyTime)
    value(
          '2', 'orderOperateFormat2', 1, 'REGULAR_EXPRESSION_MATCH', '([A-Za-z]+\\.+)+',
          1, 1, '2021-10-26 00:00:00', '2021-10-26 00:00:00'
    );

-- mock define value todo
insert into LogContentFormatConfig (
    ruleId, ruleName, logConfigId, type, `value`,
    executeSequence, version, createTime, modifyTime)
    value(
          '3', 'orderOperateFormat3', 1, 'CODE_EXECUTE', 'test',
          1, 1, '2021-10-26 00:00:00', '2021-10-26 00:00:00'
    );

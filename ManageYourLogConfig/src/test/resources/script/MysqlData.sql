use ManageYourLog;
insert into LogConfig (
     ruleId, ruleName, logRecordSort, operatorSort,
     indexSort, description, version, createTime, modifyTime)
    value(
        '1', 'orderOperate', 'operate', 'deliveryman',
        'id', 'order operate', 1, '2021-10-26 00:00:00', '2021-10-26 00:00:00'
    );
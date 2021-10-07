drop DATABASE if exists `ManageLog`;
create DATABASE ManageLog;
USE ManageLog;
set NAMES utf8mb4;
drop table if exists `LogRecord`;
create table `LogRecord`(
    recordId long default 0 not null,
    content varchar(2000) default '' not null,
    operatorSort varchar(50) default '' not null,
    operator varchar(50) default '' not null,
    logRecordSortId int default 0 not null,
    indexIds varchar(200) default '' not null,
    version int(2) default 1 not null,
    createTime datetime not null,
    modifyTime datetime not null
);
drop table if exists `LogRecordIndex`;
create table `LogRecordIndex`(
    indexId long default 0 not null,
    logRecordId long default 0 not null,
    indexSortIndexId long default 0 not null,
    indexValue varchar(200) default '' not null,
    version int(2) default 1 not null,
    createTime datetime not null,
    modifyTime datetime not null
);

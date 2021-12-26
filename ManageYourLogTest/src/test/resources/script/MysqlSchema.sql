drop database if exists ManageYourLog;
create database ManageYourLog;
use ManageYourLog;
drop table if exists LogRecord;
CREATE TABLE `LogRecord` (
                             `recordId` varchar(100) NOT NULL DEFAULT '' COMMENT 'record id',
                             `content` text COMMENT 'log content',
                             `operatorSort` varchar(100) DEFAULT '' COMMENT 'log operator sort',
                             `operator` varchar(50) NOT NULL DEFAULT '' COMMENT 'log operator',
                             `logRecordSort` varchar(200) NOT NULL DEFAULT '0',
                             `indexIds` varchar(200) DEFAULT '' COMMENT 'log index id collection',
                             `version` int NOT NULL DEFAULT '1' COMMENT 'log version',
                             `createTime` datetime NOT NULL COMMENT 'record create time',
                             `modifyTime` datetime NOT NULL COMMENT 'record modify time',
                             UNIQUE KEY `LogRecord_recordId_uindex` (`recordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
drop table if exists LogRecordIndex;
CREATE TABLE `LogRecordIndex` (
                                  `indexId` varchar(100) NOT NULL DEFAULT '' COMMENT 'index id',
                                  `logRecordId` varchar(100) NOT NULL DEFAULT '' COMMENT 'mark belong to which record',
                                  `sort` varchar(50) DEFAULT '0' COMMENT 'index sort id',
                                  `indexValue` text COMMENT 'index value',
                                  `version` int DEFAULT '1' COMMENT 'version',
                                  `createTime` datetime DEFAULT NULL,
                                  `modifyTime` datetime DEFAULT NULL,
                                  UNIQUE KEY `LogRecordIndex_indexId_uindex` (`indexId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


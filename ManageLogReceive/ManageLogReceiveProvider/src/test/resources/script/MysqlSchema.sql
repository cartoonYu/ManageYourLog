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

drop table if exists LogConfig;
CREATE TABLE `LogConfig` (
                             `ruleId` varchar(100) NOT NULL DEFAULT '' COMMENT 'rule id',
                             `ruleName` varchar(200) NOT NULL DEFAULT '' COMMENT 'rule name',
                             `logRecordSort` varchar(30) NOT NULL DEFAULT '' COMMENT 'log record sort',
                             `operatorSort` varchar(30) DEFAULT '' COMMENT 'log operator sort',
                             `contentTemplate` text NOT NULL COMMENT 'log content template',
                             `description` varchar(200) NOT NULL DEFAULT '',
                             `version` int NOT NULL DEFAULT '1' COMMENT 'log version',
                             `createTime` datetime NOT NULL COMMENT 'config create time',
                             `modifyTime` datetime NOT NULL COMMENT 'config modify time',
                             UNIQUE KEY `LogConfig_ruleId_uindex` (`ruleId`),
                             UNIQUE KEY `LogConfig_ruleName_uindex` (`ruleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

drop table if exists LogIndexConfig;
CREATE TABLE `LogIndexConfig` (
                             `ruleId` varchar(100) NOT NULL DEFAULT '' COMMENT 'rule id',
                             `ruleName` varchar(200) NOT NULL DEFAULT '' COMMENT 'rule name',
                             `logConfigId` varchar(100) NOT NULL COMMENT 'related config id',
                             `sort` varchar(50) NOT NULL DEFAULT '' COMMENT 'index sort',
                             `valueIndex` int NOT NULL COMMENT 'value at log index',
                             `description` varchar(200) NOT NULL DEFAULT '',
                             `version` int NOT NULL DEFAULT '1' COMMENT 'log version',
                             `createTime` datetime NOT NULL COMMENT 'config create time',
                             `modifyTime` datetime NOT NULL COMMENT 'config modify time',
                             UNIQUE KEY `LogIndexConfig_ruleId_uindex` (`ruleId`),
                             UNIQUE KEY `LogIndexConfig_ruleName_uindex` (`ruleName`),
                             KEY `LogIndexConfig_logConfigId_index`(`logConfigId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
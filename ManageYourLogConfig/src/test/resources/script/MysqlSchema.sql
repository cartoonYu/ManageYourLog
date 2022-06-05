drop database if exists ManageYourLog;
create database ManageYourLog;
use ManageYourLog;
drop table if exists LogConfig;
CREATE TABLE `LogConfig` (
                             `ruleId` varchar(100) NOT NULL DEFAULT '' COMMENT 'rule id',
                             `ruleName` varchar(200) NOT NULL DEFAULT '' COMMENT 'rule name',
                             `logRecordSort` varchar(30) NOT NULL DEFAULT '' COMMENT 'log record sort',
                             `operatorSort` varchar(30) DEFAULT '' COMMENT 'log operator sort',
                             `indexSort` varchar(50) NOT NULL DEFAULT '' COMMENT 'index sort',
                             `description` varchar(200) NOT NULL DEFAULT '',
                             `version` int NOT NULL DEFAULT '1' COMMENT 'log version',
                             `createTime` datetime NOT NULL COMMENT 'config create time',
                             `modifyTime` datetime NOT NULL COMMENT 'config modify time',
                             UNIQUE KEY `LogConfig_ruleId_uindex` (`ruleId`),
                             UNIQUE KEY `LogConfig_ruleName_uindex` (`ruleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


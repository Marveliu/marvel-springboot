/*QUARTZ_01*/
DROP TABLE IF EXISTS SYS_QRTZ_FIRED_TRIGGERS;
/*QUARTZ_02*/
DROP TABLE IF EXISTS SYS_QRTZ_PAUSED_TRIGGER_GRPS;
/*QUARTZ_03*/
DROP TABLE IF EXISTS SYS_QRTZ_SCHEDULER_STATE;
/*QUARTZ_04*/
DROP TABLE IF EXISTS SYS_QRTZ_LOCKS;
/*QUARTZ_05*/
DROP TABLE IF EXISTS SYS_QRTZ_SIMPLE_TRIGGERS;
/*QUARTZ_06*/
DROP TABLE IF EXISTS SYS_QRTZ_SIMPROP_TRIGGERS;
/*QUARTZ_07*/
DROP TABLE IF EXISTS SYS_QRTZ_CRON_TRIGGERS;
/*QUARTZ_08*/
DROP TABLE IF EXISTS SYS_QRTZ_BLOB_TRIGGERS;
/*QUARTZ_09*/
DROP TABLE IF EXISTS SYS_QRTZ_TRIGGERS;
/*QUARTZ_10*/
DROP TABLE IF EXISTS SYS_QRTZ_JOB_DETAILS;
/*QUARTZ_11*/
DROP TABLE IF EXISTS SYS_QRTZ_CALENDARS;
/*QUARTZ_12*/
CREATE TABLE SYS_QRTZ_JOB_DETAILS(SCHED_NAME VARCHAR(120) NOT NULL,JOB_NAME VARCHAR(200) NOT NULL,JOB_GROUP VARCHAR(200) NOT NULL,DESCRIPTION VARCHAR(250) NULL,JOB_CLASS_NAME VARCHAR(250) NOT NULL,IS_DURABLE VARCHAR(1) NOT NULL,IS_NONCONCURRENT VARCHAR(1) NOT NULL,IS_UPDATE_DATA VARCHAR(1) NOT NULL,REQUESTS_RECOVERY VARCHAR(1) NOT NULL,JOB_DATA BLOB NULL,PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP))ENGINE=InnoDB;
/*QUARTZ_13*/
CREATE TABLE SYS_QRTZ_TRIGGERS(SCHED_NAME VARCHAR(120) NOT NULL,TRIGGER_NAME VARCHAR(200) NOT NULL,TRIGGER_GROUP VARCHAR(200) NOT NULL,JOB_NAME VARCHAR(200) NOT NULL,JOB_GROUP VARCHAR(200) NOT NULL,DESCRIPTION VARCHAR(250) NULL,NEXT_FIRE_TIME BIGINT(13) NULL,PREV_FIRE_TIME BIGINT(13) NULL,PRIORITY INTEGER NULL,TRIGGER_STATE VARCHAR(16) NOT NULL,TRIGGER_TYPE VARCHAR(8) NOT NULL,START_TIME BIGINT(13) NOT NULL,END_TIME BIGINT(13) NULL,CALENDAR_NAME VARCHAR(200) NULL,MISFIRE_INSTR SMALLINT(2) NULL,JOB_DATA BLOB NULL,PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)REFERENCES SYS_QRTZ_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP))ENGINE=InnoDB;
/*QUARTZ_14*/
CREATE TABLE SYS_QRTZ_SIMPLE_TRIGGERS(SCHED_NAME VARCHAR(120) NOT NULL,TRIGGER_NAME VARCHAR(200) NOT NULL,TRIGGER_GROUP VARCHAR(200) NOT NULL,REPEAT_COUNT BIGINT(7) NOT NULL,REPEAT_INTERVAL BIGINT(12) NOT NULL,TIMES_TRIGGERED BIGINT(10) NOT NULL,PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)REFERENCES SYS_QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))ENGINE=InnoDB;
/*QUARTZ_15*/
CREATE TABLE SYS_QRTZ_CRON_TRIGGERS(SCHED_NAME VARCHAR(120) NOT NULL,TRIGGER_NAME VARCHAR(200) NOT NULL,TRIGGER_GROUP VARCHAR(200) NOT NULL,CRON_EXPRESSION VARCHAR(120) NOT NULL,TIME_ZONE_ID VARCHAR(80),PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)REFERENCES SYS_QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))ENGINE=InnoDB;
/*QUARTZ_16*/
CREATE TABLE SYS_QRTZ_SIMPROP_TRIGGERS(SCHED_NAME VARCHAR(120) NOT NULL,TRIGGER_NAME VARCHAR(200) NOT NULL,TRIGGER_GROUP VARCHAR(200) NOT NULL,STR_PROP_1 VARCHAR(512) NULL,STR_PROP_2 VARCHAR(512) NULL,STR_PROP_3 VARCHAR(512) NULL,INT_PROP_1 INT NULL,INT_PROP_2 INT NULL,LONG_PROP_1 BIGINT NULL,LONG_PROP_2 BIGINT NULL,DEC_PROP_1 NUMERIC(13,4) NULL,DEC_PROP_2 NUMERIC(13,4) NULL,BOOL_PROP_1 VARCHAR(1) NULL,BOOL_PROP_2 VARCHAR(1) NULL,PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP), FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP) REFERENCES SYS_QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))ENGINE=InnoDB;
/*QUARTZ_17*/
CREATE TABLE SYS_QRTZ_BLOB_TRIGGERS (SCHED_NAME VARCHAR(120) NOT NULL,TRIGGER_NAME VARCHAR(200) NOT NULL,TRIGGER_GROUP VARCHAR(200) NOT NULL,BLOB_DATA BLOB NULL,PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),INDEX (SCHED_NAME,TRIGGER_NAME, TRIGGER_GROUP),FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)REFERENCES SYS_QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))ENGINE=InnoDB;
/*QUARTZ_18*/
CREATE TABLE SYS_QRTZ_CALENDARS (SCHED_NAME VARCHAR(120) NOT NULL,CALENDAR_NAME VARCHAR(200) NOT NULL,CALENDAR BLOB NOT NULL,PRIMARY KEY (SCHED_NAME,CALENDAR_NAME))ENGINE=InnoDB;
/*QUARTZ_19*/
CREATE TABLE SYS_QRTZ_PAUSED_TRIGGER_GRPS (SCHED_NAME VARCHAR(120) NOT NULL,TRIGGER_GROUP VARCHAR(200) NOT NULL,PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP))ENGINE=InnoDB;
/*QUARTZ_20*/
CREATE TABLE SYS_QRTZ_FIRED_TRIGGERS (SCHED_NAME VARCHAR(120) NOT NULL,ENTRY_ID VARCHAR(95) NOT NULL,TRIGGER_NAME VARCHAR(200) NOT NULL,TRIGGER_GROUP VARCHAR(200) NOT NULL,INSTANCE_NAME VARCHAR(200) NOT NULL,FIRED_TIME BIGINT(13) NOT NULL,SCHED_TIME BIGINT(13) NOT NULL,PRIORITY INTEGER NOT NULL,STATE VARCHAR(16) NOT NULL,JOB_NAME VARCHAR(200) NULL,JOB_GROUP VARCHAR(200) NULL,IS_NONCONCURRENT VARCHAR(1) NULL,REQUESTS_RECOVERY VARCHAR(1) NULL,PRIMARY KEY (SCHED_NAME,ENTRY_ID))ENGINE=InnoDB;
/*QUARTZ_21*/
CREATE TABLE SYS_QRTZ_SCHEDULER_STATE (SCHED_NAME VARCHAR(120) NOT NULL,INSTANCE_NAME VARCHAR(200) NOT NULL,LAST_CHECKIN_TIME BIGINT(13) NOT NULL,CHECKIN_INTERVAL BIGINT(13) NOT NULL,PRIMARY KEY (SCHED_NAME,INSTANCE_NAME))ENGINE=InnoDB;
/*QUARTZ_22*/
CREATE TABLE SYS_QRTZ_LOCKS (SCHED_NAME VARCHAR(120) NOT NULL,LOCK_NAME VARCHAR(40) NOT NULL,PRIMARY KEY (SCHED_NAME,LOCK_NAME))ENGINE=InnoDB;
/*QUARTZ_23*/
CREATE INDEX IDX_SYS_QRTZ_01 ON SYS_QRTZ_JOB_DETAILS(SCHED_NAME,REQUESTS_RECOVERY);
/*QUARTZ_24*/
CREATE INDEX IDX_SYS_QRTZ_02 ON SYS_QRTZ_JOB_DETAILS(SCHED_NAME,JOB_GROUP);
/*QUARTZ_25*/
CREATE INDEX IDX_SYS_QRTZ_03 ON SYS_QRTZ_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
/*QUARTZ_26*/
CREATE INDEX IDX_SYS_QRTZ_04 ON SYS_QRTZ_TRIGGERS(SCHED_NAME,JOB_GROUP);
/*QUARTZ_27*/
CREATE INDEX IDX_SYS_QRTZ_05 ON SYS_QRTZ_TRIGGERS(SCHED_NAME,CALENDAR_NAME);
/*QUARTZ_28*/
CREATE INDEX IDX_SYS_QRTZ_06 ON SYS_QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);
/*QUARTZ_29*/
CREATE INDEX IDX_SYS_QRTZ_07 ON SYS_QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE);
/*QUARTZ_30*/
CREATE INDEX IDX_SYS_QRTZ_08 ON SYS_QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE);
/*QUARTZ_31*/
CREATE INDEX IDX_SYS_QRTZ_09 ON SYS_QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE);
/*QUARTZ_32*/
CREATE INDEX IDX_SYS_QRTZ_10 ON SYS_QRTZ_TRIGGERS(SCHED_NAME,NEXT_FIRE_TIME);
/*QUARTZ_33*/
CREATE INDEX IDX_SYS_QRTZ_11 ON SYS_QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME);
/*QUARTZ_34*/
CREATE INDEX IDX_SYS_QRTZ_12 ON SYS_QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME);
/*QUARTZ_35*/
CREATE INDEX IDX_SYS_QRTZ_13 ON SYS_QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);
/*QUARTZ_36*/
CREATE INDEX IDX_SYS_QRTZ_14 ON SYS_QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);
/*QUARTZ_37*/
CREATE INDEX IDX_SYS_QRTZ_15 ON SYS_QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME);
/*QUARTZ_38*/
CREATE INDEX IDX_SYS_QRTZ_16 ON SYS_QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);
/*QUARTZ_39*/
CREATE INDEX IDX_SYS_QRTZ_17 ON SYS_QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
/*QUARTZ_40*/
CREATE INDEX IDX_SYS_QRTZ_18 ON SYS_QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_GROUP);
/*QUARTZ_41*/
CREATE INDEX IDX_SYS_QRTZ_19 ON SYS_QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);
/*QUARTZ_42*/
CREATE INDEX IDX_SYS_QRTZ_20 ON SYS_QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);
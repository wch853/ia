/**********
 * DDL
 *********/
DROP DATABASE IF EXISTS ia;
CREATE DATABASE ia;
USE ia;

DROP TABLE IF EXISTS block;
CREATE TABLE block (
  block_id   VARCHAR(255) NOT NULL
  COMMENT '地块编号',
  block_name VARCHAR(255) NOT NULL
  COMMENT '地块名称',
  block_loc  VARCHAR(255) NOT NULL
  COMMENT '地块位置',
  block_ps   VARCHAR(255) DEFAULT NULL
  COMMENT '地块备注',
  PRIMARY KEY (block_id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='地块';

DROP TABLE IF EXISTS section;
CREATE TABLE section (
  id           INT AUTO_INCREMENT NOT NULL
  COMMENT '区块编号',
  section_name VARCHAR(255)       NOT NULL
  COMMENT '区块名称',
  block_id     VARCHAR(255)       NOT NULL
  COMMENT '所属地块编号',
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='区块';

DROP TABLE IF EXISTS field;
CREATE TABLE field (
  field_id   VARCHAR(255)             NOT NULL
  COMMENT '大棚编号',
  field_name VARCHAR(255)             NOT NULL
  COMMENT '大棚名称',
  block_id   VARCHAR(255)             NULL
  COMMENT '所属地块编号',
  crop_id    VARCHAR(255) DEFAULT NULL
  COMMENT '种植作物编号',
  use_status VARCHAR(255) DEFAULT '0' NOT NULL
  COMMENT '使用状态：0-unuse 未使用，1-inuse 使用中',
  field_ps   VARCHAR(255) DEFAULT NULL
  COMMENT '大棚备注',
  PRIMARY KEY (field_id),
  KEY idx_block_id(block_id),
  KEY idx_crop_id(crop_id),
  KEY idx_use_status(use_status)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='大棚';

DROP TABLE IF EXISTS crop;
CREATE TABLE crop (
  crop_id   VARCHAR(255) NOT NULL
  COMMENT '作物编号',
  crop_name VARCHAR(255) NOT NULL
  COMMENT '作物名称',
  crop_ps   VARCHAR(255) DEFAULT NULL
  COMMENT '作物备注',
  PRIMARY KEY (crop_id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='作物';

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
  emp_id       VARCHAR(255) NOT NULL
  COMMENT '员工编号',
  emp_name     VARCHAR(255) NOT NULL
  COMMENT '员工姓名',
  emp_tel      VARCHAR(255) NOT NULL
  COMMENT '员工联系方式',
  emp_position VARCHAR(255) DEFAULT NULL
  COMMENT '员工职位',
  emp_age      INT          DEFAULT NULL
  COMMENT '员工年龄',
  emp_sex      VARCHAR(255) DEFAULT NULL
  COMMENT '员工性别',
  emp_mail     VARCHAR(255) DEFAULT NULL
  COMMENT '员工邮箱',
  mail_status  TINYINT      DEFAULT 0
  COMMENT '邮件推送 0-不推送 1-推送',
  emp_ps       VARCHAR(255) DEFAULT NULL
  COMMENT '员工备注',
  PRIMARY KEY (emp_id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='员工';

DROP TABLE IF EXISTS sensor;
CREATE TABLE sensor (
  sensor_id   VARCHAR(255)             NOT NULL
  COMMENT '传感器编号',
  sensor_func VARCHAR(255)             NOT NULL
  COMMENT '传感器功能类型',
  sensor_type VARCHAR(255)             NOT NULL
  COMMENT '传感器型号',
  field_id    VARCHAR(255) DEFAULT NULL
  COMMENT '所属大棚编号',
  terminal_id VARCHAR(255) DEFAULT NULL
  COMMENT '所属终端编号',
  use_status  VARCHAR(255) DEFAULT '0' NOT NULL
  COMMENT '使用状态：0-unuse 未使用，1-inuse 使用中， 2-error 故障中',
  sensor_ps   VARCHAR(255) DEFAULT NULL
  COMMENT '传感器备注',
  PRIMARY KEY (sensor_id),
  KEY idx_field_id(field_id),
  KEY idx_terminal_id(terminal_id),
  KEY idx_use_status(use_status)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='传感器';

DROP TABLE IF EXISTS machine;
CREATE TABLE machine (
  machine_id   VARCHAR(255)             NOT NULL
  COMMENT '机械编号',
  machine_type VARCHAR(255)             NOT NULL
  COMMENT '机械型号',
  block_id     VARCHAR(255) DEFAULT NULL
  COMMENT '所属地块编号',
  use_status   VARCHAR(255) DEFAULT '0' NOT NULL
  COMMENT '使用状态：0-unuse 未使用，1-inuse 使用中， 2-error 故障中',
  machine_ps   VARCHAR(255) DEFAULT NULL
  COMMENT '机械备注',
  PRIMARY KEY (machine_id),
  KEY idx_block_id(block_id),
  KEY idx_use_status(use_status)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='机械';

DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle (
  vehicle_id   VARCHAR(255)             NOT NULL
  COMMENT '车辆编号',
  vehicle_type VARCHAR(255)             NOT NULL
  COMMENT '车辆型号',
  block_id     VARCHAR(255) DEFAULT NULL
  COMMENT '所属地块编号',
  use_status   VARCHAR(255) DEFAULT '0' NOT NULL
  COMMENT '使用状态：0-unuse 未使用，1-inuse 使用中， 2-error 故障中',
  vehicle_ps   VARCHAR(255) DEFAULT NULL
  COMMENT '车辆备注',
  PRIMARY KEY (vehicle_id),
  KEY idx_block_id(block_id),
  KEY idx_use_status(use_status)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='车辆';

DROP TABLE IF EXISTS terminal;
CREATE TABLE terminal (
  terminal_id   VARCHAR(255)             NOT NULL
  COMMENT '终端编号',
  terminal_type VARCHAR(255)             NOT NULL
  COMMENT '终端型号',
  use_status    VARCHAR(255) DEFAULT '0' NOT NULL
  COMMENT '使用状态：0-unuse 未使用，1-inuse 使用中， 2-error 故障中',
  termial_ps    VARCHAR(255) DEFAULT NULL
  COMMENT '终端备注',
  PRIMARY KEY (terminal_id),
  KEY idx_use_status(use_status)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='终端';

DROP TABLE IF EXISTS end_device;
CREATE TABLE end_device (
  id          INT AUTO_INCREMENT NOT NULL
  COMMENT '终端编号',
  model       VARCHAR(255)       NOT NULL
  COMMENT '终端型号',
  mac         VARCHAR(255)       NOT NULL
  COMMENT 'mac地址',
  section_id  INT                NULL
  COMMENT '所属区块编号',
  use_status  INT                NOT NULL DEFAULT 0
  COMMENT '终端使用状态：0，未使用；1，使用中；2：故障中',
  create_time DATETIME           NOT NULL DEFAULT NOW()
  COMMENT '创建时间',
  update_time DATETIME           NOT NULL DEFAULT NOW() ON UPDATE NOW()
  COMMENT '更新时间',
  PRIMARY KEY (id),
  KEY idx_use_status(use_status),
  UNIQUE KEY (mac)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='终端';

DROP TABLE IF EXISTS data_record;
CREATE TABLE data_record (
  id          INT AUTO_INCREMENT NOT NULL
  COMMENT '数据记录编号',
  sensor_id   VARCHAR(255)       NOT NULL
  COMMENT '来源传感器编号',
  data_type   VARCHAR(255)       NOT NULL
  COMMENT '数据类型：1-temperature 温度，2-moisture 湿度，3-soil_temperature 土壤温度，4-soil_moisture 土壤水分，5-light 光照，6-co2 二氧化碳，7-ph pH，8-n 氮含量，9-p 磷含量，10-k 钾含量，11-hg 汞含量，12-pb 铅含量',
  val         DOUBLE(8, 2)       NOT NULL
  COMMENT '数据记录值',
  record_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  COMMENT '记录时间',
  PRIMARY KEY (id),
  KEY idx_sensor_id(sensor_id),
  KEY idx_data_type(data_type)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='数据记录';

DROP TABLE IF EXISTS upstream_data_record;
CREATE TABLE upstream_data_record (
  id            INT AUTO_INCREMENT NOT NULL
  COMMENT '数据记录编号',
  device_id INT                NOT NULL
  COMMENT '来源终端设备编号',
  data_type     INT                NOT NULL
  COMMENT '数据类型编号',
  value         DOUBLE(8, 2)       NOT NULL
  COMMENT '数据记录值',
  receive_time  DATETIME           NOT NULL
  COMMENT '数据上传时间',
  record_time   DATETIME           NOT NULL DEFAULT NOW()
  COMMENT '数据记录时间',
  PRIMARY KEY (id),
  KEY idx_section_id(device_id),
  KEY idx_data_type(data_type),
  KEY idx_receive_time(receive_time)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='数据上传记录';

DROP TABLE IF EXISTS field_status;
CREATE TABLE field_status (
  field_id         VARCHAR(255) NOT NULL
  COMMENT '大棚编号',
  update_time      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '状态更新时间',
  temperature      DOUBLE(8, 2) DEFAULT NULL
  COMMENT '温度',
  moisture         DOUBLE(8, 2) DEFAULT NULL
  COMMENT '湿度',
  soil_temperature DOUBLE(8, 2) DEFAULT NULL
  COMMENT '土壤温度',
  soil_moisture    DOUBLE(8, 2) DEFAULT NULL
  COMMENT '土壤湿度',
  light            DOUBLE(8, 2) DEFAULT NULL
  COMMENT '光照度',
  co2              DOUBLE(8, 2) DEFAULT NULL
  COMMENT '二氧化碳浓度',
  ph               DOUBLE(8, 2) DEFAULT NULL
  COMMENT 'ph',
  n                DOUBLE(8, 2) DEFAULT NULL
  COMMENT '氮含量',
  p                DOUBLE(8, 2) DEFAULT NULL
  COMMENT '磷含量',
  k                DOUBLE(8, 2) DEFAULT NULL
  COMMENT '钾含量',
  hg               DOUBLE(8, 2) DEFAULT NULL
  COMMENT '汞含量',
  pb               DOUBLE(8, 2) DEFAULT NULL
  COMMENT '铅含量',
  PRIMARY KEY (field_id),
  KEY idx_update_time(update_time)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='大棚状态';

DROP TABLE IF EXISTS irrigation_plan;
CREATE TABLE irrigation_plan (
  id          INT AUTO_INCREMENT NOT NULL
  COMMENT '排灌方案编号',
  plan_volume DOUBLE(8, 2)       NOT NULL
  COMMENT '灌溉量(m3)',
  duration    INT                NOT NULL
  COMMENT '灌溉持续时长(分钟)',
  plan_ps     VARCHAR(255) DEFAULT NULL
  COMMENT '方案备注',
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='排灌方案';

DROP TABLE IF EXISTS warn_record;
CREATE TABLE warn_record (
  id          INT AUTO_INCREMENT NOT NULL
  COMMENT '报警记录编号',
  field_id    VARCHAR(255)       NOT NULL
  COMMENT '来源大棚编号',
  warn_type   VARCHAR(255)       NOT NULL
  COMMENT '报警类型：1-temperature 温度，2-moisture 湿度，3-soil_temperature 土壤温度，4-soil_moisture 土壤水分，5-light 光照，6-co2 二氧化碳，7-ph pH，8-n 氮含量，9-p 磷含量，10-k 钾含量，11-hg 汞含量，12-pb 铅含量',
  warn_val    DOUBLE(8, 2)       NOT NULL
  COMMENT '报警值',
  warn_time   TIMESTAMP                   DEFAULT CURRENT_TIMESTAMP
  COMMENT '最早报警时间',
  warn_count  INT                NOT NULL DEFAULT 1
  COMMENT '报警计数',
  handle_time TIMESTAMP          NULL
  COMMENT '处理时间',
  flag        VARCHAR(255)       NOT NULL DEFAULT '0'
  COMMENT '处理标志：0-unhandle 未处理，1-handled 已处理，2-ignore 已忽略',
  PRIMARY KEY (id),
  KEY idx_field_id(field_id),
  KEY idx_warn_type(warn_type),
  KEY idx_warn_time(warn_time),
  KEY idx_flag(flag)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='报警记录';

DROP TABLE IF EXISTS alarm_record;
CREATE TABLE alarm_record (
  id          INT AUTO_INCREMENT NOT NULL
  COMMENT '报警记录编号',
  device_id   INT                NOT NULL
  COMMENT '来源终端设备编号',
  data_type   INT                NOT NULL
  COMMENT '数据类型',
  value       DOUBLE(8, 2)       NOT NULL
  COMMENT '数据值',
  alarm_time  DATETIME           NOT NULL DEFAULT NOW()
  COMMENT '报警时间',
  handle_time DATETIME                    DEFAULT NULL
  COMMENT '处理时间',
  handle_flag INT                NOT NULL DEFAULT 0
  COMMENT '处理标志 0-未处理 1-已处理 2-已忽略',
  PRIMARY KEY (id),
  KEY idx_device_id(device_id),
  KEY idx_data_type(data_type),
  KEY idx_alarm_time(alarm_time),
  KEY idx_handle_flag(handle_flag)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='报警记录';

DROP TABLE IF EXISTS warn_threshold;
CREATE TABLE warn_threshold (
  id             INT AUTO_INCREMENT       NOT NULL
  COMMENT '报警阈值编号',
  threshold_type VARCHAR(255)             NOT NULL
  COMMENT '阈值类型：1-temperature 温度，2-moisture 湿度，3-soil_temperature 土壤温度，4-soil_moisture 土壤水分，5-light 光照，6-co2 二氧化碳，7-ph pH，8-n 氮含量，9-p 磷含量，10-k 钾含量，11-hg 汞含量，12-pb 铅含量',
  floor          DOUBLE(8, 2)             NOT NULL
  COMMENT '阈值下限',
  ceil           DOUBLE(8, 2)             NOT NULL
  COMMENT '阈值上限',
  use_status     VARCHAR(255) DEFAULT '0' NOT NULL
  COMMENT '使用状态，0unuse，1inuse',
  PRIMARY KEY (id),
  KEY idx_threshold_type(threshold_type),
  KEY idx_use_status(use_status)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='报警阈值';

DROP TABLE IF EXISTS tmp_data;
CREATE TABLE tmp_data (
  field_id VARCHAR(255) NOT NULL
  COMMENT '大棚编号',
  val      DOUBLE(8, 2) DEFAULT NULL
  COMMENT '临时数据值'
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='大棚临时数据表';

DROP TABLE IF EXISTS data_type;
CREATE TABLE data_type (
  id              INT AUTO_INCREMENT NOT NULL
  COMMENT '数据类型编号',
  data_type_name  VARCHAR(255)       NOT NULL
  COMMENT '数据类型名称',
  data_short_name VARCHAR(3)         NOT NULL
  COMMENT '数据类型缩写',
  floor          DOUBLE(8, 2)             NOT NULL
  COMMENT '阈值下限',
  ceil           DOUBLE(8, 2)             NOT NULL
  COMMENT '阈值上限',
  use_status      TINYINT            NOT NULL DEFAULT 1
  COMMENT '使用状态 0-未使用，1-使用中',
  PRIMARY KEY (id),
  UNIQUE KEY (data_type_name),
  UNIQUE KEY (data_short_name)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='数据类型';

DROP TABLE IF EXISTS memo;
CREATE TABLE memo (
  id          INT AUTO_INCREMENT NOT NULL
  COMMENT '记录编号',
  title       VARCHAR(255)       NOT NULL
  COMMENT '标题',
  type        VARCHAR(255)       NOT NULL
  COMMENT '类型，0-日志，1-备忘录，2-注意事项',
  content     TEXT               NULL
  COMMENT '内容',
  update_user VARCHAR(255)       NOT NULL
  COMMENT '更新人',
  update_time TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间',
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT ='记录表';

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id       INT          NOT NULL AUTO_INCREMENT
  COMMENT '用户编号',
  name     VARCHAR(255) NOT NULL
  COMMENT '用户名称',
  username VARCHAR(255) NOT NULL
  COMMENT '账号',
  password VARCHAR(255) NOT NULL
  COMMENT '密码',
  salt     VARCHAR(255) NOT NULL
  COMMENT '盐',
  status   TINYINT      NOT NULL DEFAULT 1
  COMMENT '账号状态 0-无效，1-有效',
  PRIMARY KEY (id),
  UNIQUE KEY (username)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '用户';

DROP TABLE IF EXISTS role;
CREATE TABLE role (
  id        INT          NOT NULL AUTO_INCREMENT
  COMMENT '角色编号',
  role_name VARCHAR(255) NOT NULL
  COMMENT '角色名称',
  PRIMARY KEY (id),
  UNIQUE KEY (role_name)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '角色';

DROP TABLE IF EXISTS permission;
CREATE TABLE permission (
  id       INT          NOT NULL AUTO_INCREMENT
  COMMENT '权限编号',
  url      VARCHAR(255) NOT NULL
  COMMENT 'url地址',
  url_name VARCHAR(255) NOT NULL
  COMMENT 'url描述',
  perm     VARCHAR(255) NOT NULL
  COMMENT '权限标识符',
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '权限';

DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles (
  user_id INT NOT NULL
  COMMENT '用户编号',
  role_id INT NOT NULL
  COMMENT '角色编号',
  PRIMARY KEY (user_id, role_id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '用户-角色';

DROP TABLE IF EXISTS role_permissions;
CREATE TABLE role_permissions (
  role_id       INT NOT NULL
  COMMENT '角色编号',
  permission_id INT NOT NULL
  COMMENT '权限编号',
  PRIMARY KEY (role_id, permission_id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '角色-权限';

/**********
 * trigger / procedure
**********/

/*
 * data_record新增记录->即时更新大棚数据
 */
DELIMITER //
DROP PROCEDURE IF EXISTS update_field_status;
/*
 * 当数据记录表新增数据时，触发大棚状态表的相关字段更新
 * 触发器中不支持使用动态sql！
 */
CREATE PROCEDURE update_field_status(IN type VARCHAR(255), IN val DOUBLE(8, 2), IN f_id VARCHAR(255))
  BEGIN
    DECLARE u_status VARCHAR(255);
    SELECT use_status
    INTO u_status
    FROM warn_threshold
    WHERE threshold_type = type;
    /* 若该阈值启用，更新即时状态 */
    IF u_status = '1'
    THEN
      CASE type
        WHEN '1'
        THEN
          UPDATE field_status
          SET temperature = val
          WHERE field_id = f_id;
        WHEN '2'
        THEN
          UPDATE field_status
          SET moisture = val
          WHERE field_id = f_id;
        WHEN '3'
        THEN
          UPDATE field_status
          SET soil_temperature = val
          WHERE field_id = f_id;
        WHEN '4'
        THEN
          UPDATE field_status
          SET soil_moisture = val
          WHERE field_id = f_id;
        WHEN '5'
        THEN
          UPDATE field_status
          SET light = val
          WHERE field_id = f_id;
        WHEN '6'
        THEN
          UPDATE field_status
          SET co2 = val
          WHERE field_id = f_id;
        WHEN '7'
        THEN
          UPDATE field_status
          SET ph = val
          WHERE field_id = f_id;
        WHEN '8'
        THEN
          UPDATE field_status
          SET n = val
          WHERE field_id = f_id;
        WHEN '9'
        THEN
          UPDATE field_status
          SET p = val
          WHERE field_id = f_id;
        WHEN '10'
        THEN
          UPDATE field_status
          SET k = val
          WHERE field_id = f_id;
        WHEN '11'
        THEN
          UPDATE field_status
          SET hg = val
          WHERE field_id = f_id;
        WHEN '12'
        THEN
          UPDATE field_status
          SET pb = val
          WHERE field_id = f_id;
      ELSE
        SET f_id = '';
      END CASE;
    ELSE
      SET u_status = '0';
    END IF;
  END;

/*
 * data_record表触发器
 */
DROP TRIGGER IF EXISTS after_data_insert;
CREATE TRIGGER after_data_insert
  AFTER INSERT
  ON data_record
  FOR EACH ROW
  BEGIN
    DECLARE f_id VARCHAR(255) DEFAULT '';
    SET f_id = (SELECT field_id
                FROM sensor
                WHERE sensor_id = NEW.sensor_id);
    CALL update_field_status(NEW.data_type, NEW.val, f_id);
  END //
DELIMITER ;

/*
 * 新增大棚状态表模拟数据（空）
 * ----- 生产环境删 -----
 */
DELIMITER //
DROP PROCEDURE IF EXISTS add_field_status_data;
CREATE PROCEDURE add_field_status_data(IN prefix VARCHAR(255))
  BEGIN
    DECLARE i INT;
    DECLARE f_id VARCHAR(255);
    SET i = 1;
    WHILE i <= 4 DO
      SET f_id = CONCAT(prefix, i);
      INSERT INTO field_status (field_id) VALUES (f_id);
      SET i = i + 1;
    END WHILE;
  END;
DELIMITER ;

/*
 * 将对应数据类型编码转换为数据类型名称
 */
DELIMITER //
DROP PROCEDURE IF EXISTS code_to_type;
/* 将对应数据类型编码转换为数据类型名称 */
CREATE PROCEDURE code_to_type(IN code VARCHAR(255), OUT type VARCHAR(255))
  BEGIN
    CASE code
      WHEN '1'
      THEN
        SET type = 'temperature';
      WHEN '2'
      THEN
        SET type = 'moisture';
      WHEN '3'
      THEN
        SET type = 'soil_temperature';
      WHEN '4'
      THEN
        SET type = 'soil_moisture';
      WHEN '5'
      THEN
        SET type = 'light';
      WHEN '6'
      THEN
        SET type = 'co2';
      WHEN '7'
      THEN
        SET type = 'ph';
      WHEN '8'
      THEN
        SET type = 'n';
      WHEN '9'
      THEN
        SET type = 'p';
      WHEN '10'
      THEN
        SET type = 'k';
      WHEN '11'
      THEN
        SET type = 'hg';
      WHEN '12'
      THEN
        SET type = 'pb';
    ELSE
      SET type = '';
    END CASE;
  END //
DELIMITER ;

/*
 * 扫描大棚即时状态，将异常记录插入warn_record表
 */
DELIMITER //
DROP PROCEDURE IF EXISTS check_warn;
/* 获取阈值信息，并调用比较存储过程 */
CREATE PROCEDURE check_warn()
  BEGIN
    DECLARE ts_code, ts_type VARCHAR(255);
    DECLARE ts_floor, ts_ceil DOUBLE(8, 2);
    DECLARE ts_end INT DEFAULT 0;
    /* 定义warn_threshold的游标，获取状态为使用中的每类阈值的上下限 */
    DECLARE ts_cursor CURSOR FOR SELECT
                                   threshold_type,
                                   floor,
                                   ceil
                                 FROM warn_threshold
                                 WHERE use_status = '1';
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET ts_end = 1;

    OPEN ts_cursor;

    WHILE ts_end != 1 DO
      FETCH ts_cursor
      INTO ts_code, ts_floor, ts_ceil;

      /* 将阈值编码转为阈值名称 */
      CALL code_to_type(ts_code, ts_type);

      /* 将该项阈值的所有数据插入临时表 */
      SET @sql_exe = CONCAT('INSERT INTO tmp_data (SELECT field_id, ', ts_type, ' AS val FROM field_status)');
      PREPARE stmt FROM @sql_exe;
      EXECUTE stmt;
      DEALLOCATE PREPARE stmt;

      /* 调用比较存储过程 */
      CALL check_warn_compare(ts_code, ts_floor, ts_ceil);

    END WHILE;
    CLOSE ts_cursor;
  END;

/* 比较阈值，将不在阈值范围内的数据插入warn_record表 */
DROP PROCEDURE IF EXISTS check_warn_compare;
CREATE PROCEDURE check_warn_compare(IN ts_code VARCHAR(255), IN ts_floor DOUBLE(8, 2), IN ts_ceil DOUBLE(8, 2))
  BEGIN
    DECLARE fs_id VARCHAR(255);
    DECLARE fs_val DOUBLE(8, 2);
    DECLARE val_end, record_id INT DEFAULT 0;

    /* 定义该项阈值对应所有大棚即时状态数据游标 */
    DECLARE val_cursor CURSOR FOR SELECT
                                    field_id,
                                    val
                                  FROM tmp_data;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET val_end = 1;

    OPEN val_cursor;

    WHILE val_end != 1 DO
      FETCH val_cursor
      INTO fs_id, fs_val;
      /* 数据值不为空时才比较 */
      IF fs_val IS NOT NULL
      THEN
        /* 不在规定阈值范围内 */
        IF fs_val < ts_floor || fs_val > ts_ceil
        THEN
          /* 查找是否存在相应大棚未处理的同类型报警记录 */
          SELECT id
          INTO record_id
          FROM warn_record
          WHERE field_id = fs_id AND warn_type = ts_code AND flag = '0';
          IF record_id = 0
          THEN
            /* 不存在相应报警记录，向表中插入 */
            INSERT INTO warn_record (field_id, warn_type, warn_val) VALUES (fs_id, ts_code, fs_val);
          ELSE
            /* 若存在，更新该报警记录 */
            UPDATE warn_record
            SET warn_val = fs_val, warn_count = warn_count + 1
            WHERE field_id = fs_id AND warn_type = ts_code AND flag = '0';
          END IF;
        END IF;
      END IF;
    END WHILE;

    CLOSE val_cursor;

    /* 每遍历完成一类数据，将tmp_data表truncate */
    TRUNCATE tmp_data;
  END //
DELIMITER ;

/* 将某项阈值标为未使用后，将所有大棚数据项中的该项数据置空 */
DELIMITER //
DROP PROCEDURE IF EXISTS ignore_data;
CREATE PROCEDURE ignore_data(IN ts_code VARCHAR(255))
  BEGIN
    DECLARE ts_type VARCHAR(255);
    /* 将阈值编码转为阈值名称 */
    CALL code_to_type(ts_code, ts_type);

    /* 将该项阈值的所有数据置空 */
    SET @sql_exe = CONCAT('UPDATE field_status SET ', ts_type, ' = NULL');
    PREPARE stmt FROM @sql_exe;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END //
DELIMITER ;

/* 插入图表测试数据，生产删 */
DELIMITER //
DROP PROCEDURE IF EXISTS insert_test_chart_data;
CREATE PROCEDURE insert_test_chart_data(IN days INT)
  BEGIN
    DECLARE now, r_time, e_time TIMESTAMP;
    DECLARE random_value DOUBLE(8, 2);
    SELECT CURRENT_TIMESTAMP
    INTO now;
    SELECT DATE_ADD(now, INTERVAL -days DAY)
    INTO r_time;
    SELECT DATE_ADD(now, INTERVAL 1 HOUR)
    INTO e_time;
    WHILE r_time < e_time DO
      SET random_value = RAND() * 10 + 15;
      INSERT INTO data_record (sensor_id, data_type, val, record_time) VALUES ('s-01-001', 1, random_value, r_time);
      SET random_value = RAND() * 10 + 15;
      INSERT INTO data_record (sensor_id, data_type, val, record_time) VALUES ('s-01-001', 2, random_value, r_time);
      SET random_value = RAND() * 10 + 15;
      INSERT INTO data_record (sensor_id, data_type, val, record_time) VALUES ('s-01-001', 3, random_value, r_time);
      SET random_value = RAND() * 10 + 15;
      INSERT INTO data_record (sensor_id, data_type, val, record_time) VALUES ('s-01-001', 4, random_value, r_time);
      SET random_value = RAND() * 10000 + 3000;
      INSERT INTO data_record (sensor_id, data_type, val, record_time) VALUES ('s-01-001', 5, random_value, r_time);
      SET random_value = RAND() * 1000 + 800;
      INSERT INTO data_record (sensor_id, data_type, val, record_time) VALUES ('s-01-001', 6, random_value, r_time);
      SET random_value = RAND() * 1 + 6.5;
      INSERT INTO data_record (sensor_id, data_type, val, record_time) VALUES ('s-01-001', 7, random_value, r_time);
      SET random_value = RAND() * 10 + 30;
      INSERT INTO data_record (sensor_id, data_type, val, record_time) VALUES ('s-01-001', 8, random_value, r_time);
      SET random_value = RAND() * 10 + 5;
      INSERT INTO data_record (sensor_id, data_type, val, record_time) VALUES ('s-01-001', 9, random_value, r_time);
      SET random_value = RAND() * 100 + 30;
      INSERT INTO data_record (sensor_id, data_type, val, record_time) VALUES ('s-01-001', 10, random_value, r_time);
      SET random_value = RAND() * 1 + 0.15;
      INSERT INTO data_record (sensor_id, data_type, val, record_time) VALUES ('s-01-001', 11, random_value, r_time);
      SET random_value = RAND() * 100 + 35;
      INSERT INTO data_record (sensor_id, data_type, val, record_time) VALUES ('s-01-001', 12, random_value, r_time);
      SELECT DATE_ADD(r_time, INTERVAL 1 HOUR)
      INTO r_time;
    END WHILE;
  END //
DELIMITER ;

/*********
 * data
 **********/

/*
 * block
 */
INSERT INTO ia.block (block_id, block_name, block_loc, block_ps)
VALUES ('b01', '沛县现代农业产业园区', '朱寨镇、沛城镇、鹿楼镇、张寨镇、经济开发区', '近郊城市农业区');
INSERT INTO ia.block (block_id, block_name, block_loc, block_ps) VALUES ('b02', '胡寨草庙千亩长茄示范园', '胡寨镇', '东部优质粮食主产区');
INSERT INTO ia.block (block_id, block_name, block_loc, block_ps) VALUES ('b03', '沛城万亩精品农业示范园', '沛城镇', '近郊城市农业区');
INSERT INTO ia.block (block_id, block_name, block_loc, block_ps) VALUES ('b04', '张寨万亩农业生态园', '张寨镇', '南部高效设施园艺区');
INSERT INTO ia.block (block_id, block_name, block_loc, block_ps) VALUES ('b05', '朱寨现代农业科技示范园', '朱寨镇', '西部高效生态养殖区');
INSERT INTO ia.block (block_id, block_name, block_loc, block_ps) VALUES ('b06', '鹿楼种养生态示范园', '鹿楼镇', '西部高效生态养殖区');
INSERT INTO ia.block (block_id, block_name, block_loc, block_ps) VALUES ('b07', '河口设施农业示范园', '河口镇', '南部高效设施园艺区');
INSERT INTO ia.block (block_id, block_name, block_loc, block_ps) VALUES ('b08', '敬安循环农业示范园', '敬安镇', '南部高效设施园艺区');
INSERT INTO ia.block (block_id, block_name, block_loc, block_ps) VALUES ('b09', '安国生态农业观光园', '安国镇', '南部高效设施园艺区');

/*
 * field
 */
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1701001', '加温温室', 'b01', 'c001', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1701002', '双屋面温室', 'b01', 'c002', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1701003', '透光塑料大棚', 'b01', 'c003', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1701004', '塑料大棚', 'b01', 'c004', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1702001', '加温温室', 'b02', 'c001', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1702002', '双屋面温室', 'b02', 'c002', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1702003', '透光塑料大棚', 'b02', 'c003', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1702004', '塑料大棚', 'b02', 'c004', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1703001', '加温温室', 'b03', 'c001', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1703002', '双屋面温室', 'b03', 'c002', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1703003', '透光塑料大棚', 'b03', 'c003', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1703004', '塑料大棚', 'b03', 'c004', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1704001', '加温温室', 'b04', 'c001', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1704002', '双屋面温室', 'b04', 'c002', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1704003', '透光塑料大棚', 'b04', 'c003', '1', NULL);
INSERT INTO ia.field (field_id, field_name, block_id, crop_id, use_status, field_ps)
VALUES ('f1704004', '塑料大棚', 'b04', 'c004', '1', NULL);

/*
 * crop
 */
INSERT INTO ia.crop (crop_id, crop_name, crop_ps) VALUES ('c001', '玉米', NULL);
INSERT INTO ia.crop (crop_id, crop_name, crop_ps) VALUES ('c002', '生菜', NULL);
INSERT INTO ia.crop (crop_id, crop_name, crop_ps) VALUES ('c003', '花生', NULL);
INSERT INTO ia.crop (crop_id, crop_name, crop_ps) VALUES ('c004', '大豆', NULL);

/*
 * employee
 */
INSERT INTO ia.employee (emp_id, emp_name, emp_tel, emp_position, emp_age, emp_sex, emp_mail, mail_status, emp_ps)
VALUES ('e001', 'wch1', '15261861234', NULL, NULL, NULL, 'wch853@163.com', 1, NULL);
INSERT INTO ia.employee (emp_id, emp_name, emp_tel, emp_position, emp_age, emp_sex, emp_mail, mail_status, emp_ps)
VALUES ('e002', 'wch2', '15261861234', NULL, NULL, NULL, 'wch853@163.com', 1, NULL);
INSERT INTO ia.employee (emp_id, emp_name, emp_tel, emp_position, emp_age, emp_sex, emp_mail, mail_status, emp_ps)
VALUES ('e003', 'wch3', '15261861234', NULL, NULL, NULL, 'wch853@163.com', 0, NULL);

/*
 * sensor
 */
INSERT INTO ia.sensor (sensor_id, sensor_func, sensor_type, field_id, terminal_id, use_status, sensor_ps)
VALUES ('s-01-001', '1', 'abc001', 'f1701001', 't01', '1', NULL);
INSERT INTO ia.sensor (sensor_id, sensor_func, sensor_type, field_id, terminal_id, use_status, sensor_ps)
VALUES ('s-01-002', '1', 'abc001', 'f1701002', 't01', '1', NULL);
INSERT INTO ia.sensor (sensor_id, sensor_func, sensor_type, field_id, terminal_id, use_status, sensor_ps)
VALUES ('s-02-001', '2', 'abc002', 'f1701003', 't01', '1', NULL);
INSERT INTO ia.sensor (sensor_id, sensor_func, sensor_type, field_id, terminal_id, use_status, sensor_ps)
VALUES ('s-02-002', '2', 'abc002', 'f1701004', 't01', '1', NULL);

/*
 * machine
 */
INSERT INTO ia.machine (machine_id, machine_type, block_id, use_status, machine_ps)
VALUES ('m001', 'cba001', 'b01', '0', NULL);
INSERT INTO ia.machine (machine_id, machine_type, block_id, use_status, machine_ps)
VALUES ('m002', 'cba002', 'b02', '0', NULL);
INSERT INTO ia.machine (machine_id, machine_type, block_id, use_status, machine_ps)
VALUES ('m003', 'cba003', 'b03', '0', NULL);
INSERT INTO ia.machine (machine_id, machine_type, block_id, use_status, machine_ps)
VALUES ('m004', 'cba004', 'b04', '0', NULL);

/*
 * vehicle
 */
INSERT INTO ia.vehicle (vehicle_id, vehicle_type, block_id, use_status, vehicle_ps)
VALUES ('v001', 'xyz001', 'b01', '0', NULL);
INSERT INTO ia.vehicle (vehicle_id, vehicle_type, block_id, use_status, vehicle_ps)
VALUES ('v002', 'xyz002', 'b02', '0', NULL);
INSERT INTO ia.vehicle (vehicle_id, vehicle_type, block_id, use_status, vehicle_ps)
VALUES ('v003', 'xyz003', 'b03', '0', NULL);
INSERT INTO ia.vehicle (vehicle_id, vehicle_type, block_id, use_status, vehicle_ps)
VALUES ('v004', 'xyz004', 'b04', '0', NULL);

/*
 * terminal
 */
INSERT INTO ia.terminal (terminal_id, terminal_type, use_status, termial_ps) VALUES ('t01', 't001', '1', NULL);
INSERT INTO ia.terminal (terminal_id, terminal_type, use_status, termial_ps) VALUES ('t02', 't002', '1', NULL);
INSERT INTO ia.terminal (terminal_id, terminal_type, use_status, termial_ps) VALUES ('t03', 't003', '1', NULL);

/*
 * field_status
 */
/* ----- 测试，生产删 ----- */
CALL add_field_status_data('f170100');
CALL add_field_status_data('f170200');
CALL add_field_status_data('f170300');
CALL add_field_status_data('f170400');

/*
 * data_record
 */
INSERT INTO ia.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-001', '1', 14.15, NULL);
INSERT INTO ia.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-002', '2', 25.25, NULL);
INSERT INTO ia.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-001', '3', 25.25, NULL);
INSERT INTO ia.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-002', '4', 25.25, NULL);
INSERT INTO ia.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-001', '5', 2000, NULL);
INSERT INTO ia.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-002', '6', 800, NULL);
INSERT INTO ia.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-001', '7', 7, NULL);
INSERT INTO ia.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-002', '8', 40, NULL);
INSERT INTO ia.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-001', '9', 40, NULL);
INSERT INTO ia.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-002', '10', 40, NULL);
INSERT INTO ia.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-001', '11', 1, NULL);
INSERT INTO ia.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-002', '12', 40, NULL);

/*
 * data_record
 * ----- 测试，生产删 -----
 * CALL insert_test_chart_data();
 */

/*
 * warn_threshold
 */
INSERT INTO ia.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (1, '1', 15, 45, '1');
INSERT INTO ia.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (2, '2', 15, 45, '1');
INSERT INTO ia.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (3, '3', 15, 45, '1');
INSERT INTO ia.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (4, '4', 15, 45, '1');
INSERT INTO ia.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (5, '5', 3000, 60000, '1');
INSERT INTO ia.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (6, '6', 800, 6000, '1');
INSERT INTO ia.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (7, '7', 6.5, 7.5, '1');
INSERT INTO ia.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (8, '8', 30, 100, '1');
INSERT INTO ia.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (9, '9', 5, 30, '1');
INSERT INTO ia.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (10, '10', 30, 160, '1');
INSERT INTO ia.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (11, '11', 0.15, 1.5, '1');
INSERT INTO ia.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (12, '12', 35, 500, '1');

/*
 * warn_record
 */
/* 通过扫描field_status表插入数据 */
/* CALL check_warn(); */
INSERT INTO ia.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag)
VALUES (1, 'f1701001', '1', 1.23, NULL, NULL, '0');
INSERT INTO ia.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag)
VALUES (2, 'f1701002', '2', 3.45, NULL, NULL, '0');
INSERT INTO ia.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag)
VALUES (3, 'f1701003', '3', 5.98, NULL, NULL, '0');
INSERT INTO ia.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag)
VALUES (4, 'f1701004', '4', 9.58, NULL, NULL, '0');
INSERT INTO ia.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag)
VALUES (5, 'f1702001', '5', 5.25, NULL, NULL, '0');

/*
 * memo
 */
INSERT INTO ia.memo (id, title, type, content, update_user, update_time) VALUES (1, '日志1', '0', '日志1', 'root', NULL);
INSERT INTO ia.memo (id, title, type, content, update_user, update_time) VALUES (2, '备忘录1', '1', '备忘录1', 'root', NULL);
INSERT INTO ia.memo (id, title, type, content, update_user, update_time)
VALUES (3, '注意事项1', '2', '注意事项1', 'root', NULL);

/*
 * user
 */
INSERT INTO ia.user (id, name, username, password, salt, status)
VALUES (1, 'root', 'wch853', '6991b327c9a016bbfc7fbe905d08a82e', '!@#', 1);

/*
 * role
 */
INSERT INTO ia.role (id, role_name) VALUES (1, '超级管理员');

/*
 * permission
 */
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (1, '/**', '完全权限', '*');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (2, '/sys/file/*/add', '档案添加', 'sys:file:add');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (3, '/sys/file/*/modify', '档案修改', 'sys:file:modify');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (4, '/sys/file/*/remove', '档案删除', 'sys:file:remove');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (5, '/sys/file/**', '档案查询', 'sys:file:query');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (6, '/sys/auth/**', '权限管理', 'sys:auth');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (7, '/sys/data/**', '数据查询', 'sys:data:query');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (8, '/sys/warn/*/modify', '报警修改', 'sys:warn:modify');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (9, '/sys/warn/**', '报警查询', 'sys:warn:query');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (10, '/sys/control/**', '控制管理', 'sys:ctrl');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (11, '/sys/memo/add', '备忘添加', 'sys:memo:add');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (12, '/sys/memo/modify', '备忘修改', 'sys:memo:modify');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (13, '/sys/memo/remove', '备忘删除', 'sys:memo:remove');
INSERT INTO ia.permission (id, url, url_name, perm) VALUES (14, '/sys/memo/**', '备忘查询', 'sys:memo:query');

/*
 * user_roles
 */
INSERT INTO ia.user_roles (user_id, role_id) VALUES (1, 1);

/*
 * role_permissions
 */
INSERT INTO ia.role_permissions (role_id, permission_id) VALUES (1, 1);

/**
 * end_device
 */
INSERT INTO ia.end_device (id, model, mac, section_id, use_status, create_time, update_time)
VALUES (1, 'cc2530', '00-12-4B-00-03-98-A1-AB', NULL, 0, NOW(), NOW());
INSERT INTO ia.end_device (id, model, mac, section_id, use_status, create_time, update_time)
VALUES (2, 'cc2530', '00-12-4B-00-9E-17-1D-52', NULL, 0, NOW(), NOW());

INSERT INTO ia.data_type (id, data_type_name, data_short_name, floor, ceil, use_status) VALUES (1, '温度', 't', 15, 45, 1);
INSERT INTO ia.data_type (id, data_type_name, data_short_name, floor, ceil, use_status) VALUES (2, '湿度', 'h', 15, 45, 1);
INSERT INTO ia.data_type (id, data_type_name, data_short_name, floor, ceil, use_status) VALUES (3, '土壤温度', 'st', 15, 45, 1);
INSERT INTO ia.data_type (id, data_type_name, data_short_name, floor, ceil, use_status) VALUES (4, '土壤湿度', 'sh', 15, 45, 1);
INSERT INTO ia.data_type (id, data_type_name, data_short_name, floor, ceil, use_status) VALUES (5, '光照度', 'l', 3000, 60000, 1);
INSERT INTO ia.data_type (id, data_type_name, data_short_name, floor, ceil, use_status) VALUES (6, '二氧化碳含量', 'co2', 350, 1000, 1);
INSERT INTO ia.data_type (id, data_type_name, data_short_name, floor, ceil, use_status) VALUES (7, 'pH值', 'ph', 6.5, 8.5, 1);
INSERT INTO ia.data_type (id, data_type_name, data_short_name, floor, ceil, use_status) VALUES (8, '氮含量', 'n', 30, 100, 1);
INSERT INTO ia.data_type (id, data_type_name, data_short_name, floor, ceil, use_status) VALUES (9, '磷含量', 'p', 5, 30, 1);
INSERT INTO ia.data_type (id, data_type_name, data_short_name, floor, ceil, use_status) VALUES (10, '钾含量', 'k', 30, 160, 1);
INSERT INTO ia.data_type (id, data_type_name, data_short_name, floor, ceil, use_status) VALUES (11, '汞含量', 'hg', 0.15, 1.5, 1);
INSERT INTO ia.data_type (id, data_type_name, data_short_name, floor, ceil, use_status) VALUES (12, '铅含量', 'pb', 35, 500, 1);
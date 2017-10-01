/**********
 * DDL
 *********/

DROP TABLE IF EXISTS block;
CREATE TABLE block (
block_id VARCHAR(255) NOT NULL COMMENT '地块编号',
block_name VARCHAR(255) NOT NULL COMMENT '地块名称',
block_loc VARCHAR(255) NOT NULL COMMENT '地块位置',
block_ps VARCHAR(255) DEFAULT NULL COMMENT '地块备注',
PRIMARY KEY (block_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='地块';

DROP TABLE IF EXISTS field;
CREATE TABLE field (
field_id VARCHAR(255) NOT NULL COMMENT '大棚编号',
field_name VARCHAR(255) NOT NULL COMMENT '大棚名称',
block_id VARCHAR(255) NOT NULL COMMENT '所属地块编号',
crop_id VARCHAR(255) DEFAULT NULL COMMENT '种植作物编号',
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态：0-unuse 未使用，1-inuse 使用中',
field_ps VARCHAR(255) DEFAULT NULL COMMENT '大棚备注',
PRIMARY KEY (field_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='大棚';

DROP TABLE IF EXISTS crop;
CREATE TABLE crop (
crop_id VARCHAR(255) NOT NULL COMMENT '作物编号',
crop_name VARCHAR(255) NOT NULL COMMENT '作物名称',
crop_ps VARCHAR(255) DEFAULT NULL COMMENT '作物备注',
PRIMARY KEY (crop_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='作物';

DROP TABLE IF EXISTS admin;
CREATE TABLE admin (
admin_id INT NOT NULL auto_increment COMMENT '管理员编号',
username VARCHAR(255) NOT NULL COMMENT '用户名',
password VARCHAR(255) NOT NULL COMMENT '密码',
PRIMARY KEY (admin_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='管理员';

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
emp_id VARCHAR(255) NOT NULL COMMENT '员工编号',
emp_name VARCHAR(255) NOT NULL COMMENT '员工姓名',
emp_tel VARCHAR(255) NOT NULL COMMENT '员工联系方式',
emp_position VARCHAR(255) DEFAULT NULL COMMENT '员工职位',
emp_age INT DEFAULT NULL COMMENT '员工年龄',
emp_sex VARCHAR(255) DEFAULT NULL COMMENT '员工性别',
emp_ps VARCHAR(255) DEFAULT NULL COMMENT '员工备注',
PRIMARY KEY (emp_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='员工';

DROP TABLE IF EXISTS sensor;
CREATE TABLE sensor (
sensor_id VARCHAR(255) NOT NULL COMMENT '传感器编号',
sensor_func VARCHAR(255) NOT NULL COMMENT '传感器功能类型',
sensor_type VARCHAR(255) NOT NULL COMMENT '传感器型号',
field_id VARCHAR(255) DEFAULT NULL COMMENT '所属大棚编号',
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态：0-unuse 未使用，1-inuse 使用中， 2-error 故障中',
sensor_ps VARCHAR(255) DEFAULT NULL COMMENT '传感器备注',
PRIMARY KEY (sensor_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='传感器';

DROP TABLE IF EXISTS machine;
CREATE TABLE machine (
machine_id VARCHAR(255) NOT NULL COMMENT '机械编号',
machine_type VARCHAR(255) NOT NULL COMMENT '机械型号',
block_id VARCHAR(255) DEFAULT NULL COMMENT '所属地块编号',
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态：0-unuse 未使用，1-inuse 使用中， 2-error 故障中',
machine_ps VARCHAR(255) DEFAULT NULL COMMENT '机械备注',
PRIMARY KEY (machine_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='机械';

DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle (
vehicle_id VARCHAR(255) NOT NULL COMMENT '车辆编号',
vehicle_type VARCHAR(255) NOT NULL COMMENT '车辆型号',
block_id VARCHAR(255) DEFAULT NULL COMMENT '所属地块编号',
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态：0-unuse 未使用，1-inuse 使用中， 2-error 故障中',
vehicle_ps VARCHAR(255) DEFAULT NULL COMMENT '车辆备注',
PRIMARY KEY (vehicle_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='车辆';

DROP TABLE IF EXISTS data_record;
CREATE TABLE data_record (
id INT AUTO_INCREMENT NOT NULL COMMENT '数据记录编号',
sensor_id VARCHAR(255) NOT NULL COMMENT '来源传感器编号',
data_type VARCHAR(255) NOT NULL COMMENT '数据类型：1-temperature 温度，2-moisture 湿度，3-soil_temperature 土壤温度，4-soil_moisture 土壤水分，5-light 光照，6-co2 二氧化碳，7-ph pH，8-n 氮含量，9-p 磷含量，10-k 钾含量，11-hg 汞含量，12-pb 铅含量',
val DOUBLE(5, 2) NOT NULL COMMENT '数据记录值',
record_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='数据记录';

DROP TABLE IF EXISTS field_status;
CREATE TABLE field_status (
field_id VARCHAR(255) NOT NULL COMMENT '大棚编号',
update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '状态更新时间',
temperature DOUBLE(5, 2) DEFAULT NULL COMMENT '温度',
moisture DOUBLE(5, 2) DEFAULT NULL COMMENT '湿度',
soil_temperature DOUBLE(5, 2) DEFAULT NULL COMMENT '土壤温度',
soil_moisture DOUBLE(5, 2) DEFAULT NULL COMMENT '土壤湿度',
light DOUBLE(5, 2) DEFAULT NULL COMMENT '光照度',
co2 DOUBLE(5, 2) DEFAULT NULL COMMENT '二氧化碳浓度',
ph DOUBLE(5, 2) DEFAULT NULL COMMENT 'ph',
n DOUBLE(5, 2) DEFAULT NULL COMMENT '氮含量',
p DOUBLE(5, 2) DEFAULT NULL COMMENT '磷含量',
k DOUBLE(5, 2) DEFAULT NULL COMMENT '钾含量',
hg DOUBLE(5, 2) DEFAULT NULL COMMENT '汞含量',
pb DOUBLE(5, 2) DEFAULT NULL COMMENT '铅含量',
PRIMARY KEY (field_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='大棚状态';

DROP TABLE IF EXISTS warn_record;
CREATE TABLE warn_record (
id INT AUTO_INCREMENT NOT NULL COMMENT '报警记录编号',
field_id VARCHAR(255) NOT NULL COMMENT '来源大棚编号',
warn_type VARCHAR(255) NOT NULL COMMENT '报警类型：1-temperature 温度，2-moisture 湿度，3-soil_temperature 土壤温度，4-soil_moisture 土壤水分，5-light 光照，6-co2 二氧化碳，7-ph pH，8-n 氮含量，9-p 磷含量，10-k 钾含量，11-hg 汞含量，12-pb 铅含量',
warn_val DOUBLE(5, 2) NOT NULL COMMENT '报警值',
warn_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '最近报警时间',
warn_count INT NOT NULL DEFAULT 1 COMMENT '报警计数',
handle_time TIMESTAMP NULL COMMENT '处理时间',
flag VARCHAR(255) NOT NULL DEFAULT '0' COMMENT '处理标志：0-unhandle 未处理，1-handled 已处理，2-ignore 已忽略',
PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='报警记录';

DROP TABLE IF EXISTS warn_threshold;
CREATE TABLE warn_threshold (
id INT AUTO_INCREMENT NOT NULL COMMENT '报警阈值编号',
threshold_type VARCHAR(255) NOT NULL COMMENT '阈值类型：1-temperature 温度，2-moisture 湿度，3-soil_temperature 土壤温度，4-soil_moisture 土壤水分，5-light 光照，6-co2 二氧化碳，7-ph pH，8-n 氮含量，9-p 磷含量，10-k 钾含量，11-hg 汞含量，12-pb 铅含量',
floor DOUBLE(5, 2) NOT NULL COMMENT '阈值下限',
ceil DOUBLE(5, 2) NOT NULL COMMENT '阈值上限',
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态，0unuse，1inuse',
PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='报警阈值';

DROP TABLE IF EXISTS tmp_data;
CREATE TABLE tmp_data(
  field_id VARCHAR(255) NOT NULL COMMENT '大棚编号',
  val DOUBLE(5, 2) DEFAULT  NULL COMMENT '临时数据值'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='大棚临时数据表';


/**********
 * trigger
**********/

/*
 * data_record新增记录-》即时更新大棚数据
 */
DELIMITER //
DROP PROCEDURE IF EXISTS update_field_status;
/*
 * 当数据记录表新增数据时，触发大棚状态表的相关字段更新
 * 触发器中不支持使用动态sql！
 */
CREATE PROCEDURE update_field_status(IN type VARCHAR(255), IN val DOUBLE(5, 2), IN f_id VARCHAR(255))
  BEGIN
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
  END;

/*
 * data_record表触发器
 */
DROP TRIGGER IF EXISTS after_data_insert;
CREATE TRIGGER after_data_insert
AFTER INSERT ON data_record
FOR EACH ROW
  BEGIN
    DECLARE f_id VARCHAR(255) DEFAULT '';
    SET f_id = (SELECT field_id
                FROM sensor
                WHERE sensor_id = NEW.sensor_id);
    CALL update_field_status(NEW.data_type, NEW.val, f_id);
  END //
DELIMITER ;


/**********
 * procedure
 **********/

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
    DECLARE ts_floor, ts_ceil DOUBLE(5, 2);
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
CREATE PROCEDURE check_warn_compare(IN ts_code VARCHAR(255), IN ts_floor DOUBLE(5, 2), IN ts_ceil DOUBLE(5, 2))
  BEGIN
    DECLARE fs_id VARCHAR(255);
    DECLARE fs_val DOUBLE(5, 2);
    DECLARE val_end, record_id INT DEFAULT 0;

    /* 定义该项阈值对应所有大棚即时状态数据游标 */
    DECLARE val_cursor CURSOR FOR SELECT field_id, val FROM tmp_data;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET val_end = 1;

    OPEN val_cursor;

    WHILE val_end != 1 DO
      FETCH val_cursor INTO fs_id, fs_val;
      /* 数据值不为空时才比较 */
      IF fs_val IS NOT NULL
      THEN
        /* 不在规定阈值范围内 */
        IF fs_val < ts_floor || fs_val > ts_ceil
        THEN
          /* 查找是否存在相应大棚未处理的同类型报警记录 */
          SELECT id INTO record_id FROM warn_record WHERE field_id = fs_id AND warn_type = ts_code AND flag = '0';
          IF record_id = 0 THEN
            /* 不存在相应报警记录，向表中插入 */
            INSERT INTO warn_record (field_id, warn_type, warn_val) VALUES (fs_id, ts_code, fs_val);
          ELSE
            /* 若存在，更新该报警记录 */
            UPDATE warn_record SET warn_val = fs_val, warn_time = NOW(), warn_count = warn_count + 1
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


/*********
 * data
 **********/

/*
 * block
 */
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b01', '沛县现代农业产业园区', '朱寨镇、沛城镇、鹿楼镇、张寨镇、经济开发区', '近郊城市农业区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b02', '胡寨草庙千亩长茄示范园', '胡寨镇', '东部优质粮食主产区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b03', '沛城万亩精品农业示范园', '沛城镇', '近郊城市农业区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b04', '张寨万亩农业生态园', '张寨镇', '南部高效设施园艺区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b05', '朱寨现代农业科技示范园', '朱寨镇', '西部高效生态养殖区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b06', '鹿楼种养生态示范园', '鹿楼镇', '西部高效生态养殖区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b07', '河口设施农业示范园', '河口镇', '南部高效设施园艺区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b08', '敬安循环农业示范园', '敬安镇', '南部高效设施园艺区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b09', '安国生态农业观光园', '安国镇', '南部高效设施园艺区');

/*
 * field
 */
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1701001', '加温温室', 'b01', 'c001', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1701002', '双屋面温室', 'b01', 'c002', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1701003', '透光塑料大棚', 'b01', 'c003', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1701004', '塑料大棚', 'b01', 'c004', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1702001', '加温温室', 'b02', 'c001', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1702002', '双屋面温室', 'b02', 'c002', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1702003', '透光塑料大棚', 'b02', 'c003', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1702004', '塑料大棚', 'b02', 'c004', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1703001', '加温温室', 'b03', 'c001', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1703002', '双屋面温室', 'b03', 'c002', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1703003', '透光塑料大棚', 'b03', 'c003', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1703004', '塑料大棚', 'b03', 'c004', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1704001', '加温温室', 'b04', 'c001', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1704002', '双屋面温室', 'b04', 'c002', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1704003', '透光塑料大棚', 'b04', 'c003', '1', null);
INSERT INTO wa.field (field_id, field_name, block_id, crop_id, use_status, field_ps) VALUES ('f1704004', '塑料大棚', 'b04', 'c004', '1', null);

/*
 * crop
 */
INSERT INTO wa.crop (crop_id, crop_name, crop_ps) VALUES ('c001', '玉米', null);
INSERT INTO wa.crop (crop_id, crop_name, crop_ps) VALUES ('c002', '生菜', null);
INSERT INTO wa.crop (crop_id, crop_name, crop_ps) VALUES ('c003', '花生', null);
INSERT INTO wa.crop (crop_id, crop_name, crop_ps) VALUES ('c004', '大豆', null);

/*
 * employee
 */
INSERT INTO wa.employee (emp_id, emp_name, emp_tel, emp_position, emp_age, emp_sex, emp_ps) VALUES ('e001', 'wch1', '15261861234', null, null, null, null);
INSERT INTO wa.employee (emp_id, emp_name, emp_tel, emp_position, emp_age, emp_sex, emp_ps) VALUES ('e002', 'wch2', '15261861234', null, null, null, null);
INSERT INTO wa.employee (emp_id, emp_name, emp_tel, emp_position, emp_age, emp_sex, emp_ps) VALUES ('e003', 'wch3', '15261861234', null, null, null, null);

/*
 * sensor
 */
INSERT INTO wa.sensor (sensor_id, sensor_func, sensor_type, field_id, use_status, sensor_ps) VALUES ('s-01-001', '1', 'abc001', 'f1701001', '1', null);
INSERT INTO wa.sensor (sensor_id, sensor_func, sensor_type, field_id, use_status, sensor_ps) VALUES ('s-01-002', '1', 'abc001', 'f1701002', '1', null);
INSERT INTO wa.sensor (sensor_id, sensor_func, sensor_type, field_id, use_status, sensor_ps) VALUES ('s-02-001', '2', 'abc002', 'f1701003', '1', null);
INSERT INTO wa.sensor (sensor_id, sensor_func, sensor_type, field_id, use_status, sensor_ps) VALUES ('s-02-002', '2', 'abc002', 'f1701004', '1', null);

/*
 * machine
 */
INSERT INTO wa.machine (machine_id, machine_type, block_id, use_status, machine_ps) VALUES ('m001', 'cba001', 'b01', '0', null);
INSERT INTO wa.machine (machine_id, machine_type, block_id, use_status, machine_ps) VALUES ('m002', 'cba002', 'b02', '0', null);
INSERT INTO wa.machine (machine_id, machine_type, block_id, use_status, machine_ps) VALUES ('m003', 'cba003', 'b03', '0', null);
INSERT INTO wa.machine (machine_id, machine_type, block_id, use_status, machine_ps) VALUES ('m004', 'cba004', 'b04', '0', null);

/*
 * vehicle
 */
INSERT INTO wa.vehicle (vehicle_id, vehicle_type, block_id, use_status, vehicle_ps) VALUES ('v001', 'xyz001', 'b01', '0', null);
INSERT INTO wa.vehicle (vehicle_id, vehicle_type, block_id, use_status, vehicle_ps) VALUES ('v002', 'xyz002', 'b02', '0', null);
INSERT INTO wa.vehicle (vehicle_id, vehicle_type, block_id, use_status, vehicle_ps) VALUES ('v003', 'xyz003', 'b03', '0', null);
INSERT INTO wa.vehicle (vehicle_id, vehicle_type, block_id, use_status, vehicle_ps) VALUES ('v004', 'xyz004', 'b04', '0', null);

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
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-001', '1', 19.15, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-002', '2', 25.25, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-001', '3', 25.25, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-002', '4', 25.25, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-001', '5', 25.25, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-002', '6', 25.25, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-001', '7', 1, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-002', '8', 0.34, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-001', '9', 0.35, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-002', '10', 0.35, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-001', '11', 0.35, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-002', '12', 0.35, NULL);

/*
 * warn_threshold
 */
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (1, '1', 20, 75, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (2, '2', 20, 75, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (3, '3', 20, 75, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (4, '4', 20, 75, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (5, '5', 20, 75, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (6, '6', 20, 75, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (7, '7', 3, 9, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (8, '8', 0.35, 2.65, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (9, '9', 0.35, 2.65, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (10, '10', 0.35, 2.65, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (11, '11', 0.35, 2.65, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (12, '12', 0.35, 2.65, '1');

/*
 * warn_record
 */
INSERT INTO wa.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag) VALUES (1, 'f1701001', '1', 1.23, NULL, NULL, '0');
INSERT INTO wa.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag) VALUES (2, 'f1701002', '2', 3.45, NULL, NULL, '0');
INSERT INTO wa.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag) VALUES (3, 'f1701003', '3', 5.98, NULL, NULL, '0');
INSERT INTO wa.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag) VALUES (4, 'f1701004', '4', 9.58, NULL, NULL, '0');
INSERT INTO wa.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag) VALUES (5, 'f1702001', '5', 5.25, NULL, NULL, '0');
/* 通过扫描field_status表插入数据 */
/* CALL check_warn(); */


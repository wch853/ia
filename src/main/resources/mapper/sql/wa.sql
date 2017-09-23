----- DDL -----

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
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态，0unuse，1inuse',
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
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态，0unuse, 1inuse, 2error',
sensor_ps VARCHAR(255) DEFAULT NULL COMMENT '传感器备注',
PRIMARY KEY (sensor_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='传感器';

DROP TABLE IF EXISTS machine;
CREATE TABLE machine (
machine_id VARCHAR(255) NOT NULL COMMENT '机械编号',
machine_type VARCHAR(255) NOT NULL COMMENT '机械型号',
block_id VARCHAR(255) DEFAULT NULL COMMENT '所属地块编号',
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态，0unuse, 1inuse, 2error',
machine_ps VARCHAR(255) DEFAULT NULL COMMENT '机械备注',
PRIMARY KEY (machine_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='机械';

DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle (
vehicle_id VARCHAR(255) NOT NULL COMMENT '车辆编号',
vehicle_type VARCHAR(255) NOT NULL COMMENT '车辆型号',
block_id VARCHAR(255) DEFAULT NULL COMMENT '所属地块编号',
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态，0unuse, 1inuse, 2error',
vehicle_ps VARCHAR(255) DEFAULT NULL COMMENT '车辆备注',
PRIMARY KEY (vehicle_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='车辆';

DROP TABLE IF EXISTS data_record;
CREATE TABLE data_record (
id INT AUTO_INCREMENT NOT NULL COMMENT '数据记录编号',
sensor_id VARCHAR(255) NOT NULL COMMENT '来源传感器编号',
data_type VARCHAR(255) NOT NULL COMMENT '数据类型 1-温度temperature 2-湿度moisture 3-土壤温度 soil_temperature 4-土壤水分soil_moisture 5-光照 light 6-二氧化碳 co2 7-pH ph 8-氮含量n 9-磷含量p 10-钾含量k 11-汞含量hg 12-铅含量pb',
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
warn_type VARCHAR(255) NOT NULL COMMENT '报警类型',
warn_val DOUBLE(5, 2) DEFAULT NULL COMMENT '报警值',
warn_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '报警时间',
handle_time TIMESTAMP NULL COMMENT '处理时间',
flag VARCHAR(255) NOT NULL DEFAULT '0' COMMENT '处理标志 0-未处理 1-已处理 2-已忽略',
PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='报警记录';

DROP TABLE IF EXISTS warn_threshold;
CREATE TABLE warn_threshold (
id INT AUTO_INCREMENT NOT NULL COMMENT '报警阈值编号',
threshold_type VARCHAR(255) NOT NULL COMMENT '阈值类型',
floor DOUBLE(5, 2) NOT NULL COMMENT '阈值下限',
ceil DOUBLE(5, 2) NOT NULL COMMENT '阈值上限',
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态，0unuse，1inuse',
PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='报警阈值';


----- trigger -----

-- 当数据记录表新增数据时，触发大棚状态表的相关字段更新
DELIMITER //
DROP PROCEDURE IF EXISTS update_field_status;
CREATE PROCEDURE update_field_status(IN type VARCHAR(255), IN val INT, IN f_id VARCHAR(255))
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


----- data -----
-- block
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b01', '沛县现代农业产业园区', '朱寨镇、沛城镇、鹿楼镇、张寨镇、经济开发区', '近郊城市农业区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b02', '胡寨草庙千亩长茄示范园', '胡寨镇', '东部优质粮食主产区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b03', '沛城万亩精品农业示范园', '沛城镇', '近郊城市农业区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b04', '张寨万亩农业生态园', '张寨镇', '南部高效设施园艺区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b05', '朱寨现代农业科技示范园', '朱寨镇', '西部高效生态养殖区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b06', '鹿楼种养生态示范园', '鹿楼镇', '西部高效生态养殖区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b07', '河口设施农业示范园', '河口镇', '南部高效设施园艺区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b08', '敬安循环农业示范园', '敬安镇', '南部高效设施园艺区');
INSERT INTO wa.block (block_id, block_name, block_loc, block_ps) VALUES ('b09', '安国生态农业观光园', '安国镇', '南部高效设施园艺区');

-- field
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

-- crop
INSERT INTO wa.crop (crop_id, crop_name, crop_ps) VALUES ('c001', '玉米', null);
INSERT INTO wa.crop (crop_id, crop_name, crop_ps) VALUES ('c002', '生菜', null);
INSERT INTO wa.crop (crop_id, crop_name, crop_ps) VALUES ('c003', '花生', null);
INSERT INTO wa.crop (crop_id, crop_name, crop_ps) VALUES ('c004', '大豆', null);

-- employee
INSERT INTO wa.employee (emp_id, emp_name, emp_tel, emp_position, emp_age, emp_sex, emp_ps) VALUES ('e001', 'wch1', '15261861234', null, null, null, null);
INSERT INTO wa.employee (emp_id, emp_name, emp_tel, emp_position, emp_age, emp_sex, emp_ps) VALUES ('e002', 'wch2', '15261861234', null, null, null, null);
INSERT INTO wa.employee (emp_id, emp_name, emp_tel, emp_position, emp_age, emp_sex, emp_ps) VALUES ('e003', 'wch3', '15261861234', null, null, null, null);

-- sensor
INSERT INTO wa.sensor (sensor_id, sensor_func, sensor_type, field_id, use_status, sensor_ps) VALUES ('s-01-001', '1', 'abc001', 'f1701001', '1', null);
INSERT INTO wa.sensor (sensor_id, sensor_func, sensor_type, field_id, use_status, sensor_ps) VALUES ('s-01-002', '1', 'abc001', 'f1701002', '1', null);
INSERT INTO wa.sensor (sensor_id, sensor_func, sensor_type, field_id, use_status, sensor_ps) VALUES ('s-02-001', '2', 'abc002', 'f1701003', '1', null);
INSERT INTO wa.sensor (sensor_id, sensor_func, sensor_type, field_id, use_status, sensor_ps) VALUES ('s-02-002', '2', 'abc002', 'f1701004', '1', null);

-- machine
INSERT INTO wa.machine (machine_id, machine_type, block_id, use_status, machine_ps) VALUES ('m001', 'cba001', 'b01', '0', null);
INSERT INTO wa.machine (machine_id, machine_type, block_id, use_status, machine_ps) VALUES ('m002', 'cba002', 'b02', '0', null);
INSERT INTO wa.machine (machine_id, machine_type, block_id, use_status, machine_ps) VALUES ('m003', 'cba003', 'b03', '0', null);
INSERT INTO wa.machine (machine_id, machine_type, block_id, use_status, machine_ps) VALUES ('m004', 'cba004', 'b04', '0', null);

-- vehicle
INSERT INTO wa.vehicle (vehicle_id, vehicle_type, block_id, use_status, vehicle_ps) VALUES ('v001', 'xyz001', 'b01', '0', null);
INSERT INTO wa.vehicle (vehicle_id, vehicle_type, block_id, use_status, vehicle_ps) VALUES ('v002', 'xyz002', 'b02', '0', null);
INSERT INTO wa.vehicle (vehicle_id, vehicle_type, block_id, use_status, vehicle_ps) VALUES ('v003', 'xyz003', 'b03', '0', null);
INSERT INTO wa.vehicle (vehicle_id, vehicle_type, block_id, use_status, vehicle_ps) VALUES ('v004', 'xyz004', 'b04', '0', null);

-- field_status
DELIMITER //
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
  END //
DELIMITER ;

CALL add_field_status_data('f170100');
CALL add_field_status_data('f170200');
CALL add_field_status_data('f170300');
CALL add_field_status_data('f170400');

-- data_record
TRUNCATE data_record;
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-001', '1', 35.22, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-002', '2', 45.22, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-001', '3', 23.69, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-002', '4', 48.56, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-001', '5', 27.36, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-002', '6', 35.35, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-001', '7', 56.23, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-002', '8', 12.56, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-001', '9', 25.5, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-01-002', '10', 96.63, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-001', '11', 15.3, NULL);
INSERT INTO wa.data_record (id, sensor_id, data_type, val, record_time) VALUES (NULL, 's-02-002', '12', 96.8, NULL);

-- warn_threshold
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (1, '1', 12.22, 42.23, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (2, '2', 16.36, 86.35, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (3, '3', 15.36, 86.85, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (4, '4', 15.36, 56.35, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (5, '5', 56.36, 76.36, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (6, '6', 23.26, 43.36, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (7, '7', 3, 9, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (8, '8', 0.35, 2.65, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (9, '9', 0.35, 2.65, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (10, '10', 0.35, 2.65, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (11, '11', 0.35, 2.65, '1');
INSERT INTO wa.warn_threshold (id, threshold_type, floor, ceil, use_status) VALUES (12, '12', 0.35, 2.65, '1');

-- warn_record
INSERT INTO wa.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag) VALUES (1, 'f1701001', '1', 1.23, '2017-09-20 20:25:09', null, '0');
INSERT INTO wa.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag) VALUES (2, 'f1701002', '2', 3.45, '2017-09-20 20:26:28', null, '0');
INSERT INTO wa.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag) VALUES (3, 'f1701003', '3', 5.98, '2017-09-20 20:26:28', null, '0');
INSERT INTO wa.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag) VALUES (4, 'f1701004', '4', 9.58, '2017-09-20 20:26:28', null, '0');
INSERT INTO wa.warn_record (id, field_id, warn_type, warn_val, warn_time, handle_time, flag) VALUES (5, 'f1702001', '5', 5.25, NULL, NULL, '0');


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
emp_sex VARCHAR(255) DEFAULT NULL COMMENT '员工联系方式',
emp_ps VARCHAR(255) DEFAULT NULL COMMENT '员工备注',
PRIMARY KEY (emp_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='员工';

DROP TABLE IF EXISTS sensor;
CREATE TABLE sensor (
sensor_id VARCHAR(255) NOT NULL COMMENT '传感器编号',
sensor_fun VARCHAR(255) NOT NULL COMMENT '传感器功能类型',
sensor_type VARCHAR(255) NOT NULL COMMENT '传感器型号',
field_id VARCHAR(255) DEFAULT NULL COMMENT '所属大棚编号',
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态，0unuse，1inuse',
sensor_ps VARCHAR(255) DEFAULT NULL COMMENT '传感器备注',
PRIMARY KEY (sensor_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='传感器';

DROP TABLE IF EXISTS machine;
CREATE TABLE machine (
machine_id VARCHAR(255) NOT NULL COMMENT '机械编号',
machine_type VARCHAR(255) NOT NULL COMMENT '机械型号',
block_id VARCHAR(255) DEFAULT NULL COMMENT '所属地块编号',
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态，0unuse，1inuse',
machine_ps VARCHAR(255) DEFAULT NULL COMMENT '机械备注',
PRIMARY KEY (machine_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='机械';

DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle (
veh_id VARCHAR(255) NOT NULL COMMENT '车辆编号',
veh_type VARCHAR(255) NOT NULL COMMENT '车辆型号',
block_id VARCHAR(255) DEFAULT NULL COMMENT '所属地块编号',
use_status VARCHAR(255) DEFAULT '0' NOT NULL COMMENT '使用状态，0unuse，1inuse',
veh_ps VARCHAR(255) DEFAULT NULL COMMENT '车辆备注',
PRIMARY KEY (veh_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='车辆';

DROP TABLE IF EXISTS soil_status;
CREATE TABLE soil_status (
id INT NOT NULL auto_increment COMMENT '测量记录编号',
sensor_id VARCHAR(255) NOT NULL COMMENT '来源传感器编号',
measure_time TIMESTAMP NOT NULL COMMENT '测量时间',
n_content INT DEFAULT NULL COMMENT '氮含量',
p_content INT DEFAULT NULL COMMENT '磷含量',
k_content INT DEFAULT NULL COMMENT '钾含量',
hg_content INT DEFAULT NULL COMMENT '汞含量',
pb_content INT DEFAULT NULL COMMENT '铅含量',
PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='土壤墒情';

DROP TABLE IF EXISTS environment_status;
CREATE TABLE environment_status (
id INT NOT NULL auto_increment COMMENT '测量记录编号',
sensor_id VARCHAR(255) NOT NULL COMMENT '来源传感器编号',
measure_time TIMESTAMP NOT NULL COMMENT '测量时间',
temperature INT DEFAULT NULL COMMENT '温度',
moisture INT DEFAULT NULL COMMENT '湿度',
PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='环境参数';

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
INSERT INTO wa.sensor (sensor_id, sensor_fun, sensor_type, field_id, use_status, sensor_ps) VALUES ('s-01-001', '1', 'abc001', 'f1701001', '1', null);
INSERT INTO wa.sensor (sensor_id, sensor_fun, sensor_type, field_id, use_status, sensor_ps) VALUES ('s-01-002', '1', 'abc001', 'f1701002', '1', null);
INSERT INTO wa.sensor (sensor_id, sensor_fun, sensor_type, field_id, use_status, sensor_ps) VALUES ('s-02-001', '2', 'abc002', 'f1701003', '1', null);
INSERT INTO wa.sensor (sensor_id, sensor_fun, sensor_type, field_id, use_status, sensor_ps) VALUES ('s-02-002', '2', 'abc002', 'f1701004', '1', null);
-- machine
INSERT INTO wa.machine (machine_id, machine_type, block_id, use_status, machine_ps) VALUES ('m001', 'cba001', 'b01', '0', null);
INSERT INTO wa.machine (machine_id, machine_type, block_id, use_status, machine_ps) VALUES ('m002', 'cba002', 'b02', '0', null);
INSERT INTO wa.machine (machine_id, machine_type, block_id, use_status, machine_ps) VALUES ('m003', 'cba003', 'b03', '0', null);
INSERT INTO wa.machine (machine_id, machine_type, block_id, use_status, machine_ps) VALUES ('m004', 'cba004', 'b04', '0', null);
-- vehicle
INSERT INTO wa.vehicle (veh_id, veh_type, block_id, use_status, veh_ps) VALUES ('v001', 'xyz001', 'b01', '0', null);
INSERT INTO wa.vehicle (veh_id, veh_type, block_id, use_status, veh_ps) VALUES ('v002', 'xyz002', 'b02', '0', null);
INSERT INTO wa.vehicle (veh_id, veh_type, block_id, use_status, veh_ps) VALUES ('v003', 'xyz003', 'b03', '0', null);
INSERT INTO wa.vehicle (veh_id, veh_type, block_id, use_status, veh_ps) VALUES ('v004', 'xyz004', 'b04', '0', null);
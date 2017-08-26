package com.njfu.wa.sys.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.njfu.wa.sys.domain.*;
import com.njfu.wa.sys.service.*;
import com.njfu.wa.sys.util.Message;
import com.njfu.wa.sys.util.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统档案管理控制器
 */
@Controller
@RequestMapping("/sys/file")
public class FileController {

    @Autowired
    private BlockService blockService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private CropService cropService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private SensorService sensorService;

    /**
     * 档案页首页
     *
     * @return page
     */
    @RequestMapping
    public String file() {
        return "sys/file/file";
    }

    /**
     * 地块档案页
     *
     * @return page
     */
    @RequestMapping("/block")
    public String blockFile() {
        return "sys/file/blockFile";
    }

    /**
     * 获取地块列表
     *
     * @param offset offset
     * @param limit  limit
     * @param block  blockId blockName
     * @return json data
     */
    @RequestMapping("/getBlocks")
    public @ResponseBody
    PaginationResult getBlocks(int offset, int limit, Block block) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Block> blocks = blockService.getBlocks(block);
        return new PaginationResult(page.getTotal(), blocks);
    }

    /**
     * 新增地块信息
     *
     * @param block blockId blockName blockLoc blockPs
     * @return json message
     */
    @PostMapping("/addBlock")
    public @ResponseBody
    Message addBlock(Block block) {
        return blockService.addBlock(block);
    }

    /**
     * 修改地块信息
     *
     * @param block blockId blockName blockLoc blockPs
     * @return json message
     */
    @PostMapping("/modifyBlock")
    public @ResponseBody
    Message modifyBlock(Block block) {
        return blockService.modifyBlock(block);
    }

    /**
     * 删除地块信息
     *
     * @param block blockId
     * @return json message
     */
    @PostMapping("/removeBlock")
    public @ResponseBody
    Message removeBlock(Block block) {
        return blockService.removeBlock(block);
    }

    /**
     * 大棚档案页
     *
     * @param model blocks crops
     * @return page
     */
    @RequestMapping("/field")
    public String fieldFile(Model model) {
        List<Block> blocks = blockService.getBlocks(new Block());
        List<Crop> crops = cropService.getCrops(new Crop());
        model.addAttribute("blocks", blocks);
        model.addAttribute("crops", crops);
        return "sys/file/fieldFile";
    }

    /**
     * 获取大棚列表
     *
     * @param offset offset
     * @param limit  limit
     * @param field  fieldName useStatus
     * @param block  blockId
     * @param crop   cropId
     * @return json data
     */
    @RequestMapping("/getFields")
    public @ResponseBody
    PaginationResult getFields(int offset, int limit, Field field, Block block, Crop crop) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Field> fields = fieldService.getFields(field, block, crop);
        return new PaginationResult(page.getTotal(), fields);
    }

    /**
     * 新增大棚信息
     *
     * @param field fieldId fieldName useStatus fieldPs
     * @param block blockId
     * @param crop  cropId
     * @return json message
     */
    @PostMapping("/addField")
    public @ResponseBody
    Message addField(Field field, Block block, Crop crop) {
        return fieldService.addField(field, block, crop);
    }

    /**
     * 修改大棚信息
     *
     * @param field fieldId fieldName useStatus fieldPs
     * @param block blockId
     * @param crop  cropId
     * @return json message
     */
    @PostMapping("/modifyField")
    public @ResponseBody
    Message modifyField(Field field, Block block, Crop crop) {
        return fieldService.modifyField(field, block, crop);
    }

    /**
     * 移除大棚信息
     *
     * @param field fieldId
     * @return json message
     */
    @PostMapping("/removeField")
    public @ResponseBody
    Message removeField(Field field) {
        return fieldService.removeField(field);
    }

    /**
     * 作物档案页
     *
     * @return page
     */
    @RequestMapping("/crop")
    public String cropFile() {
        return "sys/file/cropFile";
    }

    /**
     * 获取作物列表
     *
     * @param offset offset
     * @param limit  limit
     * @param crop   cropId cropName
     * @return json data
     */
    @RequestMapping("/getCrops")
    public @ResponseBody
    PaginationResult getCrops(int offset, int limit, Crop crop) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Crop> crops = cropService.getCrops(crop);
        return new PaginationResult(page.getTotal(), crops);
    }

    /**
     * 新增作物信息
     *
     * @param crop cropId cropName cropPs
     * @return json message
     */
    @PostMapping("/addCrop")
    public @ResponseBody
    Message addCrop(Crop crop) {
        return cropService.addCrop(crop);
    }

    /**
     * 修改作物信息
     *
     * @param crop cropId cropName cropPs
     * @return json message
     */
    @PostMapping("/modifyCrop")
    public @ResponseBody
    Message modifyCrop(Crop crop) {
        return cropService.modifyCrop(crop);
    }

    /**
     * 删除作物信息
     *
     * @param crop cropId
     * @return json message
     */
    @PostMapping("/removeCrop")
    public @ResponseBody
    Message removeCrop(Crop crop) {
        return cropService.removeCrop(crop);
    }

    /**
     * 员工档案页
     *
     * @return page
     */
    @RequestMapping("/employee")
    public String employeeFile() {
        return "sys/file/employeeFile";
    }

    /**
     * 获取员工列表
     *
     * @param offset   offset
     * @param limit    limit
     * @param employee empId empName
     * @return json data
     */
    @RequestMapping("/getEmployees")
    public @ResponseBody
    PaginationResult getEmployees(int offset, int limit, Employee employee) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Employee> employees = employeeService.getEmployees(employee);
        return new PaginationResult(page.getTotal(), employees);
    }

    /**
     * 新增员工信息
     *
     * @param employee empId empName empTel empPosition empAge empSex empPs
     * @return json message
     */
    @PostMapping("/addEmployee")
    public @ResponseBody
    Message addEmployee(Employee employee) {
        return employeeService.addEmployee(employee);
    }

    /**
     * 修改员工信息
     *
     * @param employee empId empName empTel empPosition empAge empSex empPs
     * @return json message
     */
    @PostMapping("/modifyEmployee")
    public @ResponseBody
    Message modifyEmployee(Employee employee) {
        return employeeService.modifyEmployee(employee);
    }

    /**
     * 删除员工信息
     *
     * @param employee empId
     * @return json message
     */
    @PostMapping("/removeEmployee")
    public @ResponseBody
    Message removeEmployee(Employee employee) {
        return employeeService.removeEmployee(employee);
    }

    /**
     * 机械档案页
     *
     * @param model blocks
     * @return page
     */
    @RequestMapping("/machine")
    public String machineFile(Model model) {
        List<Block> blocks = blockService.getBlocks(new Block());
        model.addAttribute("blocks", blocks);
        return "sys/file/machineFile";
    }

    /**
     * 获取机械列表
     *
     * @param offset  offset
     * @param limit   limit
     * @param machine machineId machineType useStatus machinePs
     * @param block   blockId
     * @return json data
     */
    @RequestMapping("/getMachines")
    public @ResponseBody
    PaginationResult getMachines(int offset, int limit, Machine machine, Block block) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Machine> machines = machineService.getMachines(machine, block);
        return new PaginationResult(page.getTotal(), machines);
    }

    /**
     * 新增机械信息
     *
     * @param machine 　machineId machineType useStatus machinePs
     * @param block   　blockId
     * @return json message
     */
    @PostMapping("/addMachine")
    public @ResponseBody
    Message addMachine(Machine machine, Block block) {
        return machineService.addMachine(machine, block);
    }

    /**
     * 修改机械信息
     *
     * @param machine 　machineId machineType useStatus machinePs
     * @param block   　blockId
     * @return json message
     */
    @PostMapping("/modifyMachine")
    public @ResponseBody
    Message modifyMachine(Machine machine, Block block) {
        return machineService.modifyMachine(machine, block);
    }

    /**
     * 删除机械信息
     *
     * @param machine 　machineId
     * @return json message
     */
    @PostMapping("/removeMachine")
    public @ResponseBody
    Message removeMachine(Machine machine) {
        return machineService.removeMachine(machine);
    }

    /**
     * 车辆档案页
     *
     * @return page
     */
    @RequestMapping("/vehicle")
    public String vehicleFile(Model model) {
        List<Block> blocks = blockService.getBlocks(new Block());
        model.addAttribute("blocks", blocks);
        return "sys/file/vehicleFile";
    }

    /**
     * 获取车辆列表
     *
     * @param offset  offset
     * @param limit   limit
     * @param vehicle vehicleId vehicleType useStatus vehiclePs
     * @param block   blockId
     * @return json data
     */
    @RequestMapping("/getVehicles")
    public @ResponseBody
    PaginationResult getVehicles(int offset, int limit, Vehicle vehicle, Block block) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Vehicle> vehicles = vehicleService.getVehicles(vehicle, block);
        return new PaginationResult(page.getTotal(), vehicles);
    }

    /**
     * 新增车辆信息
     *
     * @param vehicle vehicleId vehicleType useStatus vehiclePs
     * @param block   blockId
     * @return json message
     */
    @RequestMapping("/addVehicle")
    public @ResponseBody
    Message addVehicle(Vehicle vehicle, Block block) {
        return vehicleService.addVehicle(vehicle, block);
    }

    /**
     * 修改车辆信息
     *
     * @param vehicle vehicleId vehicleType useStatus vehiclePs
     * @param block   blockId
     * @return json message
     */
    @RequestMapping("/modifyVehicle")
    public @ResponseBody
    Message modifyVehicle(Vehicle vehicle, Block block) {
        return vehicleService.modifyVehicle(vehicle, block);
    }

    /**
     * 删除车辆档案
     *
     * @param vehicle vehicleId
     * @return json message
     */
    @RequestMapping("/removeVehicle")
    public @ResponseBody
    Message removeVehicle(Vehicle vehicle) {
        return vehicleService.removeVehicle(vehicle);
    }

    /**
     * 传感器档案页
     *
     * @return page
     */
    @RequestMapping("/sensor")
    public String sensorFile(Model model) {
        List<Field> fields = fieldService.getFields(new Field(), new Block(), new Crop());
        model.addAttribute("fields", fields);
        return "sys/file/sensorFile";
    }

    /**
     * 获取传感器列表
     *
     * @param offset offset
     * @param limit  limit
     * @param sensor sensorId sensorFunc sensorType useStatus sensorPs
     * @param field  fieldId
     * @return json data
     */
    @RequestMapping("/getSensors")
    public @ResponseBody
    PaginationResult getSensors(int offset, int limit, Sensor sensor, Field field) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Sensor> sensors = sensorService.getSensors(sensor, field);
        return new PaginationResult(page.getTotal(), sensors);
    }

    /**
     * 新增传感器信息
     *
     * @param sensor sensorId sensorFunc sensorType useStatus sensorPs
     * @param field  fieldId
     * @return json message
     */
    @RequestMapping("/addSensor")
    public @ResponseBody
    Message addSensor(Sensor sensor, Field field) {
        return sensorService.addSensor(sensor, field);
    }

    /**
     * 修改传感器信息
     *
     * @param sensor sensorId sensorFunc sensorType useStatus sensorPs
     * @param field  fieldId
     * @return json message
     */
    @RequestMapping("/modifySensor")
    public @ResponseBody
    Message modifySensor(Sensor sensor, Field field) {
        return sensorService.modifySensor(sensor, field);
    }

    /**
     * 删除传感器信息
     *
     * @param sensor sensorId
     * @return json message
     */
    @RequestMapping("/removeSensor")
    public @ResponseBody
    Message removeSensor(Sensor sensor) {
        return sensorService.removeSensor(sensor);
    }
}

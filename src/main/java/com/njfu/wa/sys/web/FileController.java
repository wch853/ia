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
     * @param block block
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
     * @param block block
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
     * @param block block
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
     * @param model data
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
     * @param offset  offset
     * @param limit   limit
     * @param field   field
     * @param blockId blockId
     * @param cropId  cropId
     * @return json data
     */
    @RequestMapping("/getFields")
    public @ResponseBody
    PaginationResult getFields(int offset, int limit, Field field, String blockId, String cropId) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Field> fields = fieldService.getFields(field, blockId, cropId);
        return new PaginationResult(page.getTotal(), fields);
    }

    /**
     * 新增大棚信息
     *
     * @param field   field
     * @param blockId blockId
     * @param cropId  cropId
     * @return json message
     */
    @PostMapping("/addField")
    public @ResponseBody
    Message addField(Field field, String blockId, String cropId) {
        return fieldService.addField(field, blockId, cropId);
    }

    /**
     * 修改大棚信息
     *
     * @param field   field
     * @param blockId blockId
     * @param cropId  cropId
     * @return json message
     */
    @PostMapping("/modifyField")
    public @ResponseBody
    Message modifyField(Field field, String blockId, String cropId) {
        return fieldService.modifyField(field, blockId, cropId);
    }

    /**
     * 移除大棚信息
     *
     * @param fieldId fieldId
     * @return json message
     */
    @PostMapping("/removeField")
    public @ResponseBody
    Message removeField(String fieldId) {
        return fieldService.removeField(fieldId);
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
     * @param crop   crop
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
     * @param crop crop
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
     * @param crop crop
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
     * @param crop
     * @return
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
     * @param offset offset
     * @param limit  limit
     * @return json data
     */
    @RequestMapping("/getEmployees")
    public @ResponseBody
    PaginationResult getEmployees(int offset, int limit) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Employee> employees = employeeService.getEmployees();
        return new PaginationResult(page.getTotal(), employees);
    }

    /**
     * 机械档案页
     *
     * @return
     */
    @RequestMapping("/machine")
    public String machineFile() {
        return "sys/file/machineFile";
    }

    /**
     * 获取机械列表
     *
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping("/getMachines")
    public @ResponseBody
    PaginationResult getMachines(int offset, int limit) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Machine> machines = machineService.getMachines();
        return new PaginationResult(page.getTotal(), machines);
    }

    /**
     * 车辆档案页
     *
     * @return
     */
    @RequestMapping("/vehicle")
    public String vehicleFile() {
        return "sys/file/vehicleFile";
    }

    /**
     * 获取车辆列表
     *
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping("/getVehicles")
    public @ResponseBody
    PaginationResult getVehicles(int offset, int limit) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Vehicle> vehicles = vehicleService.getVehicles();
        return new PaginationResult(page.getTotal(), vehicles);
    }

    /**
     * 传感器档案页
     *
     * @return
     */
    @RequestMapping("/sensor")
    public String sensorFile() {
        return "sys/file/sensorFile";
    }

    /**
     * 获取传感器列表
     *
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping("/getSensors")
    public @ResponseBody
    PaginationResult getSensors(int offset, int limit) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Sensor> sensors = sensorService.getSensors();
        return new PaginationResult(page.getTotal(), sensors);
    }
}

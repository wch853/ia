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

    @RequestMapping
    public String file() {
        return "sys/file/file";
    }

    @RequestMapping("/block")
    public String blockFile() {
        return "sys/file/blockFile";
    }

    @RequestMapping("/getBlocks")
    public @ResponseBody
    PaginationResult getBlocks(int offset, int limit) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Block> blocks = blockService.getBlocks();
        return new PaginationResult(page.getTotal(), blocks);
    }

    @RequestMapping("/field")
    public String fieldFile(Model model) {
        List<Block> blocks = blockService.getBlocks();
        List<Crop> crops = cropService.getCrops();
        model.addAttribute("blocks", blocks);
        model.addAttribute("crops", crops);
        return "sys/file/fieldFile";
    }

    @RequestMapping("/getFields")
    public @ResponseBody
    PaginationResult getFields(int offset, int limit, String fieldName, String blockId, String cropId, String useStatus) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Field> fields = fieldService.getFields(fieldName, blockId, cropId, useStatus);
        return new PaginationResult(page.getTotal(), fields);
    }

    @PostMapping("/addField")
    public @ResponseBody
    Message addField(String fieldId, String fieldName, String blockId, String cropId, String useStatus, String fieldPs) {
        return fieldService.addField(fieldId, fieldName, blockId, cropId, useStatus, fieldPs);
    }

    @PostMapping("/modifyField")
    public @ResponseBody
    Message modifyField(String fieldId, String fieldName, String blockId, String cropId, String useStatus, String fieldPs) {
        return fieldService.modifyField(fieldId, fieldName, blockId, cropId, useStatus, fieldPs);
    }

    @PostMapping("/removeField")
    public @ResponseBody
    Message removeField(String fieldId) {
        return fieldService.removeField(fieldId);
    }

    @RequestMapping("/crop")
    public String cropFile() {
        return "sys/file/cropFile";
    }

    @RequestMapping("/getCrops")
    public @ResponseBody
    PaginationResult getCrops(int offset, int limit) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Crop> crops = cropService.getCrops();
        return new PaginationResult(page.getTotal(), crops);
    }

    @RequestMapping("/employee")
    public String employeeFile() {
        return "sys/file/employeeFile";
    }

    @RequestMapping("/getEmployees")
    public @ResponseBody
    PaginationResult getEmployees(int offset, int limit) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Employee> employees = employeeService.getEmployees();
        return new PaginationResult(page.getTotal(), employees);
    }

    @RequestMapping("/machine")
    public String machineFile() {
        return "sys/file/machineFile";
    }

    @RequestMapping("/getMachines")
    public @ResponseBody
    PaginationResult getMachines(int offset, int limit) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Machine> machines = machineService.getMachines();
        return new PaginationResult(page.getTotal(), machines);
    }

    @RequestMapping("/vehicle")
    public String vehicleFile() {
        return "sys/file/vehicleFile";
    }

    @RequestMapping("/getVehicles")
    public @ResponseBody
    PaginationResult getVehicles(int offset, int limit) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Vehicle> vehicles = vehicleService.getVehicles();
        return new PaginationResult(page.getTotal(), vehicles);
    }

    @RequestMapping("/sensor")
    public String sensorFile() {
        return "sys/file/sensorFile";
    }

    @RequestMapping("/getSensors")
    public @ResponseBody
    PaginationResult getSensors(int offset, int limit) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Sensor> sensors = sensorService.getSensors();
        return new PaginationResult(page.getTotal(), sensors);
    }
}

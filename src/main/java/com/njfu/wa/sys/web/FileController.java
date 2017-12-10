package com.njfu.wa.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.wa.sys.domain.*;
import com.njfu.wa.sys.enums.ResultEnum;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.service.*;
import com.njfu.wa.sys.utils.PaginationResult;
import com.njfu.wa.sys.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统档案管理控制器
 */
@Controller
@RequestMapping("/sys/file")
public class FileController {

    @Resource
    private BlockService blockService;

    @Resource
    private FieldService fieldService;

    @Resource
    private CropService cropService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private MachineService machineService;

    @Resource
    private VehicleService vehicleService;

    @Resource
    private TerminalService terminalService;

    @Resource
    private SensorService sensorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    /**
     * 地块档案页
     *
     * @return Page
     */
    @GetMapping("/block")
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
    @GetMapping("/block/data")
    public @ResponseBody
    PaginationResult getBlocks(int offset, int limit, Block block) {
        PageHelper.offsetPage(offset, limit);
        List<Block> blocks = blockService.getBlocks(block);
        PageInfo<Block> page = new PageInfo<>(blocks);
        return new PaginationResult<>(page.getTotal(), blocks);
    }

    /**
     * 新增地块信息
     *
     * @param block blockId blockName blockLoc blockPs
     * @return json Result
     */
    @PostMapping("/block/add")
    public @ResponseBody
    Result addBlock(Block block) {
        try {
            blockService.addBlock(block);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改地块信息
     *
     * @param block blockId blockName blockLoc blockPs
     * @return json Result
     */
    @PostMapping("/block/modify")
    public @ResponseBody
    Result modifyBlock(Block block) {
        try {
            blockService.modifyBlock(block);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除地块信息
     *
     * @param block blockId
     * @return json Result
     */
    @PostMapping("/block/remove")
    public @ResponseBody
    Result removeBlock(Block block) {
        try {
            blockService.removeBlock(block);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 大棚档案页
     *
     * @param model blocks crops
     * @return Page
     */
    @GetMapping("/field")
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
    @GetMapping("/field/data")
    public @ResponseBody
    PaginationResult getFields(int offset, int limit, Field field, Block block, Crop crop) {
        PageHelper.offsetPage(offset, limit);
        List<Field> fields = fieldService.getFields(field, block, crop);
        PageInfo<Field> page = new PageInfo<>(fields);
        return new PaginationResult<>(page.getTotal(), fields);
    }

    /**
     * 新增大棚信息
     *
     * @param field fieldId fieldName useStatus fieldPs
     * @param block blockId
     * @param crop  cropId
     * @return json Result
     */
    @PostMapping("/field/add")
    public @ResponseBody
    Result addField(Field field, Block block, Crop crop) {
        try {
            fieldService.addField(field, block, crop);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改大棚信息
     *
     * @param field fieldId fieldName useStatus fieldPs
     * @param block blockId
     * @param crop  cropId
     * @return json Result
     */
    @PostMapping("/field/modify")
    public @ResponseBody
    Result modifyField(Field field, Block block, Crop crop) {
        try {
            fieldService.modifyField(field, block, crop);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 移除大棚信息
     *
     * @param field fieldId
     * @return json Result
     */
    @PostMapping("/field/remove")
    public @ResponseBody
    Result removeField(Field field) {
        try {
            fieldService.removeField(field);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 作物档案页
     *
     * @return Page
     */
    @GetMapping("/crop")
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
    @GetMapping("/crop/data")
    public @ResponseBody
    PaginationResult getCrops(int offset, int limit, Crop crop) {
        PageHelper.offsetPage(offset, limit);
        List<Crop> crops = cropService.getCrops(crop);
        PageInfo<Crop> page = new PageInfo<>(crops);
        return new PaginationResult<>(page.getTotal(), crops);
    }

    /**
     * 新增作物信息
     *
     * @param crop cropId cropName cropPs
     * @return json Result
     */
    @PostMapping("/crop/add")
    public @ResponseBody
    Result addCrop(Crop crop) {
        try {
            cropService.addCrop(crop);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改作物信息
     *
     * @param crop cropId cropName cropPs
     * @return json Result
     */
    @PostMapping("/crop/modify")
    public @ResponseBody
    Result modifyCrop(Crop crop) {
        try {
            cropService.modifyCrop(crop);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除作物信息
     *
     * @param crop cropId
     * @return json Result
     */
    @PostMapping("/crop/remove")
    public @ResponseBody
    Result removeCrop(Crop crop) {
        try {
            cropService.removeCrop(crop);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 员工档案页
     *
     * @return Page
     */
    @GetMapping("/employee")
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
    @GetMapping("/employee/data")
    public @ResponseBody
    PaginationResult getEmployees(int offset, int limit, Employee employee) {
        PageHelper.offsetPage(offset, limit);
        List<Employee> employees = employeeService.getEmployees(employee);
        PageInfo<Employee> page = new PageInfo<>(employees);
        return new PaginationResult<>(page.getTotal(), employees);
    }

    /**
     * 新增员工信息
     *
     * @param employee empId empName empTel empPosition empAge empSex empPs
     * @return json Result
     */
    @PostMapping("/employee/add")
    public @ResponseBody
    Result addEmployee(Employee employee) {
        try {
            employeeService.addEmployee(employee);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改员工信息
     *
     * @param employee empId empName empTel empPosition empAge empSex empPs
     * @return json Result
     */
    @PostMapping("/employee/modify")
    public @ResponseBody
    Result modifyEmployee(Employee employee) {
        try {
            employeeService.modifyEmployee(employee);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除员工信息
     *
     * @param employee empId
     * @return json Result
     */
    @PostMapping("/employee/remove")
    public @ResponseBody
    Result removeEmployee(Employee employee) {
        try {
            employeeService.removeEmployee(employee);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 机械档案页
     *
     * @param model blocks
     * @return Page
     */
    @GetMapping("/machine")
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
    @GetMapping("/machine/data")
    public @ResponseBody
    PaginationResult getMachines(int offset, int limit, Machine machine, Block block) {
        PageHelper.offsetPage(offset, limit);
        List<Machine> machines = machineService.getMachines(machine, block);
        PageInfo<Machine> page = new PageInfo<>(machines);
        return new PaginationResult<>(page.getTotal(), machines);
    }

    /**
     * 新增机械信息
     *
     * @param machine 　machineId machineType useStatus machinePs
     * @param block   　blockId
     * @return json Result
     */
    @PostMapping("/machine/add")
    public @ResponseBody
    Result addMachine(Machine machine, Block block) {
        try {
            machineService.addMachine(machine, block);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改机械信息
     *
     * @param machine 　machineId machineType useStatus machinePs
     * @param block   　blockId
     * @return json Result
     */
    @PostMapping("/machine/modify")
    public @ResponseBody
    Result modifyMachine(Machine machine, Block block) {
        try {
            machineService.modifyMachine(machine, block);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除机械信息
     *
     * @param machine 　machineId
     * @return json Result
     */
    @PostMapping("/machine/remove")
    public @ResponseBody
    Result removeMachine(Machine machine) {
        try {
            machineService.removeMachine(machine);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 车辆档案页
     *
     * @return Page
     */
    @GetMapping("/vehicle")
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
    @GetMapping("/vehicle/data")
    public @ResponseBody
    PaginationResult getVehicles(int offset, int limit, Vehicle vehicle, Block block) {
        PageHelper.offsetPage(offset, limit);
        List<Vehicle> vehicles = vehicleService.getVehicles(vehicle, block);
        PageInfo<Vehicle> page = new PageInfo<>(vehicles);
        return new PaginationResult<>(page.getTotal(), vehicles);
    }

    /**
     * 新增车辆信息
     *
     * @param vehicle vehicleId vehicleType useStatus vehiclePs
     * @param block   blockId
     * @return json Result
     */
    @PostMapping("/vehicle/add")
    public @ResponseBody
    Result addVehicle(Vehicle vehicle, Block block) {
        try {
            vehicleService.addVehicle(vehicle, block);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改车辆信息
     *
     * @param vehicle vehicleId vehicleType useStatus vehiclePs
     * @param block   blockId
     * @return json Result
     */
    @PostMapping("/vehicle/modify")
    public @ResponseBody
    Result modifyVehicle(Vehicle vehicle, Block block) {
        try {
            vehicleService.modifyVehicle(vehicle, block);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除车辆档案
     *
     * @param vehicle vehicleId
     * @return json Result
     */
    @PostMapping("/vehicle/remove")
    public @ResponseBody
    Result removeVehicle(Vehicle vehicle) {
        try {
            vehicleService.removeVehicle(vehicle);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 终端档案页
     *
     * @return Page
     */
    @GetMapping("/terminal")
    public String terminalFile() {
        return "sys/file/terminal";
    }

    /**
     * 获取终端列表
     *
     * @param terminal terminalId terminalType useStatus
     * @param offset   offset
     * @param limit    limit
     * @return json data
     */
    @RequestMapping("terminal/data")
    public @ResponseBody
    PaginationResult getTerminals(Terminal terminal, int offset, int limit) {
        PageHelper.offsetPage(offset, limit);
        List<Terminal> terminals = terminalService.getTerminals(terminal);
        PageInfo<Terminal> page = new PageInfo<>(terminals);
        return new PaginationResult<>(page.getTotal(), terminals);
    }

    /**
     * 新增终端
     *
     * @param terminal terminal
     * @return json Result
     */
    @RequestMapping("/terminal/add")
    public @ResponseBody
    Result addTerminal(Terminal terminal) {
        try {
            terminalService.addTerminal(terminal);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改终端信息
     *
     * @param terminal terminal
     * @return json Result
     */
    @RequestMapping("/terminal/modify")
    public @ResponseBody
    Result modifyTerminal(Terminal terminal) {
        try {
            terminalService.modifyTerminal(terminal);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除终端
     *
     * @param terminalId terminalId
     * @return json Result
     */
    @RequestMapping("/terminal/remove")
    public @ResponseBody
    Result removeTerminal(String terminalId) {
        try {
            terminalService.removeTerminal(terminalId);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 传感器档案页
     *
     * @return Page
     */
    @GetMapping("/sensor")
    public String sensorFile(Model model) {
        List<Field> fields = fieldService.getFields(new Field(), new Block(), new Crop());
        List<Terminal> terminals = terminalService.getTerminals(new Terminal());
        model.addAttribute("fields", fields);
        model.addAttribute("terminals", terminals);
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
    @GetMapping("/sensor/data")
    public @ResponseBody
    PaginationResult getSensors(int offset, int limit, Sensor sensor, Field field) {
        PageHelper.offsetPage(offset, limit);
        List<Sensor> sensors = sensorService.getSensors(sensor, field);
        PageInfo<Sensor> page = new PageInfo<>(sensors);
        return new PaginationResult<>(page.getTotal(), sensors);
    }

    /**
     * 新增传感器信息
     *
     * @param sensor sensorId sensorFunc sensorType useStatus sensorPs
     * @param field  fieldId
     * @return json Result
     */
    @PostMapping("/sensor/add")
    public @ResponseBody
    Result addSensor(Sensor sensor, Field field) {
        try {
            sensorService.addSensor(sensor, field);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改传感器信息
     *
     * @param sensor sensorId sensorFunc sensorType useStatus sensorPs
     * @param field  fieldId
     * @return json Result
     */
    @PostMapping("/sensor/modify")
    public @ResponseBody
    Result modifySensor(Sensor sensor, Field field) {
        try {
            sensorService.modifySensor(sensor, field);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除传感器信息
     *
     * @param sensor sensorId
     * @return json Result
     */
    @PostMapping("/sensor/remove")
    public @ResponseBody
    Result removeSensor(Sensor sensor) {
        try {
            sensorService.removeSensor(sensor);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }
}

package com.njfu.ia.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.ia.sys.domain.*;
import com.njfu.ia.sys.enums.ResultEnum;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.service.*;
import com.njfu.ia.sys.utils.PaginationResult;
import com.njfu.ia.sys.utils.Result;
import com.njfu.ia.sys.utils.page.PageOffset;
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
    private SectionService sectionService;

    @Resource
    private FieldService fieldService;

    @Resource
    private CropService cropService;

    @Resource
    private MachineService machineService;

    @Resource
    private VehicleService vehicleService;

    @Resource
    private EndDeviceService endDeviceService;

    @Resource
    private SensorService sensorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    /**
     * 地块档案页
     *
     * @return
     */
    @GetMapping("/block")
    public String blockFile() {
        return "sys/file/block";
    }

    /**
     * 获取地块列表
     *
     * @param offset
     * @param limit
     * @param block
     * @return
     */
    @GetMapping("/block/data")
    @PageOffset
    public @ResponseBody
    PaginationResult getBlocks(int offset, int limit, Block block) {
        List<Block> blocks = blockService.getBlocks(block);
        PageInfo<Block> page = new PageInfo<>(blocks);
        return new PaginationResult<>(page.getTotal(), blocks);
    }

    /**
     * 获取地块列表
     *
     * @param block
     * @return
     */
    @GetMapping("/block/list")
    public @ResponseBody
    Result listBlocks(Block block) {
        try {
            List<Block> blocks = blockService.getBlocks(block);
            return Result.response(ResultEnum.SUCCESS, null, blocks);
        } catch (Exception e) {
            LOGGER.error("list blocks Exception", e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 新增地块信息
     *
     * @param block
     * @return
     */
    @PostMapping("/block/add")
    public @ResponseBody
    Result addBlock(Block block) {
        try {
            blockService.addBlock(block);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改地块信息
     *
     * @param block
     * @return
     */
    @PostMapping("/block/modify")
    public @ResponseBody
    Result modifyBlock(Block block) {
        try {
            blockService.modifyBlock(block);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除地块信息
     *
     * @param block
     * @return
     */
    @PostMapping("/block/remove")
    public @ResponseBody
    Result removeBlock(Integer id) {
        try {
            blockService.removeBlock(id);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 区块档案页
     *
     * @return
     */
    @GetMapping("/section")
    public String sectionFile() {
        return "sys/file/section";
    }

    /**
     * 获取区块信息数据
     *
     * @param offset
     * @param limit
     * @param section
     * @return
     */
    @GetMapping("/section/data")
    @PageOffset
    public @ResponseBody
    PaginationResult getSectionData(int offset, int limit, Section section) {
        List<Section> sections = sectionService.getSections(section);
        PageInfo<Section> page = new PageInfo<>(sections);
        return new PaginationResult(page.getTotal(), sections);
    }

    /**
     * 获取区块信息列表
     *
     * @param section
     * @return
     */
    @GetMapping("/section/list")
    public @ResponseBody
    Result getSectionList(Section section) {
        try {
            List<Section> sections = sectionService.getSections(section);
            return Result.response(ResultEnum.SUCCESS, null, sections);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 新增区块信息
     *
     * @param section
     * @return
     */
    @PostMapping("/section/add")
    public @ResponseBody
    Result addSection(Section section) {
        try {
            sectionService.addSection(section);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改区块信息
     *
     * @param section
     * @return
     */
    @PostMapping("/section/modify")
    public @ResponseBody
    Result modifySection(Section section) {
        try {
            sectionService.modifySection(section);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除区块信息
     *
     * @param id
     * @return
     */
    @PostMapping("/section/remove")
    public @ResponseBody
    Result removeSection(Integer id) {
        try {
            sectionService.removeSection(id);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 大棚档案页
     *
     * @return
     */
    @GetMapping("/field")
    public String field() {
        return "sys/file/field";
    }

    /**
     * 获取大棚列表
     *
     * @param offset
     * @param limit
     * @param field
     * @return
     */
    @GetMapping("/field/data")
    public @ResponseBody
    PaginationResult getFields(int offset, int limit, Field field) {
        PageHelper.offsetPage(offset, limit);
        List<Field> fields = fieldService.getFields(field);
        PageInfo<Field> page = new PageInfo<>(fields);
        return new PaginationResult<>(page.getTotal(), fields);
    }

    /**
     * 新增大棚信息
     *
     * @param field
     * @param block
     * @param crop
     * @return
     */
    @PostMapping("/field/add")
    public @ResponseBody
    Result addField(Field field, Block block, Crop crop) {
        try {
            fieldService.addField(field);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改大棚信息
     *
     * @param field
     * @return
     */
    @PostMapping("/field/modify")
    public @ResponseBody
    Result modifyField(Field field) {
        try {
            fieldService.modifyField(field);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 移除大棚信息
     *
     * @param id
     * @return
     */
    @PostMapping("/field/remove")
    public @ResponseBody
    Result removeField(Integer id) {
        try {
            fieldService.removeField(id);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 作物档案页
     *
     * @return
     */
    @GetMapping("/crop")
    public String crop() {
        return "sys/file/crop";
    }

    /**
     * 获取作物信息数据
     *
     * @param offset
     * @param limit
     * @param crop
     * @return
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
     * 获取作物信息列表
     *
     * @param crop
     * @return
     */
    @GetMapping("/crop/list")
    public @ResponseBody
    Result getCropList(Crop crop) {
        try {
            List<Crop> crops = cropService.getCrops(crop);
            return Result.response(ResultEnum.SUCCESS, null, crops);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 新增作物信息
     *
     * @param crop
     * @return
     */
    @PostMapping("/crop/add")
    public @ResponseBody
    Result addCrop(Crop crop) {
        try {
            cropService.addCrop(crop);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改作物信息
     *
     * @param crop
     * @return
     */
    @PostMapping("/crop/modify")
    public @ResponseBody
    Result modifyCrop(Crop crop) {
        try {
            cropService.modifyCrop(crop);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除作物信息
     *
     * @param crop
     * @return
     */
    @PostMapping("/crop/remove")
    public @ResponseBody
    Result removeCrop(Crop crop) {
        try {
            cropService.removeCrop(crop);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
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
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
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
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
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
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
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
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
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
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
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
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
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
    @GetMapping("/device")
    public String endDeviceFile() {
        return "sys/file/device";
    }

    /**
     * 获取终端列表
     *
     * @param offset    offset
     * @param limit     limit
     * @param endDevice endDevice
     * @return json data
     */
    @RequestMapping("/device/data")
    public @ResponseBody
    PaginationResult getEndDevices(int offset, int limit, EndDevice endDevice) {
        PageHelper.offsetPage(offset, limit);
        List<EndDevice> endDevices = endDeviceService.getEndDevices(endDevice);
        PageInfo<EndDevice> page = new PageInfo<>(endDevices);
        return new PaginationResult<>(page.getTotal(), endDevices);
    }

    /**
     * 获取终端列表
     *
     * @param offset    offset
     * @param limit     limit
     * @param endDevice endDevice
     * @return EndDevice list
     */
    @RequestMapping("/device/list")
    public @ResponseBody
    Result listEndDevices(EndDevice endDevice) {
        List<EndDevice> endDevices = endDeviceService.getEndDevices(endDevice);
        return Result.response(ResultEnum.SUCCESS, null, endDevices);
    }

    /**
     * 传感器档案页
     *
     * @return
     */
    @GetMapping("/sensor")
    public String sensorFile() {
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
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
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
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
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
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }
}

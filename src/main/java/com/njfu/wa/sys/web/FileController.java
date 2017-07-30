package com.njfu.wa.sys.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.domain.Field;
import com.njfu.wa.sys.service.BlockService;
import com.njfu.wa.sys.service.CropService;
import com.njfu.wa.sys.service.FieldService;
import com.njfu.wa.sys.util.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public @ResponseBody PaginationResult getFields(int offset, int limit, String fieldName, String blockId, String cropId) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<Field> fields = fieldService.getFields(fieldName, blockId, cropId);
        return new PaginationResult(page.getTotal(), fields);
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

    @RequestMapping("/equipment")
    public String equipmentFile() {
        return "sys/file/equipmentFile";
    }
}

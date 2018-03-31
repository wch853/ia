package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Section;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SectionServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SectionServiceTest.class);

    @Resource
    private SectionService sectionService;

    @Test
    public void selectSections() {
        List<Section> sections = sectionService.getSections(new Section());
        LOGGER.info("sections: {}", sections);
    }
}
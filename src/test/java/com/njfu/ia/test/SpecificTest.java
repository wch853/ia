package com.njfu.ia.test;

import com.njfu.ia.sys.domain.Block;
import com.njfu.ia.sys.utils.JsonUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SpecificTest {

    @Test
    public void getShiroMd5Password() {
        String source = "123456";
        String timeMills = "1513685984517";
        SimpleHash md5 = new SimpleHash("md5", source,
                ByteSource.Util.bytes(timeMills));
        System.out.println(md5);
    }

    @Test
    public void test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("blockId", "f1");

        Block block = JsonUtils.toBean(JsonUtils.toJsonString(map), Block.class);
        System.out.println(block);
    }
}

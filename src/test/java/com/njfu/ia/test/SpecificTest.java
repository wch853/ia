package com.njfu.ia.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.springframework.util.DigestUtils;

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
        String md5 = DigestUtils.md5DigestAsHex(String.valueOf(System.currentTimeMillis()).getBytes());
        System.out.println(md5);
    }
}

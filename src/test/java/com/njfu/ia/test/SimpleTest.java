package com.njfu.ia.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleTest {

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

    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            iterator.remove();
        }
    }
}

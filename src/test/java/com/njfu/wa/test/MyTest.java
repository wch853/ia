package com.njfu.wa.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class MyTest {

    @Test
    public void demo1() {
        String source = "123456";
        SimpleHash md5 = new SimpleHash("md5", source, ByteSource.Util.bytes("1513262685325"));
        System.out.println(md5);
    }

    @Test
    public void demo2() {
    }
}

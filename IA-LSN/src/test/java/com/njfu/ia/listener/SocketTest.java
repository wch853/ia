package com.njfu.ia.listener;


import com.njfu.ia.listener.utils.Constants;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketTest {

    private Socket socket;

    private OutputStream os;

    @Before
    public void before() throws IOException {
        socket = new Socket("127.0.0.1", 9040);
        os = socket.getOutputStream();
    }

    @Test
    public void test1() throws IOException, InterruptedException {
        byte[] message = constructMessage("tp:0|mac:00-12-4B-00-03-98-A1-AB");
        while (true) {
            os.write(message);
            os.flush();
            Thread.sleep(1000);
        }
    }


    private byte[] constructMessage(String message) {
        int length = message.length();
        byte[] data = new byte[length + 3];

        int x = 0;
        data[x++] = Constants.START_FLAG;
        for (byte b : message.getBytes()) {
            data[x++] = b;
        }

        data[x++] = (byte) length;
        data[x] = Constants.END_FLAG;

        return data;
    }
}

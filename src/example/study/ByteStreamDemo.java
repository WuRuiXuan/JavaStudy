package example.study;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by wuruixuan on 2018/1/18.
 */

public class ByteStreamDemo {
    public static void main(String[] args) {
        write();
        read();
    }

    public static void write() {
        File file = new File("/Users/wuruixuan/Desktop/a.txt");
        try {
            OutputStream out = new FileOutputStream(file);
            String info = "coding lesson";
            out.write(info.getBytes()); // 直接写入文件中，没有缓存
            out.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        File file = new File("/Users/wuruixuan/Desktop/a.txt");
        try {
            InputStream in = new FileInputStream(file);
            byte[] bytes = new byte[1024*1024*10]; // 每次读取10MB
            int len = -1; // 每次真实读取的长度
            StringBuffer buf = new StringBuffer();
            while ((len = in.read(bytes)) != -1) { // 读完返回-1
                buf.append(new String(bytes, 0, len));
            }
            in.close();
            System.out.println(buf);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



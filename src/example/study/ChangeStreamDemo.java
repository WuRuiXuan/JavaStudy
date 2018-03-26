package example.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

/**
 * Created by wuruixuan on 2018/1/18.
 */

public class ChangeStreamDemo {
    public static void main(String[] args) {
        String info = reader(System.in);
        System.out.println(info);
    }

    // 转换成字符流
    public static String reader(InputStream in) {
        Reader reader = new InputStreamReader(in);
        char[] cs = new char[1024];
        int len = -1;
        StringBuffer bf = new StringBuffer();
        try {
            while ((len = reader.read(cs)) != -1) {
                bf.append(new String(cs, 0, len));
            }
            reader.close();
            return bf.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String bfReader(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            return reader.readLine();
        }
        catch (IOException e) {
            return null;
        }
    }

    public static void writer(OutputStream out) {
        Writer writer = new OutputStreamWriter(out);
        try {
            writer.write("string");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

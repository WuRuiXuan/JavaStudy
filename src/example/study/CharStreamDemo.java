package example.study;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by wuruixuan on 2018/1/18.
 */

/**
 * 如果是文本类型，建议使用字符流
 * 如果是非文本类型（音频、视频），建议使用字节流
 */

public class CharStreamDemo {
    public static void main(String[] args) {
        write();
        read();
    }

    public static void write() {
        File file = new File("/Users/wuruixuan/Desktop/b.txt");
        try {
            Writer out = new FileWriter(file);
            String info = "coding lesson";
            out.write(info); // 输出到缓存中
            out.write("\r\n"); // 输出换行符
            out.flush(); // 刷新缓存（清空缓存并写入文件）
            out.close(); // 会刷新
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        File file = new File("/Users/wuruixuan/Desktop/b.txt");
        try {
            Reader in = new FileReader(file);
            char[] cs = new char[2];
            int len = -1;
            StringBuffer bf = new StringBuffer();
            while ((len = in.read(cs)) != -1) {
                bf.append(new String(cs, 0, len));
            }
            in.close();
            System.out.println(bf);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

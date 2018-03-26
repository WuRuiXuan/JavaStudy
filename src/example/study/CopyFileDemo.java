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

public class CopyFileDemo {
    public static void main(String[] args) {
        copyFile(new File("c:\\1.jpg"), "d:\\");
    }

    /**
     *
     * @param target 源文件
     * @param dest 目标文件
     */
    public static void copyFile(File target, String dest) {
        String fileName = target.getName();
        System.out.println(fileName);
        File destFile = new File(dest + fileName);
        try {
            InputStream in = new FileInputStream(target);
            OutputStream out = new FileOutputStream(destFile);
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            out.close();
            in.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

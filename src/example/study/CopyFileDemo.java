package example.study;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件复制
 */

public class CopyFileDemo {
    public static void main(String[] args) {
        copyFile(new File(System.getProperty("user.dir") + "/DemoImage.jpg"), System.getProperty("user.dir") + "/CopiedImage.jpg");
    }

    /**
     *
     * @param target 源文件
     * @param dest 目标文件
     */
    public static void copyFile(File target, String dest) {
        String fileName = target.getName();
        System.out.println(fileName);
        File destFile = new File(dest);
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

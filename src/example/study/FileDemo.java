package example.study;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wuruixuan on 2018/1/17.
 */

public class FileDemo {
    public static void main(String[] args) {
        File file = new File("c" + File.separator + "a.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("文件绝对路径：" + file.getAbsolutePath());
        long lastModified = file.lastModified();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastTime = df.format(new Date(lastModified));
        System.out.println("最后修改时间：" + lastTime);
        System.out.println("文件长度：" + file.length());
        System.out.println("是否为目录：" + file.isDirectory());

        File file2 = new File("c:\\test");
        if (!file2.exists()) {
            file2.mkdir();
        }
        // file2.delete();
        String[] names = file2.list();
        for (String name : names) {
            System.out.println(name);
        }

        File[] files = file2.listFiles();
        for (File f : files) {
            System.out.println(f.getPath() + "--" + f.length());
        }

        findFile(new File("c:\\test"), ".txt");
    }

    /**
     *
     * @param target 目标文件夹
     * @param ext 扩展名
     */
    public static void findFile(File target, String ext) {
        if (target != null) {
            if (target.isDirectory()) {
                File[] files = target.listFiles();
                if (files != null) {
                    for (File f : files) {
                        findFile(f, ext);
                    }
                }
            }
            else {
                String path = target.getAbsolutePath();
                if (path.endsWith(ext)) {
                    System.out.println(path);
                }
            }
        }
    }
}

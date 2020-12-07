package com.xrca.io;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;

/**
 * @author xrca
 * @description File类使用
 * @date 2020/11/6 22:31
 **/
public class FileDemo {
    /**
     * @author xrca
     * @description 文件创建
     * @date 2020/11/6 22:49
     **/
    @Test
    public void createFile() throws Exception {
        // 相对路径
        File file1 = new File("file1.txt"); // 相对于当前module

        // 绝对路径
        File file2 = new File("C:/Users/fishs/workspace/IdeaProjects/CoreJava/IO/file2.txt");

        // 父子路径
        File file3 = new File("C:/Users/fishs/workspace/IdeaProjects/CoreJava", "IO");

        // 基于父类路径
        File file4 = new File(file3, "file4.txt");

        System.out.println(file1.createNewFile());
        System.out.println(file2.createNewFile());
        System.out.println(file4.createNewFile());
    }

    /**
     * @author xrca
     * @description File常用方法
     * @date 2020/11/6 22:50
     **/
    @Test
    public void commonMethods() {
        File file1 = new File("file1.txt");
        File file2 = new File("C:/Users/fishs/workspace/IdeaProjects/CoreJava/IO/file2.txt");

        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(file1.lastModified());

        System.out.println("==================================");

        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());

        System.out.println("===================================");

        File file3 = new File("C:/Users/fishs/workspace/IdeaProjects/CoreJava/IO");
        System.out.println(Arrays.toString(file3.list()));


        File file = new File("file1.txt");
        // 若目标文件已存在，则无法重命名
        File newFile = new File("newFile.txt");
        boolean renameTo = file.renameTo(newFile);
        System.out.println(renameTo);

        System.out.println("====================================");

        File file4 = new File("C:/Users/fishs/workspace/IdeaProjects" +
                "/CoreJava/IO/src/main/java/com/xrca/corejava/io/FileDemo.java");
        System.out.println(file4.isDirectory());
        System.out.println(file4.isFile());
        System.out.println(file4.exists());
        System.out.println(file4.canRead());
        System.out.println(file4.canWrite());
        System.out.println(file4.isHidden());
    }
}

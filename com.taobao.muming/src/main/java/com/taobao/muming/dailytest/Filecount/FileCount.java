package com.taobao.muming.dailytest.Filecount;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileCount {
    private static List<String> excludeFolder;
    private static List<String> includeFolder;
    private static List<String> excludeFile;
    private static List<String> includeFile;
    private static List<String> excludeKeyword;
    private static List<String> includeKeyword;
    private static Long count = 0l;

    static {
        excludeFolder = new ArrayList<String>();
        excludeFile = new ArrayList<String>();
        excludeKeyword = new ArrayList<String>();
        includeKeyword = new ArrayList<String>();
        //或关系
        excludeFolder.add("classes");
        excludeFolder.add(".svn");
        excludeFolder.add(".settings");
        excludeFolder.add("lib");
        excludeFolder.add("css");
        excludeFolder.add("images");
        excludeFolder.add("tlds");
        excludeFolder.add("upload");
        excludeFolder.add("sqlmaps");
        //或关系
        excludeFile.add(".classpath");
        excludeFile.add(".project");
        excludeFile.add("sitemap_errorlist.txt");
        excludeFile.add("sitemap.txt");
        //或关系
//        excludeKeyword.add("springframework");
//        excludeKeyword.add("/dtd");
//        excludeKeyword.add("xmlns");
//        excludeKeyword.add("zhaoshang.alijk");
//        excludeKeyword.add("fuwu.alijk");
//        excludeKeyword.add("ir.alijk");
//        excludeKeyword.add("yi.admin.alijk");

        //与关系
        //includeKeyword.add("http://");
        //includeKeyword.add("alijk.com");
        //includeKeyword.add("alihealth.cn");
        includeKeyword.add("package");
    }

    @SuppressWarnings("resource")
    public static void findStringInFile(File file) throws IOException {
        InputStreamReader read = new InputStreamReader(
                new FileInputStream(file), "UTF-8");// 考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String line = null;
        Integer lineNo = 0;
        while ((line = bufferedReader.readLine()) != null) {
            lineNo++;
            if (line.startsWith("#")) {
                continue;
            }
            // 指定字符串判断处
            if (isMatchIncludeHttpAddr(line) && !isMatchExcludeHttpAddr(line)) {
                count++;
//                System.out.println(lineNo + "---" + line + "------"
//                        + file.getAbsolutePath());
            }
        }
    }

    private static boolean isMatchIncludeHttpAddr(String line) {
        for (String str : includeKeyword) {
            if (!line.contains(str)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isMatchExcludeHttpAddr(String line) {
        for (String str : excludeKeyword) {
            if (line.contains(str)) {
                return true;
            }
        }
        return false;
    }

    public static void handleFile(File fs) throws IOException {
        // System.out.println(fs.getAbsolutePath());
        findStringInFile(fs);
    }

    public static void traverseFolder(File file) throws IOException {
        int fileNum = 0, folderNum = 0;
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    if (!isMatchExcludeFolder(file2)) {
                        // System.out.println("文件夹:" + file2.getAbsolutePath());
                        list.add(file2);
                        folderNum++;
                    }

                } else {
                    if (!isMatchExcludeFile(file2)) {
                        // System.out.println("文件:" + file2.getAbsolutePath());
                        fileNum++;
                        handleFile(file2);
                    }
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        if (!isMatchExcludeFolder(file2)) {
                            // System.out.println("文件夹:" +
                            // file2.getAbsolutePath());
                            list.add(file2);
                            folderNum++;
                        }
                    } else {
                        if (!isMatchExcludeFile(file2)) {
                            // System.out.println("文件:" +
                            // file2.getAbsolutePath());
                            fileNum++;
                            handleFile(file2);
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum
                + "http地址：" + count);

    }

    private static boolean isMatchExcludeFile(File file2) {
        for (String str : excludeFile) {
            if (file2.getAbsolutePath().endsWith(str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMatchExcludeFolder(File file2) {
        for (String str : excludeFolder) {
            if (file2.getAbsolutePath().endsWith(str)) {
                return true;
            }
        }
        return false;
    }

    final static void handleFiles(File dir) throws Exception {
        File[] fs = dir.listFiles();
        for (int i = 0; i < fs.length; i++) {
            handleFile(fs[i]);
            if (fs[i].isDirectory()) {
                try {
                    handleFiles(fs[i]);
                } catch (Exception e) {
                }
            }
        }
    }

    final static void fileCount(File dir) throws IOException {
        traverseFolder(dir);
    }

    public static void main(String[] args) throws Exception {
        //File root = new File("D:\\workspace\\OfficialSite");
        File root = new File("D:\\idea\\purchaseplatform\\epp-purchaseflow");
        //traverseFolder(root);
        fileCount(root);
    }
}

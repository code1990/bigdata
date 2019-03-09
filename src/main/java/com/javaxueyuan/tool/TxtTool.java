package com.javaxueyuan.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtTool {

    public static List<String> readTxt(String path){
        List<String> list = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            list = new ArrayList<>();
            File file = new File(path);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String str = "";

            while ((str = bufferedReader.readLine()) != null) {
                if (!"".equals(str)) {
                    list.add(str);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    public static void checkTxt(String path){
        File file = new File(path);
        if(!file.getParentFile().exists()){
            file.mkdirs();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void writeTxt(String path,List<String> list) {
        StringBuilder sb = new StringBuilder(1000);
        for(String str:list){
            sb.append(str+"\n");
        }
        writeTxt(path,sb.toString());
    }

    public static void writeTxt(String path,String str){
        FileWriter fw = null;
        try {
            checkTxt(path);
            File file = new File(path);
            fw = new FileWriter(file);
            fw.write(str);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("over");
        }
    }

    public static void main(String[] args) {
        System.out.println("===");
        for(String str:readTxt("C:\\Users\\issuser\\Desktop\\新建文本文档.txt")){
            System.out.println(str);
        }

        writeTxt("C:\\Users\\issuser\\Desktop\\新建文本文档123.txt","123123");
    }
}

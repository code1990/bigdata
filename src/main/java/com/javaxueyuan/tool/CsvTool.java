package com.javaxueyuan.tool;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CsvTool {

    public static List<String> readCsv(String path) {
        List<String> list = null;
        CsvReader csvReader = null;
        try {
            list = new ArrayList<>();
            csvReader = new CsvReader(path);
            // 读表头
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                // 读一整行
                System.out.println(csvReader.getRawRecord());
                // 读这行的某一列
//                System.out.println(csvReader.get("Link"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                csvReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    public static void checkCsv(String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    public static void writeCsv(String path,List<String> list) {
//        StringBuilder sb = new StringBuilder(1000);
//        for(String str:list){
//            sb.append(str+"\n");
//        }
////        writeCsv(path,sb.toString());
//    }

    public static void writeCsv(String path, List<String> list) {
        try {
            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(path, ',', Charset.forName("GBK"));

            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    String[] headers = list.get(i).split(",");
                    csvWriter.writeRecord(headers);
                } else {
                    String[] content = list.get(i).split(",");
                    csvWriter.writeRecord(content);
                }

            }
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("over");
        }
    }

    public static void main(String[] args) {
        System.out.println("===");
        for (String str : readCsv("C:\\Users\\issuser\\Desktop\\5.0.0.csv")) {
            System.out.println(str);
        }

        writeCsv("C:\\Users\\issuser\\Desktop\\1234.csv",  TxtTool.readTxt("C:\\Users\\issuser\\Desktop\\123.txt"));
    }
}

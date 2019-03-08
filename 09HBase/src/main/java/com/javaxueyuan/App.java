package com.javaxueyuan;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用过时API来操作HBase
 */
public class App {
    public static Configuration conf;

    static {
        //使用HBaseConfiguration的单例方法实现
        conf = HBaseConfiguration.create();
        //设置zk的主节点
        conf.set("hbase.zookeeper.quorum","192.168.1.101");
        //设置zk主节点的连接端口
        conf.set("hbase.zookeeper.property.clientPort","2181");
    }

    /**
     * 判断表是否存在
     * @param tableName
     * @return
     * @throws IOException
     */
    public static boolean isTableExist(String tableName) throws IOException {
        boolean result = false;
        //在HBase管理当中 访问表首先需要创建HBaseAdmin对象
        //获取HBaseAdmin的方式如下2种
//        Connection con = ConnectionFactory.createConnection(conf);
//        HBaseAdmin admin = (HBaseAdmin)con.getAdmin();
        HBaseAdmin admin = new HBaseAdmin(conf);//方法过时了
        result = admin.tableExists(tableName);
        return result;
    }

    /**
     *创建表
     */
    public static void createTable(String tableName,String ... columnFamily) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(conf);
        //判断表是否存在
        if(isTableExist(tableName)){
            System.out.println("表"+tableName+"已经存在");
            System.exit(0);
        }else{
//            创建表属性对象 表名需要转字节
            HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(tableName));
            //创建多个列族
            for(String cf:columnFamily){
                descriptor.addFamily(new HColumnDescriptor(cf));
            }
            //根据对表的配置 创建表
            admin.createTable(descriptor);
            System.out.println("表"+tableName+"创建成功");
        }
    }

    /**
     * 删除表
     */
    public static void dropTable(String tableName) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(conf);
        if(isTableExist(tableName)){
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            System.out.println("表"+tableName+"删除成功");
        }else{
            System.out.println("表"+tableName+"不存在");
        }
    }
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Map<String,Object> params = new HashMap<>();
        params.put("isRange","false");
//        boolean isRange=(boolean) params.get("isRange");
        boolean isRange = Boolean.parseBoolean(params.get("isRange").toString());
        System.out.println(isRange);
        String y = "123";
        int x = (y == null || "null".equals(y.toLowerCase()))==true ?0:2;
       x= new Integer("123");
        System.out.println(x);
        new Integer("123");
    }


}

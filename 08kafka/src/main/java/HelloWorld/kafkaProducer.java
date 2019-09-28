package HelloWorld;


import java.util.Properties;
import java.util.concurrent.TimeUnit;

import kafka.Kafka;
import kafka.consumer.Consumer;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

import java.util.Properties;

/**
 * @program: bigdata
 * @Date: 2019-09-28 14:35
 * @Author: code1990
 * @Description:
 */
public class kafkaProducer extends Thread{

    private String topic;

    public kafkaProducer(String topic) {
        super();
        this.topic = topic;
    }

    @Override
    public void run() {
        Producer producer = createProducer();
        int i = 0;
        while (true) {
            producer.send(new KeyedMessage<Integer, String>(topic, "message: "+ i++));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private Producer createProducer(){
        Properties properties = new Properties();
        /*// 声明zk*/
        properties.put("zookeeper.connect","localhost:2181");
        properties.put("serializer.class", StringEncoder.class.getName());
        /*// 声明kafka*/
        properties.put("metadata.broker.list","localhost:9092");
        return new Producer<Integer, String>(new ProducerConfig(properties));
    }

    public static void main(String[] args) {
        /*注意本地分别启动zkserver.cmd consumer 先启动zk 然后启动 consumer 然后启动producer*/
        new kafkaProducer("test").start();
    }

}

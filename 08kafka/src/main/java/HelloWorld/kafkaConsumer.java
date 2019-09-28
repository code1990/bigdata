package HelloWorld;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.Kafka;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
/**
 * @program: bigdata
 * @Date: 2019-09-28 14:35
 * @Author: code1990
 * @Description:
 */
public class kafkaConsumer extends Thread{
    private String topic;

    public kafkaConsumer(String topic) {
        super();
        this.topic = topic;
    }

    @Override
    public void run() {
        ConsumerConnector consumer = createConsumer();
        Map<String,Integer> topicMap = new HashMap<>();
        /*一次从主题中获取一个数据*/
        topicMap.put(topic,1);
        Map<String,List<KafkaStream<byte[],byte[]>>> msgStream =
                consumer.createMessageStreams(topicMap);
        /*获取么一次接受到的数据*/
        KafkaStream<byte[],byte[]> stream = msgStream.get(topic).get(0);
        ConsumerIterator<byte[],byte[]> iterator=stream.iterator();
        while (iterator.hasNext()){
            String msg = new String(iterator.next().message());
            System.out.println("接收到的数据:"+msg);
        }
    }
    private ConsumerConnector createConsumer(){
        Properties properties = new Properties();
        /*// 声明zk*/
        properties.put("zookeeper.connect","localhost:2181");
        /*// 必须要使用别的组名称，如果生产者和消费者都在同一组，则不能访问同一组内的topic数据*/
        properties.put("group.id", "group1");
        return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
    }

    public static void main(String[] args) {
        /*注意本地分别启动zkserver.cmd consumer 先启动zk 然后启动 consumer 然后启动producer*/
        new kafkaConsumer("test").start();
    }
}

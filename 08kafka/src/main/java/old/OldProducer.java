package old;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * @program: bigdata
 * @Date: 2019-09-28 14:00
 * @Author: code1990
 * @Description:
 */
public class OldProducer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("metadata.broker.list", "localhost:9092");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");

        Producer<Integer, String> producer = new Producer<Integer, String>(new ProducerConfig(properties));

        KeyedMessage<Integer, String> message = new KeyedMessage<Integer, String>("first", "hello world");
        producer.send(message);
    }

}

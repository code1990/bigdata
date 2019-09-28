package kafkastream;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.apache.kafka.streams.processor.TopologyBuilder;

import java.util.Properties;

/**
 * @program: bigdata
 * @Date: 2019-09-28 16:29
 * @Author: code1990
 * @Description:
 */
public class KafkaStreamApp {

    public static void main(String[] args) {
        /*定义输入的topic*/
        String from = "first";
        /*定义输出的topic*/
        String to = "second";

        /*设置参数*/
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"logFilter");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        StreamsConfig config = new StreamsConfig(properties);

        /*创建拓扑*/
        TopologyBuilder builder = new TopologyBuilder();

        builder.addSource("SOURCE", from)
                .addProcessor("PROCESS", new ProcessorSupplier<byte[], byte[]>() {

                    @Override
                    public Processor<byte[], byte[]> get() {
                        // 具体分析处理
                        return new LogProcessor();
                    }
                }, "SOURCE")
                .addSink("SINK", to, "PROCESS");


        // 创建kafka stream
        KafkaStreams streams = new KafkaStreams(builder, config);
        streams.start();

    }
}

package interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @program: bigdata
 * @Date: 2019-09-28 16:12
 * @Author: code1990
 * @Description:
 */
public class CountInterceptor implements ProducerInterceptor<String, String> {

    private int successCount = 0;
    private int errorCount = 0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (null == e) {
            successCount++;
        } else {
            errorCount++;
        }
    }

    @Override
    public void close() {
        System.out.println("success count" + successCount);
        System.out.println("error count" + errorCount);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}

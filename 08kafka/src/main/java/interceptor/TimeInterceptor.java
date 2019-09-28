package interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @program: bigdata
 * @Date: 2019-09-28 16:02
 * @Author: code1990
 * @Description:
 */
public class TimeInterceptor implements ProducerInterceptor<String,String> {

    /*该方法封装进KafkaProducer.send方法中，即它运行在用户主线程中
    * 用户可以在该方法中对消息做任何操作，但最好保证不要修改消息所属的topic和分区
    * */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        /*创建一个新的record 把时间错写入到消息体的最前面*/
        return  new ProducerRecord(producerRecord.topic(),producerRecord.partition(),
                producerRecord.timestamp(),producerRecord.key(),System.currentTimeMillis()+","+producerRecord.value().toString());
    }
    /*该方法会在消息被应答之前或消息发送失败时调用*/
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }
    /*关闭interceptor，主要用于执行一些资源清理工作关闭interceptor，主要用于执行一些资源清理工作*/
    @Override
    public void close() {

    }

    /*获取配置信息和初始化数据时调用。*/
    @Override
    public void configure(Map<String, ?> map) {

    }
}

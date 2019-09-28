package kafkastream;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

/**
 * @program: bigdata
 * @Date: 2019-09-28 16:22
 * @Author: code1990
 * @Description:
 */
public class LogProcessor implements Processor<byte[],byte[]> {

    private ProcessorContext context;

    @Override
    public void init(ProcessorContext processorContext) {
        this.context=processorContext;
    }

    @Override
    public void process(byte[] key, byte[] value) {
        String input = new String(value);
        if(input.contains(">>>")){
            input = input.split(">>>")[1].trim();
            context.forward("logProcessor".getBytes(),input.getBytes());
        }else{
            context.forward("logProcessor".getBytes(),input.getBytes());
        }
    }

    @Override
    public void punctuate(long l) {

    }

    @Override
    public void close() {

    }
}

package old;

import kafka.producer.Partitioner;

/**
 * @program: bigdata
 * @Date: 2019-09-28 15:37
 * @Author: code1990
 * @Description: 自定义分区
 */
public class OldPartitioner implements Partitioner {

    public OldPartitioner() {
    }

    @Override
    public int partition(Object key, int numPartitions) {
        return 0;
    }
}

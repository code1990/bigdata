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
        properties.put("metadata.broker.list","localhost:9092");
        properties.put("request.required.acks","1");
        properties.put("","");
        properties.put("","");
        properties.put("","");
        properties.put("","");




    }

}

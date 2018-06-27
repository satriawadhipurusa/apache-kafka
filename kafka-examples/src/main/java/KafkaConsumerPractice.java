import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerPractice {
    public static void main(String[] args) {
        Properties properties = new Properties();

        // kafka bootstrap server
        properties.setProperty("bootstrap.servers", "192.168.99.100:9092"); // connect to the bootstrap server
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", "test"); // naming consumer group, can have multiple consumer groups
        // reading different partitions
        properties.setProperty("enable.auto.commit", "true"); // commit offset properties for consumer group
//        properties.setProperty("enable.auto.commit", "false"); // use to control commit offset
        properties.setProperty("auto.commit.interval.ms", "1000"); // in ms, every second offset will be committed
        properties.setProperty("auto.offset.reset", "earliest"); // find offset or read messages from beginning

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties); // creating
        // consumer object based on properties
        kafkaConsumer.subscribe(Arrays.asList("second_topic")); // read from list of topics

        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100); // poll for 100 ms
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) { // loops for all consumerRecords
//                consumerRecord.value();
//                consumerRecord.key();
//                consumerRecord.offset();
//                consumerRecord.partition();
//                consumerRecord.topic();
//                consumerRecord.timestamp();

                System.out.println("Partition: " + consumerRecord.partition() +
                        ", Offset: " + consumerRecord.offset() +
                        ", Key: " + consumerRecord.key() +
                        ", Value: " + consumerRecord.value());
            }
//            kafkaConsumer.commitSync(); // trigger an offset commit

        }
    }

}



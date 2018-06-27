import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerPractice {
    public static void main(String[] args) {
        Properties properties = new Properties();

        // kafka bootstrap server
        properties.setProperty("bootstrap.servers", "192.168.99.100:9092"); // connect to the bootstrap server
        properties.setProperty("key.serializer", StringSerializer.class.getName()); // set key to string
        properties.setProperty("value.serializer", StringSerializer.class.getName()); // set value to string
        // proder acks
        properties.setProperty("acks", "1"); // properties for acks
        properties.setProperty("retries", "3"); // how many retries before failing
        properties.setProperty("linger.ms", "1"); // send the producer without lingering(flush)
        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>
                (properties); // creating producer object based on properties
        for (int key=0; key < 10; key++){
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("second_topic", Integer.toString(key),
                    "message that has key: " + Integer.toString(key));
            producer.send(producerRecord); // send the producer
        }

        producer.close(); // need to close the producer
        // topic name
    }
}

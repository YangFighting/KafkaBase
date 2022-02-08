package com.yang;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class CustomConsumer {
    private static final Logger logger = LoggerFactory.getLogger(CustomConsumer.class);
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "kafka1:9092,kafka2:9093,kafka3:9094");
        properties.put("group.id","abcadf");
        properties.put("enable.auto.commit","true");
        properties.put("auto.offset.reset", "earliest");
        properties.put("auto.commit.interval.ms","1000");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("topic001"));


        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records)
                logger.warn("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());
        }

    }
}

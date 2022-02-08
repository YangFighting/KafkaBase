package com.yang;

import com.alibaba.fastjson.JSONObject;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;


public class CallBackProducer {
    private static final Logger logger = LoggerFactory.getLogger(CallBackProducer.class);

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "kafka1:9092,kafka2:9093,kafka3:9094");//kafka 集群， broker-list
        props.put("acks", "all");
        props.put("retries", 1);//重试次数
        props.put("batch.size", 1024 * 16);//批次大小，单位byte
        props.put("linger.ms", 1);//等待时间
        props.put("buffer.memory", 33554432);//RecordAccumulator 缓冲区大小
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < 8; i++) {
            jsonObject.put("id", Integer.toString(i));
            jsonObject.put("time", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSS").format(new Date()));
            jsonObject.put("num", new Random().nextInt(100));
            producer.send(new ProducerRecord<>("topic001", jsonObject.toString()), CallBackProducer::onCompletion);
        }

        producer.close();
    }

    private static void onCompletion(RecordMetadata recordMetadata, Exception exception) {
        if (exception == null)
            logger.warn("partition: {}, offset: {}", recordMetadata.partition(), recordMetadata.offset());
        else {
            logger.error("Callback error...");
            exception.printStackTrace();
        }
    }
}

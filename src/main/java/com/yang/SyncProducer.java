package com.yang;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class SyncProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        //kafka 集群， broker-list
        properties.put("bootstrap.servers", "kafka1:9092,kafka2:9093,kafka3:9094");
        properties.put("acks", "all");
        //重试次数
        properties.put("retries", "1");
        //批次大小
        properties.put("batch.size", "16384");
        //等待时间
        properties.put("linger.ms", 1);
        //RecordAccumulator 缓冲区大小
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, String> producer = new KafkaProducer<>(properties)) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < 5; i++) {
                jsonObject.put("id", Integer.toString(i));
                jsonObject.put("time", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSS").format(new Date()));
                jsonObject.put("num", new Random().nextInt(100));
                producer.send(new ProducerRecord<>("topic001", 0, null, jsonObject.toString())).get();

            }
        }
    }
}

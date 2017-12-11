/*
http://nverma-tech-blog.blogspot.com.br/2015/12/apache-kafka-example-of.html
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class KafkaConsumer extends Thread {

    private ConsumerConnector consumerConnector = null;
    private String topic;

    private File consumerFile;
    
    public KafkaConsumer(String topico) {
        this.topic = topico;
        this.consumerFile = new File("/home/kevin/Downloads/mavenproject1/src/main/java/arquivos/"+this.topic);
        Properties props = new Properties();
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "testgroup");
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "300");
        props.put("auto.commit.interval.ms", "1000");
        ConsumerConfig conConfig = new ConsumerConfig(props);
        this.consumerConnector = Consumer.createJavaConsumerConnector(conConfig);
        this.start();
    }

    @Override
    public void run() {
        
        try {
            //FileWriter fw = new FileWriter(this.consumerFile);

            //Key = topic name, Value = No. of threads for topic
            Map<String, Integer> topicCount = new HashMap<String, Integer>();
            topicCount.put(topic, new Integer(1));

            //ConsumerConnector creates the message stream for each topic
            Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams = consumerConnector.createMessageStreams(topicCount);

            // Get Kafka stream for topic 'mytopic'
            List<KafkaStream<byte[], byte[]>> kStreamList = consumerStreams.get(topic);

            // Iterate stream using ConsumerIterator
            for (final KafkaStream<byte[], byte[]> kStreams : kStreamList) {
                ConsumerIterator<byte[], byte[]> consumerIte = kStreams.iterator();
                FileWriter fw = new FileWriter(this.consumerFile);
                while (consumerIte.hasNext()) {
                    String receptor = new String(consumerIte.next().message());
                    fw.write(receptor+"\n");
                    System.out.println("Message consumed from topic[" + topic + "] : " + receptor);
                }
                fw.close();
            }
            //Shutdown the consumer connector
            if (consumerConnector != null) {
                consumerConnector.shutdown();
            }
        } catch (IOException ex) {
            Logger.getLogger(KafkaConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

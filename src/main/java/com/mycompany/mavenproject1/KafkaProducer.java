/*
http://nverma-tech-blog.blogspot.com.br/2015/12/apache-kafka-example-of.html
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer extends Thread {

    private Producer<Integer, String> producer;
    //private String topic;
    private String filePatch;
    private String[] topicos;

    public KafkaProducer(String filepatch, String[] topics) {
        this.filePatch = filepatch;
        this.topicos = topics;
        Properties producerProps = new Properties();
        producerProps.put("metadata.broker.list", "localhost:9092");
        producerProps.put("serializer.class", "kafka.serializer.StringEncoder");
        producerProps.put("request.required.acks", "1");
        ProducerConfig producerConfig = new ProducerConfig(producerProps);
        this.producer = new Producer<Integer, String>(producerConfig);
        this.start();
    }

    /*public KafkaProducer(String topico) {
        this.topic = topico;
        Properties producerProps = new Properties();
        producerProps.put("metadata.broker.list", "localhost:9092");
        producerProps.put("serializer.class", "kafka.serializer.StringEncoder");
        producerProps.put("request.required.acks", "1");
        ProducerConfig producerConfig = new ProducerConfig(producerProps);
        this.producer = new Producer<Integer, String>(producerConfig);
        this.start();
    }*/
//        @Override
//    public void run() {        
//            for (int i = 0; i < 1000; i++) {
//                String msg = this.topic+" "+i;
//                KeyedMessage<Integer, String> keyedMsg = new KeyedMessage<Integer, String>(topic, msg);
//                producer.send(keyedMsg); // This publishes message on given topic
//            }
//                KeyedMessage<Integer, String> keyedMsg = new KeyedMessage<Integer, String>(topic, "EXIT");
//                producer.send(keyedMsg); // This publishes message on given topic
//        //Close the producer
//        this.producer.close();
//    }
//    @Override
//    public void run() {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        while (true) {
//            System.out.print("Enter message to send to kafka broker(\"EXIT\" to close producer): ");
//            String msg = null;
//            try {
//                msg = reader.readLine(); // Read message from console
//                //Define topic name and message
//                KeyedMessage<Integer, String> keyedMsg = new KeyedMessage<Integer, String>(topic, msg);
//                producer.send(keyedMsg); // This publishes message on given topic
//                if ("EXIT".equals(msg)) {
//                    break;
//                }
//                //System.out.println("--> Message [" + msg + "] sent.Check message on Consumer's program console");
//            } catch (IOException ex) {
//                Logger.getLogger(KafkaProducer.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        //Close the producer
//        this.producer.close();
//    }
    public String searchTopic(String message) {
        for (String t : this.topicos) {
            if (message.contains(t)) {
                return t;
            }
        }
        return "semTopico";
    }

    @Override
    public void run() {
        try {
            Scanner scan = new Scanner(new File(this.filePatch));
            while (scan.hasNextLine()) {
                String reader = scan.nextLine();

                String pegaTopico = this.searchTopic(reader);

                KeyedMessage<Integer, String> keyedMsg = new KeyedMessage<Integer, String>(pegaTopico, reader);
                System.out.println(keyedMsg);
                producer.send(keyedMsg); // This publishes message on given topic

            }
            //Close the producer
            this.producer.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(KafkaProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

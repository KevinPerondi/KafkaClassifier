/*
Antes de executar:

./bin/zookeeper-server-start.sh config/zookeeper.properties
./bin/kafka-server-start.sh config/server.properties

*/
package com.mycompany.mavenproject1;

public class ProducersBuild {

    public static void main(String[] args) {
        String[] topicos = {"cracker", "hacker", "password", "malware", "virus", "http", "semTopico"};
        //String[] topicos = {"malware", "virus","semTopico"};
        String patch = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/filtrado2.txt";

        KafkaProducer produtor = new KafkaProducer(patch,topicos);

        //3 produtores dividindo pelo split
        /*String patch1 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testeaa";
        String patch2 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testeab";
        String patch3 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testeac";

        KafkaProducer produtor1 = new KafkaProducer(patch1, topicos);
        KafkaProducer produtor2 = new KafkaProducer(patch2, topicos);
        KafkaProducer produtor3 = new KafkaProducer(patch3, topicos);*/
        
        //10 produtores dividindo pelo split
        /*String patch1 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testeaa";
            String patch2 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testeab";
            String patch3 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testeac";
            String patch4 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testead";
            String patch5 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testeae";
            String patch6 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testeaf";
            String patch7 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testeag";
            String patch8 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testeah";
            String patch9 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testeai";
            String patch10 = "/home/kevin/Documentos/Facul/Sistemas Distribuidos/APS Kafka/testeaj";


            KafkaProducer produtor1 = new KafkaProducer(patch1,topicos);
            KafkaProducer produtor2 = new KafkaProducer(patch2,topicos);
            KafkaProducer produtor3 = new KafkaProducer(patch3,topicos);
            KafkaProducer produtor4 = new KafkaProducer(patch4,topicos);
            KafkaProducer produtor5 = new KafkaProducer(patch5,topicos);
            KafkaProducer produtor6 = new KafkaProducer(patch6,topicos);
            KafkaProducer produtor7 = new KafkaProducer(patch7,topicos);
            KafkaProducer produtor8 = new KafkaProducer(patch8,topicos);
            KafkaProducer produtor9 = new KafkaProducer(patch9,topicos);
            KafkaProducer produtor10 = new KafkaProducer(patch10,topicos);*/
    }
}

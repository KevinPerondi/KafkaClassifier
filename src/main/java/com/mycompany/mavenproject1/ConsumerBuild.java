/*
Antes de executar:

./bin/zookeeper-server-start.sh config/zookeeper.properties
./bin/kafka-server-start.sh config/server.properties

*/

package com.mycompany.mavenproject1;

import org.apache.log4j.BasicConfigurator;

public class ConsumerBuild {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        //String[] topicos = {"malware", "virus","semTopico"};
        String[] topicos = {"cracker","hacker","password","malware","virus","http","semTopico"};
        for (String str : topicos){
            KafkaConsumer newConsumer = new KafkaConsumer(str);
        }

    }
}

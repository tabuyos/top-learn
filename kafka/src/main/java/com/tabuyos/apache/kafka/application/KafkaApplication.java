package com.tabuyos.apache.kafka.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Tabuyos
 * @Time 11/12/20 10:57 AM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
@SpringBootApplication(scanBasePackages = {"com.tabuyos.apache.kafka"})
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }
}

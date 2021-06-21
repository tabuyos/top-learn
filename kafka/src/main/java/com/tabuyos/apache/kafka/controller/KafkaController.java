package com.tabuyos.apache.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Tabuyos
 * @Time 11/12/20 10:58 AM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
@RestController
public class KafkaController {

    private final Logger logger = LoggerFactory.getLogger(KafkaController.class);
//    private final KafkaTemplate<Object, Object> template;
//
//    public KafkaController(KafkaTemplate<Object, Object> template) {
//        this.template = template;
//    }

    @GetMapping("/send/{input}")
    public void sendFoo(@PathVariable String input) {
        logger.trace("trace input value: {}", input);
        logger.debug("debug input value: {}", input);
        logger.info("info input value: {}", input);
        logger.warn("warn input value: {}", input);
        logger.error("error input value: {}", input);
    }

//    @KafkaListener(id = "webGroup", topics = "topic_input")
//    public void listen(String input) {
//        logger.info("input value: {}", input);
//    }
}

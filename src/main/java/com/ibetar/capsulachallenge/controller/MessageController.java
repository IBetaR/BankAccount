//package com.ibetar.capsulachallenge.controller;
//
//import com.ibetar.capsulachallenge.persistence.entity.MessageRequest;
//import io.swagger.annotations.Api;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@RestController
//@Api
//@CrossOrigin(origins = "*")
//@RequestMapping(path = "api/v1/messages")
//public class MessageController {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    @PostMapping
//    public void publish(@RequestBody MessageRequest request){
//        kafkaTemplate.send("ibetar", request.message());
//    }
//}

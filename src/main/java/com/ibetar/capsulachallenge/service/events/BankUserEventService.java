package com.ibetar.capsulachallenge.service.events;

import com.ibetar.capsulachallenge.events.Event;
import com.ibetar.capsulachallenge.events.EventType;
import com.ibetar.capsulachallenge.events.TransactionCreatedEvent;
import com.ibetar.capsulachallenge.persistence.entity.BankUser;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class BankUserEventService {

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("${topic.bankUser.firstname:bankUsers}")
    private String topicUser;

    public void publish(BankUserDto bankUser){
        TransactionCreatedEvent createdEvent = new TransactionCreatedEvent();
        createdEvent.setData(bankUser);
        createdEvent.setId(UUID.randomUUID().toString());
        createdEvent.setEventType(EventType.CREATED);
        createdEvent.setDate(new Date());

        this.producer.send(topicUser,createdEvent);
    }
}

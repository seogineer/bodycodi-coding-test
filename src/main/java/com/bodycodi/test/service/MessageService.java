package com.bodycodi.test.service;

import com.bodycodi.test.dto.MessageDto;
import com.bodycodi.test.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public int insert(MessageDto message) {
        return messageRepository.insert(message);
    }

    public List<MessageDto> select(int recipient, int start, int limit) {
        return messageRepository.select(recipient, start, limit);
    }
}

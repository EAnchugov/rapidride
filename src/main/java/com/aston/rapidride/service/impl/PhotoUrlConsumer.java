package com.aston.rapidride.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoUrlConsumer {

    private final PhotoServiceImpl photoService;

    @KafkaListener(topics = "photo-urls", groupId = "photo-url-group")
    public void listen(String photoUrl) {
        try {
            photoService.savePhoto(photoUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

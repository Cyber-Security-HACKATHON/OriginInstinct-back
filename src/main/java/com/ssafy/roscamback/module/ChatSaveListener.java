package com.ssafy.roscamback.module;

import com.ssafy.roscamback.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class ChatSaveListener {

    private final RedisUtil redisUtil;

    public ChatSaveListener(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Async
    @EventListener
    @Transactional
    public void handleChatSaveEvent(ChatSaveEvent event) {
        log.info("Chat Save Event Open");

        log.info("Saving Data = {}", event.getMessage());
        redisUtil.insert("msg", event.getMessage());
    }
}

package com.ssafy.roscamback.module;

public class ChatSaveEvent {
    private String message;

    public ChatSaveEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

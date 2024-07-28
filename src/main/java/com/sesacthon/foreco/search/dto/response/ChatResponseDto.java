package com.sesacthon.foreco.search.dto.response;

import java.util.UUID;
import lombok.Getter;

@Getter
public class ChatResponseDto {
    private final String id;
    private final String message;

    public ChatResponseDto(String chatMessage) {
        this.id = UUID.randomUUID().toString();
        this.message = chatMessage;
    }
}

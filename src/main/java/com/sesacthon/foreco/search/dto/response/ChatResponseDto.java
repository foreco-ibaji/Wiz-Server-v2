package com.sesacthon.foreco.search.dto.response;

import lombok.Getter;

@Getter
public class ChatResponseDto {
    private final String id;
    private final String message;
    private final Long created;

    public ChatResponseDto(UpstageResponseDto aiResponse) {
        this.id = aiResponse.getId();
        this.message = aiResponse.getChoices().get(0).getMessage().getContent();
        this.created = aiResponse.getCreated();
    }
}

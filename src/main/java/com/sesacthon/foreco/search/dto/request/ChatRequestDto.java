package com.sesacthon.foreco.search.dto.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ChatRequestDto {

    private String query;
    private String region;

    public static ChatRequestDto newInstance(ChatSearchRequestDto request) {
        return ChatRequestDto.builder()
            .query(request.getSearchMessage())
            .region(request.getRegion())
            .build();
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

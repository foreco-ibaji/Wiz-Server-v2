package com.sesacthon.foreco.search.dto.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sesacthon.foreco.search.dto.Message;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ChatRequestDto {

    private final String model = "solar-1-mini-chat";
    private List<Message> messages;
    private final boolean stream = false;

    public static ChatRequestDto newInstance(String message) {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("system", "You are a helpful assistant."));
        messages.add(new Message("user", message));

        return ChatRequestDto.builder()
            .messages(messages)
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("\"model\": \"").append(model).append("\",\n");
        sb.append("\"messages\": [\n");
        for (Message message : messages) {
            sb.append(message.toString()).append(",\n");
        }
        if (!messages.isEmpty()) {
            sb.setLength(sb.length() - 2); // 마지막 쉼표 제거
            sb.append("\n");
        }
        sb.append("],\n");
        sb.append("\"stream\": ").append(stream).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
